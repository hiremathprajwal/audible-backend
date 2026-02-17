package com.audible.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.audible.entity.Audiobook;

@Repository
public interface AudiobookRepository extends JpaRepository<Audiobook, Integer> {
    List<Audiobook> findByTitleContainingIgnoreCase(String title);

    List<Audiobook> findByAuthorAuthorId(Integer authorId);
}
