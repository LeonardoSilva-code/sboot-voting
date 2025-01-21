package com.example.sboot_voting.adapters.in.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AgendaRequestDTO {
    @NotBlank(message = "Title cannot be blank")
    private String title;
    @NotBlank(message = "Description cannot be blank")
    private String description;
}
