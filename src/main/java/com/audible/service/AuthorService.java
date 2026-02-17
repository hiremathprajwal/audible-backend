package com.audible.service;

import java.util.List;

import com.audible.dto.AuthorDTO;

public interface AuthorService {
    AuthorDTO addAuthor(AuthorDTO authorDTO);
    AuthorDTO getAuthorById(Integer authorId);
    List<AuthorDTO> getAllAuthors();
}
