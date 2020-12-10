package com.furkannsahin.parking.service;

import com.furkannsahin.parking.model.Blacklist;

import java.util.List;

public interface BlacklistService {
    List<Blacklist> getBlacklist();
    Blacklist createBlackListItem(Blacklist blacklist);
    void deleteBlacklistItem(Long id);
}
