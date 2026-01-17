package com.example.demo.DTO;

import jakarta.validation.constraints.NotBlank;

public class LikeREQDTO {
    @NotBlank(message = "Target ID is required")
    private String targetType;
    @NotBlank(message = "Target ID is required")
    private String targetId;
    @NotBlank(message = "ISLIKE is required")
    private boolean isLike;
}
