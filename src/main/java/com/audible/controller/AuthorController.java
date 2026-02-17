package com.audible.controller;



import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;

//import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



import com.audible.dto.AuthorDTO;
import com.audible.service.AuthorService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/authors")
//@CrossOrigin(origins = "http://localhost:3000")

public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @PostMapping
    public ResponseEntity<AuthorDTO> addAuthor(@Valid @RequestBody AuthorDTO authorDTO) {

        AuthorDTO savedAuthor = authorService.addAuthor(authorDTO);
        return new ResponseEntity<>(savedAuthor, HttpStatus.CREATED);
    }

    @GetMapping("/{authorId}")

    public ResponseEntity<AuthorDTO> getAuthorById(@PathVariable Integer authorId) {
        AuthorDTO author = authorService.getAuthorById(authorId);
        return new ResponseEntity<>(author, HttpStatus.OK);

    }

    @GetMapping

    public ResponseEntity<List<AuthorDTO>> getAllAuthors() {
        List<AuthorDTO> authors = authorService.getAllAuthors();
        return new ResponseEntity<>(authors, HttpStatus.OK);
    }

}

