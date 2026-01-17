package com.example.demo.Server;

import com.example.demo.DTO.QuestionRequestDTO;
import com.example.demo.DTO.QuestionResponseDTO;
import com.example.demo.Prroducer.KafkaEventProducer;
import com.example.demo.adapter.QuestionAdapter;
import com.example.demo.events.ViewCount;
import com.example.demo.models.Question;
import com.example.demo.models.QuestionElasticDocument;
import com.example.demo.repositories.QuestionElasticRepo;
import com.example.demo.repositories.QuestionRepository;
import com.example.demo.utils.CursorUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionService implements IQuestionService {

    private final QuestionRepository questionRepository;
    private final KafkaEventProducer kafkaEventProducer;// ✅ Injected producer

    private final IQuestionIndexService questionIndexService;
    private final QuestionElasticRepo questionElasticRepo;

    @Override
    public Mono<QuestionResponseDTO> createQuestion(QuestionRequestDTO questionRequestDTO) {
        Question question = Question.builder()
                .title(questionRequestDTO.getTitle())
                .content(questionRequestDTO.getContent())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        return questionRepository.save(question)
                .map(savedQuestion->{

                    questionIndexService.createQuestionIndex(savedQuestion);

                    return QuestionAdapter.toQuestionResponseDTO((savedQuestion));
                })
                .doOnSuccess(response -> System.out.println("Question created successfully: " + response))
                .doOnError(error -> System.out.println("Error creating question: " + error));
    }

    @Override
    public Flux<QuestionResponseDTO> searchQuestions(String searchTerm, int page, int size) {
        return questionRepository.findByTitleOrContentContainingIgnoreCase(searchTerm, PageRequest.of(page, size))
                .map(QuestionAdapter::toQuestionResponseDTO)
                .doOnError(error -> System.out.println("Error searching questions:" + error))
                .doOnComplete(() -> System.out.println("Question search completed"));
    }

    @Override
    public Flux<QuestionResponseDTO> getAllQuestions(String cursor, int size) {
        Pageable pageable = PageRequest.of(0, size);
        if (!CursorUtils.isValidCursor(cursor)) {
            return questionRepository.findTop10ByOrderByCreatedAtDesc()
                    .map(QuestionAdapter::toQuestionResponseDTO)
                    .doOnError(error -> System.out.println("Error fetching questions: " + error))
                    .doOnComplete(() -> System.out.println("Question fetch completed"));
        } else {
            LocalDateTime cursorTimestamp = CursorUtils.parseCursor(cursor);
            return questionRepository.findByCreatedAtGreaterThanOrderByCreatedAtAsc(cursorTimestamp, pageable)
                    .map(QuestionAdapter::toQuestionResponseDTO)
                    .doOnError(error -> System.out.println("Error fetching questions: " + error))
                    .doOnComplete(() -> System.out.println("Question fetch completed"));
        }
    }

    @Override
    public Mono<QuestionResponseDTO> getQuestionById(String id) {
        return questionRepository.findById(id)
                .map(QuestionAdapter::toQuestionResponseDTO)
                .doOnError(error -> System.out.println("Error fetching question: " + error))
                .doOnSuccess(response -> {
                    System.out.println("Question fetched successfully: " + response);

                    // ✅ Produce Kafka event properly
                    ViewCount viewCount = new ViewCount(id, "question", LocalDateTime.now());
                    kafkaEventProducer.PublishViewCountEvent(viewCount);
                });
    }

    public List<QuestionElasticDocument> searchQuestionsbyElastic(String query){
        return questionElasticRepo.findByTitleContainingOrContentContaining(query,query);
    }
}
