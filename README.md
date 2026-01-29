# 🎌 AnimeExplorer

AnimeExplorer is a modern Android application built using **Kotlin** and **Jetpack Compose** that allows users to explore popular anime and view detailed information fetched from the **Jikan API (MyAnimeList unofficial API)**.

The project demonstrates **Clean Architecture with MVVM**, offline caching, and real-world API edge-case handling.

---

## 📱 Features

### 🏠 Anime List Screen
- Fetches **Top Anime** from Jikan API
- Displays:
  - Anime title
  - Poster image
  - MyAnimeList rating
  - Number of episodes
- Cached locally for **offline access**

### 📄 Anime Detail Screen
- Displays:
  - Title
  - Poster image
  - Rating
  - Episodes
  - Synopsis
  - Genres
- **Trailer support**:
  - Opens trailer in **YouTube app or browser**
  - Gracefully handles missing or restricted trailers
- Top app bar with **Back navigation**

### 📡 Offline Mode
- Anime list stored using **Room**
- Cached data shown when internet is unavailable

### ⚠️ Error Handling
- Loading states
- Network failure handling
- Null-safe parsing for unreliable API fields

---

## 🏗 Architecture

The app follows **Clean Architecture with MVVM**.


---

## 🧠 Tech Stack

- **Language**: Kotlin
- **UI**: Jetpack Compose (Material 3)
- **Architecture**: MVVM + Clean Architecture
- **Networking**: Retrofit + Gson
- **Local Database**: Room
- **State Management**: Coroutines + StateFlow
- **Navigation**: Navigation Compose
- **Image Loading**: Coil

---

## 🌐 API Used

**Jikan API (Unofficial MyAnimeList API)**

- Top Anime  https://api.jikan.moe/v4/top/anime
- Anime Deatils https://api.jikan.moe/v4/anime/{anime_id}


---

## 🎬 Trailer Handling (Design Decision)

- Jikan provides trailers inconsistently (`youtube_id`, `url`, `embed_url`)
- Many trailers **do not allow embedded playback**
- To ensure reliability:
- Trailers are opened using **YouTube watch URLs**
- Playback occurs in the **YouTube app or browser**

This mirrors real-world apps like **MyAnimeList** and **AniList**.

---

## ⚠️ Known Limitations

- Not all anime have trailers (API limitation)
- Some trailers are region-restricted
- Trailer playback is external (YouTube)
- Pagination is not implemented

---

👩‍💻 Author

Shubhanshi Shrivastava
Android Developer
Kotlin • Jetpack Compose • MVVM

📄 License

This project is for learning and demonstration purposes.

