package com.audible.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class LibraryDTO {

    private Integer libraryId;
    private Integer customerId;
    private Integer audioId;
    private AudiobookDTO audiobook;
    private Integer lastPosition;
    private Boolean isCompleted;

}
