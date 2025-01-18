package com.example.sboot_voting.adapters.in.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AgendaRequestDTO {
    private String title;
    private String description;
}
