package com.audible.service;

import java.util.List;

import com.audible.dto.AudiobookDTO;

public interface AudiobookService {
    AudiobookDTO addAudiobook(AudiobookDTO audiobookDTO);
    AudiobookDTO getAudiobookById(Integer audioId);
    List<AudiobookDTO> getAllAudiobooks();
    List<AudiobookDTO> searchAudiobooks(String title);
    List<AudiobookDTO> getAudiobooksByAuthor(Integer authorId);
}
