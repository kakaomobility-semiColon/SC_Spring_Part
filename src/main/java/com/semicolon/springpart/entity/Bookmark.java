package com.semicolon.springpart.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "bookmarks")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Bookmark {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "charger_id", nullable = false)
    private ChargerApiEntity charger;

    @Builder
    public Bookmark(User user, ChargerApiEntity charger) {
        this.user = user;
        this.charger = charger;
    }
}
