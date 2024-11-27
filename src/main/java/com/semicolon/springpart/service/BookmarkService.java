package com.semicolon.springpart.service;

import com.semicolon.springpart.entity.Bookmark;
import com.semicolon.springpart.entity.ChargerApiEntity;
import com.semicolon.springpart.entity.User;
import com.semicolon.springpart.repository.BookmarkRepository;
import com.semicolon.springpart.repository.ChargerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BookmarkService {

    private final BookmarkRepository bookmarkRepository;
    private final ChargerRepository chargerRepository;

    public Bookmark addBookmark(User user, String chargerId) {
        ChargerApiEntity charger = chargerRepository.findById(chargerId)
                .orElseThrow(() -> new IllegalArgumentException("Charger not found"));
        Bookmark bookmark = Bookmark.builder()
                .user(user)
                .charger(charger)
                .build();
        return bookmarkRepository.save(bookmark);
    }

    public void removeBookmark(User user, Long bookmarkId) {
        Bookmark bookmark = bookmarkRepository.findById(bookmarkId)
                .orElseThrow(() -> new IllegalArgumentException("Bookmark not found"));
        if (!bookmark.getUser().equals(user)) {
            throw new IllegalStateException("Unauthorized access");
        }
        bookmarkRepository.delete(bookmark);
    }

    public List<Bookmark> getUserBookmarks(User user) {
        return bookmarkRepository.findByUserId(user.getId());
    }
}
