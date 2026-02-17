package com.audible.entity;

import java.math.BigDecimal;

import jakarta.persistence.Column;

import jakarta.persistence.Entity;

import jakarta.persistence.FetchType;

import jakarta.persistence.GeneratedValue;

import jakarta.persistence.GenerationType;

import jakarta.persistence.Id;

import jakarta.persistence.JoinColumn;

import jakarta.persistence.ManyToOne;

import jakarta.persistence.Table;

import lombok.AllArgsConstructor;

import lombok.Data;

import lombok.NoArgsConstructor;

@Entity
@Table(name = "audiobooks")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Audiobook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "audio_id")
    private Integer audioId;

    @Column(name = "title", nullable = false, length = 200)
    private String title;

    @Column(name = "narrator", length = 100)
    private String narrator;

    @Column(name = "duration")
    private Integer duration;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "price", nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @Column(name = "cover_image", length = 500)
    private String coverImage;

    @Column(name = "audio_file", length = 500)
    private String audioFile;

    @Column(name = "short_clip", length = 500)
    private String shortClip;

    @Column(name = "total_star")
    private Float totalStar;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private Author author;

}