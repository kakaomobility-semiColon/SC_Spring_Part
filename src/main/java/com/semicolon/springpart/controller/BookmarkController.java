package com.semicolon.springpart.controller;

import com.semicolon.springpart.entity.Bookmark;
import com.semicolon.springpart.entity.User;
import com.semicolon.springpart.service.BookmarkService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/bookmarks")
public class BookmarkController {

    private final BookmarkService bookmarkService;

    @PostMapping("/{chargerId}")
    public Bookmark addBookmark(
            @AuthenticationPrincipal User user,
            @PathVariable String chargerId) {
        return bookmarkService.addBookmark(user, chargerId);
    }

    @DeleteMapping("/{bookmarkId}")
    public void removeBookmark(
            @AuthenticationPrincipal User user,
            @PathVariable Long bookmarkId) {
        bookmarkService.removeBookmark(user, bookmarkId);
    }

    @GetMapping
    public List<Bookmark> getUserBookmarks(@AuthenticationPrincipal User user) {
        return bookmarkService.getUserBookmarks(user);
    }
}
