package org.example.phase2.dto;

public record TaskResponseDto (
        Long id,
        String title,
        boolean completed
){
}
