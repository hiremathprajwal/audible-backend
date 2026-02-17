package com.audible.service;

import java.util.List;

import com.audible.dto.LibraryDTO;

public interface LibraryService {

    List<LibraryDTO> getLibraryByCustomerId(Integer customerId);

    void addToLibrary(Integer customerId, Integer audioId);

    void removeFromLibrary(Integer customerId, Integer audioId);
}
