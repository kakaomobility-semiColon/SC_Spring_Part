package com.semicolon.springpart;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CacheType {

    CHARGERS_IN_AREA("chargersInArea"),
    CHARGERS_BY_NAME_OR_ADDRESS("chargersByNameOrAddress");

    public final String cacheName;


}
