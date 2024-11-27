package org.example.springpart.controller;

import com.semicolon.springpart.SpringPartApplication;
import com.semicolon.springpart.controller.BookmarkController;
import com.semicolon.springpart.entity.Bookmark;
import com.semicolon.springpart.entity.ChargerApiEntity;
import com.semicolon.springpart.entity.User;
import com.semicolon.springpart.service.BookmarkService;
import com.semicolon.springpart.service.UserDetailService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = SpringPartApplication.class)
class BookmarkControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookmarkService bookmarkService; // 의존성 모의(MockBean) 주입

    @MockBean
    private UserDetailService userDetailService; // 필요한 서비스 추가

    @Test
    void addBookmark_success() throws Exception {
        User user = User.builder()
                .email("test@example.com")
                .password("password")
                .build();

        ChargerApiEntity charger = ChargerApiEntity.builder()
                .stationChargerId("CHARGER_1")
                .build();

        Bookmark bookmark = Bookmark.builder()
                .user(user)
                .charger(charger)
                .build();

        Mockito.when(bookmarkService.addBookmark(Mockito.any(User.class), Mockito.eq("CHARGER_1"))).thenReturn(bookmark);

        mockMvc.perform(post("/bookmarks/CHARGER_1")
                        .with(user("test@example.com").password("password")) // 인증된 사용자 주입
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.charger.stationChargerId").value("CHARGER_1"));
    }

    @Test
    void getUserBookmarks_success() throws Exception {
        User user = User.builder()
                .email("test@example.com")
                .password("password")
                .build();

        List<Bookmark> bookmarks = List.of(
                Bookmark.builder().user(user).build(),
                Bookmark.builder().user(user).build()
        );

        Mockito.when(bookmarkService.getUserBookmarks(Mockito.any(User.class))).thenReturn(bookmarks);

        mockMvc.perform(get("/bookmarks")
                        .with(user("test@example.com").password("password")) // 인증된 사용자 주입
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }
}
