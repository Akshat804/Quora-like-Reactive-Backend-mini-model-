package com.example.demo.Server;

import com.example.demo.DTO.QuestionRequestDTO;
import com.example.demo.DTO.QuestionResponseDTO;
import com.example.demo.models.Question;
import com.example.demo.models.QuestionElasticDocument;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface IQuestionService {
    public Mono<QuestionResponseDTO> createQuestion(QuestionRequestDTO questionRequestDTO);
    public Flux<QuestionResponseDTO> searchQuestions(String searchTerm,int page,int size);
    public Flux<QuestionResponseDTO>getAllQuestions(String cursor,int size);
    public Mono<QuestionResponseDTO> getQuestionById(String id);
    public List<QuestionElasticDocument> searchQuestionsbyElastic(String query);

}
