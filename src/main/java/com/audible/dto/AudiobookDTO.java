package com.audible.dto;

import java.math.BigDecimal;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class AudiobookDTO {

    private Integer audioId;

    @NotBlank(message = "Title is required")
    private String title;

    private String narrator;
    private Integer duration;
    private String description;

    @NotNull(message = "Price is required")
    private BigDecimal price;

    private String coverImage;
    private String audioFile;
    private String shortClip;
    private Float totalStar;
    private Integer authorId;
    private String authorName;

}


