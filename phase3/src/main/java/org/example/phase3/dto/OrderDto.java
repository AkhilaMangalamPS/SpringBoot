package org.example.phase3.dto;

import jakarta.validation.constraints.NotNull;

public record OrderDto (

        Long id,

        @NotNull(message = "Product name is required")
        String product,

        @NotNull(message = "User id is required")
        Long userId
){}
