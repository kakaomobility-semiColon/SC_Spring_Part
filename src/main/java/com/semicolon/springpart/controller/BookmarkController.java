package com.semicolon.springpart.controller;

import com.semicolon.springpart.service.BookmarkService;
import com.semicolon.springpart.entity.BookmarkEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/bookmark")
public class BookmarkController {

    private final BookmarkService bookmarkService;

    public BookmarkController(BookmarkService bookmarkService) {
        this.bookmarkService = bookmarkService;
    }

    @PostMapping("/{chargerId}")
    public ResponseEntity<Map<String, Object>> addBookmark(HttpServletRequest request, HttpServletResponse response, @PathVariable String chargerId) {
        String userId = getUserIdFromCookie(request);
        BookmarkEntity bookmark = bookmarkService.addBookmark(userId, chargerId);
        addUserIdCookie(response, userId);
        return createResponse(bookmark);
    }

    @GetMapping("")
    public ResponseEntity<Map<String, Object>> getBookmarks(HttpServletRequest request) {
        String userId = getUserIdFromCookie(request);
        List<BookmarkEntity> bookmarks = bookmarkService.getBookmarksByUserId(userId);
        return createResponse(bookmarks);
    }

    @DeleteMapping("/{chargerId}")
    public ResponseEntity<Map<String, Object>> removeBookmark(HttpServletRequest request, HttpServletResponse response, @PathVariable String chargerId) {
        String userId = getUserIdFromCookie(request);
        bookmarkService.removeBookmark(userId, chargerId);
        removeUserIdCookie(response);
        return createResponse(null);
    }

    private String getUserIdFromCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("user-id".equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        String userId = UUID.randomUUID().toString();
        return userId;
    }

    private void addUserIdCookie(HttpServletResponse response, String userId) {
        Cookie cookie = new Cookie("user-id", userId);
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    private void removeUserIdCookie(HttpServletResponse response) {
        Cookie cookie = new Cookie("user-id", "");
        cookie.setMaxAge(0); // 쿠키 제거
        cookie.setPath("/");
        response.addCookie(cookie);
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
