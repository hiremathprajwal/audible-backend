package com.audible.service.impl;

import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.audible.dto.AudiobookDTO;
import com.audible.dto.WishlistDTO;
import com.audible.entity.Audiobook;
import com.audible.entity.Customer;
import com.audible.entity.Wishlist;
import com.audible.exception.AudibleException;
import com.audible.repository.AudiobookRepository;
import com.audible.repository.CustomerRepository;
import com.audible.repository.WishlistRepository;
import com.audible.service.WishlistService;
import com.audible.util.MediaUrlUtil;



@Service
@Transactional

public class WishlistServiceImpl implements WishlistService {

    @Autowired
    private WishlistRepository wishlistRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AudiobookRepository audioBookRepository;

    private ModelMapper modelMapper=new ModelMapper();

    @Override
    public void addToWishlist(Integer customerId, Integer audioId) {

        if (wishlistRepository
                .findByCustomerCustomerIdAndAudiobookAudioId(customerId, audioId)
                .isPresent()) {
            throw new AudibleException("Service.WISHLIST");

        }

        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new AudibleException("Service.WISHLIST_CUSTOMER_NOTFOUND"));
        Audiobook audiobook = audioBookRepository.findById(audioId)
                .orElseThrow(() -> new AudibleException("Service.WISHLIST_AUDIO_NOTFOUND"));

        Wishlist w = new Wishlist();

        w.setCustomer(customer);
        w.setAudiobook(audiobook);
        wishlistRepository.save(w);

    }



    @Override
    public void removeFromWishlist(Integer customerId, Integer audioId) {

        Wishlist w = wishlistRepository
                .findByCustomerCustomerIdAndAudiobookAudioId(customerId, audioId)
                .orElseThrow(() -> new AudibleException("Service.NOTFOUND"));
        wishlistRepository.delete(w);

    }

    @Override
    public List<WishlistDTO> getWishlist(Integer customerId) {

        return wishlistRepository.findByCustomerCustomerId(customerId)
                .stream()
                .map(w -> {
                    WishlistDTO dto = modelMapper.map(w, WishlistDTO.class);
                    AudiobookDTO bookDto = modelMapper.map(w.getAudiobook(),AudiobookDTO.class);
                    MediaUrlUtil.applyMediaUrls(bookDto);
                    dto.setAudiobook(bookDto);
                    dto.setAudioId(bookDto.getAudioId());
                    return dto;

                }).toList();

    }
}