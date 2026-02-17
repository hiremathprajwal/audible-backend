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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.audible.dto.AudiobookDTO;
import com.audible.exception.AudibleException;
import com.audible.service.AudiobookService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/audiobooks")



//@CrossOrigin(origins = "http://localhost:3000")



public class AudiobookController {

    @Autowired
    private AudiobookService audiobookService;

    @PostMapping
    public ResponseEntity<AudiobookDTO> addAudiobook(@Valid @RequestBody AudiobookDTO audiobookDTO) {

        AudiobookDTO savedAudiobook = audiobookService.addAudiobook(audiobookDTO);
        return new ResponseEntity<>(savedAudiobook, HttpStatus.CREATED);

    }



    @GetMapping("/{audioId}")
    public ResponseEntity<AudiobookDTO> getAudiobookById(@PathVariable Integer audioId) throws AudibleException {

        AudiobookDTO audiobook = audiobookService.getAudiobookById(audioId);
        return new ResponseEntity<>(audiobook, HttpStatus.OK);
    }



    @GetMapping
    public ResponseEntity<List<AudiobookDTO>> getAllAudiobooks() {

        List<AudiobookDTO> audiobooks = audiobookService.getAllAudiobooks();
        return new ResponseEntity<>(audiobooks, HttpStatus.OK);
    }



    @GetMapping("/search")
//http://localhost:5050/api/audiobooks/search?title=Ikigai

    public ResponseEntity<List<AudiobookDTO>> searchAudiobooks(@RequestParam String title) {
        List<AudiobookDTO> audiobooks = audiobookService.searchAudiobooks(title);
        return new ResponseEntity<>(audiobooks, HttpStatus.OK);

    }



    @GetMapping("/author/{authorId}")
    public ResponseEntity<List<AudiobookDTO>> getAudiobooksByAuthor(@PathVariable Integer authorId) {

        List<AudiobookDTO> audiobooks = audiobookService.getAudiobooksByAuthor(authorId);
        return new ResponseEntity<>(audiobooks, HttpStatus.OK);
    }

}