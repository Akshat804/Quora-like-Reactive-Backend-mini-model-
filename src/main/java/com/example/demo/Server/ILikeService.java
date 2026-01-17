package com.example.demo.Server;

import com.example.demo.DTO.LikeREQDTO;
import com.example.demo.DTO.LikeResponseDTO;
import reactor.core.publisher.Mono;

public interface ILikeService {
    Mono<LikeResponseDTO> createLike(LikeREQDTO dto);
    Mono<LikeResponseDTO> countLikesByTargetType(String targetID,String targetType);
    Mono<LikeResponseDTO> countLikes(String targetID,String targetType);
    Mono<LikeResponseDTO> toggleLike(LikeREQDTO dto);


}
