package com.example.demo.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AnswerReqDTO {
    @NotBlank(message = "Answer cannot be blank")
    @Size(min = 2, max = 255, message = "Answer must be between 2 and 255 characters")
    private String content;

    @NotBlank(message = "Question ID cannot be blank")
    private String questionId;


}
