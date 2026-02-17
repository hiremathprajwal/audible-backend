package com.audible.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class AuthorDTO {

    private Integer authorId;

    @NotBlank(message = "Author name is required")
    @Size(max = 100, message = "Author name cannot exceed 100 characters")
    private String authorName;

    @Size(max = 100, message = "Author email cannot exceed 100 characters")
    private String authorEmail;

    private String description;
}