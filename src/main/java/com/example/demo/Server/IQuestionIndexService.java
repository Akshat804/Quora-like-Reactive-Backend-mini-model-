package com.example.demo.Server;

import com.example.demo.models.Question;
import com.example.demo.models.QuestionElasticDocument;

public interface IQuestionIndexService {
    public void createQuestionIndex(Question question);
}
