package com.audible.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.audible.dto.LibraryDTO;
import com.audible.service.LibraryService;

@RestController
@RequestMapping("/api/library")
//@CrossOrigin(origins = "http://localhost:3000")

public class LibraryController {

    @Autowired
    private LibraryService libraryService;

    @GetMapping("/{customerId}")
    public ResponseEntity<List<LibraryDTO>> getLibraryByCustomerId(@PathVariable Integer customerId) {

        List<LibraryDTO> library = libraryService.getLibraryByCustomerId(customerId);
        return new ResponseEntity<>(library, HttpStatus.OK);
    }

    @DeleteMapping("/{customerId}/{audioId}")
    public ResponseEntity<Void> removeFromLibrary(@PathVariable Integer customerId, @PathVariable Integer audioId) {
        libraryService.removeFromLibrary(customerId, audioId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}