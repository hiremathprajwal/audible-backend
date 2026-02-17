# Media Files Setup

## Folder Structure
```
src/main/resources/static/
├── images/
│   └── audiobooks/
│       ├── harry-potter-goblet-fire.jpg
│       ├── the-shining.jpg
│       ├── 5-second-rule.jpg
│       └── psychology-of-money.jpg
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

## How to Add Media Files

### Option 1: Download Sample Images
You can download book cover images from:
- Google Images (search for book covers)
- Amazon book pages
- Free stock photo sites (Unsplash, Pexels)

### Option 2: Use Placeholder Images
Create simple placeholder images with text using online tools:
- https://placeholder.com/
- https://via.placeholder.com/300x400?text=Book+Cover

### Option 3: Sample Audio Files
For testing, you can:
- Use any MP3 files you have
- Download royalty-free audio from:
  - https://freesound.org/
  - https://www.bensound.com/

## Accessing Files

Once files are placed in the folders, they will be accessible at:
- Images: `http://localhost:8080/images/audiobooks/harry-potter-goblet-fire.jpg`
- Audio: `http://localhost:8080/audio/audiobooks/harry-potter-goblet-fire.mp3`
- Clips: `http://localhost:8080/audio/clips/harry-potter-goblet-fire-clip.mp3`

## Frontend Integration

The frontend will fetch these URLs from the API and display/play them directly.
