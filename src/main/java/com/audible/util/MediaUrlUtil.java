package com.audible.util;

import org.springframework.util.StringUtils;
import com.audible.dto.AudiobookDTO;

public final class MediaUrlUtil {

    private static final String BASE_URL = "http://localhost:5050";
    private MediaUrlUtil() {
    }

    public static void applyMediaUrls(AudiobookDTO dto) {
        if (dto == null) {
            return;
        }

        dto.setCoverImage(resolveUrl(dto.getCoverImage(), "images/audiobooks"));
        dto.setAudioFile(resolveUrl(dto.getAudioFile(), "audio/audiobooks"));
        dto.setShortClip(resolveUrl(dto.getShortClip(), "audio/clips"));

    }

    private static String resolveUrl(String value, String resourcePath) {
        if (!StringUtils.hasText(value)) {
            return null;
        }

        if (value.startsWith("http://") || value.startsWith("https://")) {
            return value;
        }

        if (value.startsWith("/")) {
            return BASE_URL + value;
        }

        return String.format("%s/%s/%s", BASE_URL, resourcePath, value);
    }
}