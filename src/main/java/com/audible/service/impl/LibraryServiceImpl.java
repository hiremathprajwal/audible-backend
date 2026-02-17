package com.audible.service.impl;

import java.util.List;



import org.modelmapper.ModelMapper;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;



import com.audible.dto.AudiobookDTO;

import com.audible.dto.LibraryDTO;

import com.audible.entity.Audiobook;

import com.audible.entity.Customer;

import com.audible.entity.Library;

import com.audible.exception.AudibleException;

import com.audible.repository.AudiobookRepository;

import com.audible.repository.CustomerRepository;

import com.audible.repository.LibraryRepository;

import com.audible.service.LibraryService;

import com.audible.util.MediaUrlUtil;



@Service
@Transactional



public class LibraryServiceImpl implements LibraryService {
    @Autowired
    private LibraryRepository libraryRepository;



    @Autowired
    private CustomerRepository customerRepository;

    private ModelMapper modelMapper=new ModelMapper();



    @Autowired
    private AudiobookRepository audiobookRepository;



    @Override
    public List<LibraryDTO> getLibraryByCustomerId(Integer customerId) {
        return libraryRepository.findByCustomerCustomerId(customerId).stream()
                .map(this::mapToDTO)
                .toList();
    }



    @Override
    public void addToLibrary(Integer customerId, Integer audioId) {
        if (libraryRepository.findByCustomerCustomerIdAndAudiobookAudioId(customerId, audioId).isPresent()) {
            return;
        }

        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new AudibleException("Service.Customer.ID_NOT_FOUND"));

        Audiobook audiobook = audiobookRepository.findById(audioId)
                .orElseThrow(() -> new AudibleException("Service.Customer.ID_NOT_FOUND"));



        Library library = new Library();
        library.setCustomer(customer);
        library.setAudiobook(audiobook);
        library.setLastPosition(0);
        library.setIsCompleted(false);

        libraryRepository.save(library);

    }



    @Override
    public void removeFromLibrary(Integer customerId, Integer audioId) {
        Library library = libraryRepository.findByCustomerCustomerIdAndAudiobookAudioId(customerId, audioId)
                .orElseThrow(() -> new AudibleException("Service.AUDIOBOOK_NOT_FOUND_LIBRARY"));

        libraryRepository.delete(library);

    }



    private LibraryDTO mapToDTO(Library library) {

        LibraryDTO dto = modelMapper.map(library, LibraryDTO.class);
        AudiobookDTO audioDTO = modelMapper.map(library.getAudiobook(), AudiobookDTO.class);
        dto.setAudiobook(audioDTO);
        MediaUrlUtil.applyMediaUrls(audioDTO);

        return dto;

    }
}
