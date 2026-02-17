package com.audible.service.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.audible.dto.AudiobookDTO;
import com.audible.entity.Audiobook;
import com.audible.entity.Author;
import com.audible.exception.AudibleException;
import com.audible.repository.AudiobookRepository;
import com.audible.repository.AuthorRepository;
import com.audible.service.AudiobookService;
import com.audible.util.MediaUrlUtil;

@Service

@Transactional

public class AudiobookServiceImpl implements AudiobookService {

    @Autowired
    private AudiobookRepository audiobookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    private ModelMapper modelMapper = new ModelMapper();

    public AudiobookDTO addAudiobook(AudiobookDTO dto) {
        Audiobook audiobook = modelMapper.map(dto, Audiobook.class);

        if (dto.getAuthorId() != null) {
            Author author = authorRepository.findById(dto.getAuthorId())
                    .orElseThrow(() -> new AudibleException("Service.AUDIOBOOK_ID_NOT_FOUND"));
            audiobook.setAuthor(author);

        }

        Audiobook savedAudiobook = audiobookRepository.save(audiobook);
        return modelMapper.map(savedAudiobook, AudiobookDTO.class);

    }

    @Override
    public AudiobookDTO getAudiobookById(Integer audioId) {

        Audiobook audiobook = audiobookRepository.findById(audioId)
                .orElseThrow(() -> new AudibleException("Service.AUDIOBOOK_NOT_FOUND"));
        return mapToDTO(audiobook);
    }

    @Override
    public List<AudiobookDTO> getAllAudiobooks() {
        return audiobookRepository.findAll().stream()
                .map(this::mapToDTO)
                .toList();
    }

    @Override
    public List<AudiobookDTO> searchAudiobooks(String title) {
        return audiobookRepository.findByTitleContainingIgnoreCase(title).stream()
                .map(this::mapToDTO).
                        toList();
    }

    @Override
    public List<AudiobookDTO> getAudiobooksByAuthor(Integer authorId) {

        return audiobookRepository.findByAuthorAuthorId(authorId).stream()
                .map(this::mapToDTO)
                .toList();
    }

    private AudiobookDTO mapToDTO(Audiobook audiobook) {
        AudiobookDTO dto = modelMapper.map(audiobook, AudiobookDTO.class);

        if (audiobook.getAuthor() != null) {
            dto.setAuthorId(audiobook.getAuthor().getAuthorId());
            dto.setAuthorName(audiobook.getAuthor().getAuthorName());
        }
        MediaUrlUtil.applyMediaUrls(dto);
        return dto;
    }
}