package com.example.demo.Server;

import com.example.demo.DTO.AnswerReqDTO;
import com.example.demo.DTO.AnswerResponseDTO;
import com.example.demo.models.Answer;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
public interface IAnswerService {
    public Mono<AnswerResponseDTO> createAnswer(AnswerReqDTO answerRequestDTO);

    public Mono<AnswerResponseDTO> getAnswerById(String id);

    public Mono<AnswerResponseDTO> updateAnswer(String id, AnswerReqDTO answerRequestDTO);

    public Mono<Void> deleteAnswer(String id);

    public Flux<AnswerResponseDTO> getAllAnswers();

    public Flux<AnswerResponseDTO> getAnswersByQuestionId(String questionId);

    public Mono<Long> getAnswerCountByQuestionId(String questionId);

    public Flux<AnswerResponseDTO> getAnswersByQuestionIdOrderByCreatedAtDesc(String questionId);

    public Flux<AnswerResponseDTO> getAnswersByQuestionIdOrderByCreatedAtAsc(String questionId);
}
