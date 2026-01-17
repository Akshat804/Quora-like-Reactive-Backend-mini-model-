package com.example.demo.models;


import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.elasticsearch.annotations.Document;


@Setter
@Builder
@Data
@Document(indexName = "questions")
public class QuestionElasticDocument {
    private String id;
    private String title;
    private String content;
}
