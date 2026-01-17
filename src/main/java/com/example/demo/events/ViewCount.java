package com.example.demo.events;

import lombok.*;

import java.time.LocalDateTime;
@Getter
@Setter
@AllArgsConstructor
@Data
@NoArgsConstructor
public class ViewCount {
    private String targetId;
    private String targetType;
    private LocalDateTime timestamp;
}
