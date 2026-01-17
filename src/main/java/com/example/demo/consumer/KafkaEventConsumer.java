package com.example.demo.consumer;

import com.example.demo.config.KafkaConfig;
import com.example.demo.events.ViewCount;
import com.example.demo.repositories.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaEventConsumer {
    private final QuestionRepository questionsRepository;

    @KafkaListener(topics = KafkaConfig.TOPIC_NAME, groupId = "view-count-consumer", containerFactory = "kafkaListenerContainerFactory")
    public void handleViewCountEvent(ViewCount viewCount) {
        questionsRepository.findById(viewCount.getTargetId())
                .flatMap(question -> {
                    Integer views = question.getViews();
                    question.setViews(views == null ? 1 : views + 1);
                    return questionsRepository.save(question);
                })
                .subscribe(updateQuestion -> {
                    System.out.println("Question updated: " + updateQuestion.getId());
                }, error -> {
                    System.out.println("Error updating question: " + error.getMessage());
                });
    }
}
