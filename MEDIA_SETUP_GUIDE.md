# Media Files Setup Guide

## Quick Setup

### 1. Download Sample Images

Use these free resources to download book cover images:

**Harry Potter and the Goblet of Fire:**
- Search: "Harry Potter Goblet of Fire book cover" on Google Images
- Or use placeholder: https://via.placeholder.com/300x400/1a237e/ffffff?text=Harry+Potter+4

**The Shining:**
- Search: "The Shining Stephen King book cover"
- Or use placeholder: https://via.placeholder.com/300x400/8b0000/ffffff?text=The+Shining

**The 5 Second Rule:**
- Search: "5 Second Rule Mel Robbins book cover"
- Or use placeholder: https://via.placeholder.com/300x400/ff6f00/ffffff?text=5+Second+Rule

**The Psychology of Money:**
- Search: "Psychology of Money book cover"
- Or use placeholder: https://via.placeholder.com/300x400/2e7d32/ffffff?text=Psychology+of+Money

### 2. Download Sample Audio Files

For testing purposes, you can use any MP3 files or download from:
- **Free Music Archive**: https://freemusicarchive.org/
- **Bensound**: https://www.bensound.com/ (royalty-free)
- **YouTube Audio Library**: https://www.youtube.com/audiolibrary/music

For clips, just use shorter versions (30 seconds) of the same audio files.

### 3. File Placement

Save files to these locations:

```
audible-backend/src/main/resources/static/
├── images/audiobooks/
│   ├── harry-potter-goblet-fire.jpg
│   ├── the-shining.jpg
│   ├── 5-second-rule.jpg
│   └── psychology-of-money.jpg
└── audio/
    ├── audiobooks/
    │   ├── harry-potter-goblet-fire.mp3
    │   ├── the-shining.mp3
    │   ├── 5-second-rule.mp3
    │   └── psychology-of-money.mp3
    └── clips/
        ├── harry-potter-goblet-fire-clip.mp3
        ├── the-shining-clip.mp3
        ├── 5-second-rule-clip.mp3
        └── psychology-of-money-clip.mp3
```

## Testing

### Backend URLs
After starting the backend (`mvn spring-boot:run`), files will be accessible at:
- `http://localhost:8080/images/audiobooks/harry-potter-goblet-fire.jpg`
- `http://localhost:8080/audio/audiobooks/harry-potter-goblet-fire.mp3`
- `http://localhost:8080/audio/clips/harry-potter-goblet-fire-clip.mp3`

### Frontend Integration
The frontend will automatically fetch these URLs from the API response and display/play them.

## Alternative: Use Online URLs

If you don't want to download files, you can modify the database to use online URLs:

```sql
UPDATE audiobooks SET 
  cover_image = 'https://example.com/book-cover.jpg',
  audio_file = 'https://example.com/audiobook.mp3',
  short_clip = 'https://example.com/clip.mp3'
WHERE audio_id = 1;
```

## Troubleshooting

**Files not loading?**
1. Check file names match exactly (case-sensitive)
2. Ensure files are in correct folders
3. Restart Spring Boot server
4. Check browser console for 404 errors

**CORS issues?**
- WebConfig.java is already configured to allow localhost:3000
