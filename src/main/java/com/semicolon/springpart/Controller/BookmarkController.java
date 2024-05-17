package com.semicolon.springpart.Controller;

import com.semicolon.springpart.Service.BookmarkService;
import com.semicolon.springpart.entity.BookmarkEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/bookmark")
public class BookmarkController {

    private final BookmarkService bookmarkService;

    public BookmarkController(BookmarkService bookmarkService) {
        this.bookmarkService = bookmarkService;
    }

    @PostMapping("/{chargerId}")
    public ResponseEntity<Map<String, Object>> addBookmark(@RequestParam String userId, @PathVariable String chargerId) {
        BookmarkEntity bookmark = bookmarkService.addBookmark(userId, chargerId);
        return createResponse(bookmark);
    }

    @GetMapping("")
    public ResponseEntity<Map<String, Object>> getBookmarks(@RequestParam String userId) {
        List<BookmarkEntity> bookmarks = bookmarkService.getBookmarksByUserId(userId);
        return createResponse(bookmarks);
    }

    @DeleteMapping("/{chargerId}")
    public ResponseEntity<Map<String, Object>> removeBookmark(@RequestParam String userId, @PathVariable String chargerId) {
        bookmarkService.removeBookmark(userId, chargerId);
        return createResponse(null);
    }

    private ResponseEntity<Map<String, Object>> createResponse(Object data) {
        Map<String, Object> response = new HashMap<>();
        response.put("timeStamp", LocalDateTime.now());
        response.put("status", HttpStatus.OK.value());
        response.put("message", "요청이 성공했습니다.");
        response.put("result", data);
        response.put("success", true);
        return ResponseEntity.ok().body(response);
    }
}