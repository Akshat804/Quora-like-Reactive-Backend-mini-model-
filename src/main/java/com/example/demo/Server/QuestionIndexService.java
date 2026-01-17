package com.example.demo.Server;

import com.example.demo.models.Question;
import com.example.demo.models.QuestionElasticDocument;
import com.example.demo.repositories.QuestionElasticRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class QuestionIndexService implements IQuestionIndexService {

 private final QuestionElasticRepo questionElasticRepo;

 @Override
    public void createQuestionIndex(Question question){
        QuestionElasticDocument Document=QuestionElasticDocument.builder()

                .id(question.getId())
                .title(question.getTitle())
                .content(question.getContent())
                .build();



        questionElasticRepo.save(Document);






 }
}
