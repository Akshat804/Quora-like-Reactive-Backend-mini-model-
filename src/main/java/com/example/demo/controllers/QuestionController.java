package com.example.demo.controllers;

import com.example.demo.DTO.QuestionRequestDTO;
import com.example.demo.DTO.QuestionResponseDTO;
import com.example.demo.Server.IQuestionService;
import com.example.demo.models.QuestionElasticDocument;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/api/questions")
@RequiredArgsConstructor
public class QuestionController {
    private final IQuestionService questionService;

    @PostMapping()
    public Mono<QuestionResponseDTO> createQuestion(@RequestBody QuestionRequestDTO questionRequestDTO) {
        return questionService.createQuestion(questionRequestDTO)
                .doOnSuccess(response -> System.out.println("Question created successfully: " + response))
                .doOnError(error -> System.out.println("Error creating question: " + error));
    }
    @GetMapping("/search")
    public Flux<QuestionResponseDTO> searchQuestions(
        @RequestParam String query,
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size
        ){
      return questionService.searchQuestions(query, page, size)
                .doOnNext(response -> System.out.println("Question found: " + response))
                .doOnError(error -> System.out.println("Error searching questions: " + error));

        }
        @GetMapping("/getqsns")
    public Flux<QuestionResponseDTO> getAllQuestions(
            @RequestParam(required = false) String cursor,
            @RequestParam(defaultValue ="10") int size
    ){
        return questionService.getAllQuestions(cursor, size)
                .doOnError(error -> System.out.println("Error searching questions: " + error))
                .doOnComplete(() -> System.out.println("Question found: "));

        }

        @GetMapping("/{id}")
    public Mono<QuestionResponseDTO> getQuestionById(@PathVariable String id){
        return questionService.getQuestionById(id)
                .doOnNext(response -> System.out.println("Question found: " + response))
                .doOnError(error -> System.out.println("Error searching questions: " + error));

        }

        @GetMapping("/elasticsearch")

       public List<QuestionElasticDocument>search(@RequestParam String query){
        return  questionService.searchQuestionsbyElastic(query);
        }



}
