package com.audible.service.impl;

import java.util.List;

import org.modelmapper.ModelMapper;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import com.audible.dto.AuthorDTO;

import com.audible.entity.Author;
import com.audible.exception.AudibleException;
import com.audible.repository.AuthorRepository;
import com.audible.service.AuthorService;

@Service
@Transactional

public class AuthorServiceImpl implements AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    private ModelMapper modelMapper = new ModelMapper();

    @Override
    public AuthorDTO addAuthor(AuthorDTO authorDTO) {

        Author author = modelMapper.map(authorDTO, Author.class);

        Author savedAuthor = authorRepository.save(author);

        return modelMapper.map(savedAuthor, AuthorDTO.class);

    }

    @Override
    public AuthorDTO getAuthorById(Integer authorId) {

        Author author = authorRepository.findById(authorId)
                .orElseThrow(() -> new AudibleException("Service.AUTHOR_NOT_FOUND"));
        return mapToDTO(author);

    }

    @Override
    public List<AuthorDTO> getAllAuthors() {

        return authorRepository.findAll().stream()
                .map(this::mapToDTO)
                .toList();

    }

    private AuthorDTO mapToDTO(Author author) {
        return modelMapper.map(author, AuthorDTO.class);

    }

}