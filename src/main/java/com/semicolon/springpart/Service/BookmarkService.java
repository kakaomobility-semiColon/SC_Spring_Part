package com.semicolon.springpart.Service;

import com.semicolon.springpart.entity.BookmarkEntity;
import com.semicolon.springpart.repository.BookmarkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookmarkService {

    private final BookmarkRepository bookmarkRepository;

    @Autowired
    public BookmarkService(BookmarkRepository bookmarkRepository) {
        this.bookmarkRepository = bookmarkRepository;
    }

    public BookmarkEntity addBookmark(String userId, String chargerId) {
        BookmarkEntity bookmark = new BookmarkEntity();
        bookmark.setUserId(userId);
        bookmark.setChargerId(chargerId);
        return bookmarkRepository.save(bookmark);
    }

    public List<BookmarkEntity> getBookmarksByUserId(String userId) {
        return bookmarkRepository.findByUserId(userId);
    }

    public void removeBookmark(String userId, String chargerId) {
        bookmarkRepository.deleteByUserIdAndChargerId(userId, chargerId);
    }
}
