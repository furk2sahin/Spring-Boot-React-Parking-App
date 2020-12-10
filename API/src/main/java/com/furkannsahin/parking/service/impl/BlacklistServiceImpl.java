package com.furkannsahin.parking.service.impl;

import com.furkannsahin.parking.model.Blacklist;
import com.furkannsahin.parking.repository.BlacklistRepository;
import com.furkannsahin.parking.service.BlacklistService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BlacklistServiceImpl implements BlacklistService {

    private BlacklistRepository blacklistRepository;

    @Autowired
    public void setBlacklistRepository(BlacklistRepository blacklistRepository){
        this.blacklistRepository = blacklistRepository;
    }

    @Override
    public List<Blacklist> getBlacklist() {
        return blacklistRepository.findAll();
    }

    @Override
    public Blacklist createBlackListItem(Blacklist blacklist) {
        return blacklistRepository.save(blacklist);
    }

    @Override
    public void deleteBlacklistItem(Long id) {
        blacklistRepository.deleteById(id);
    }
}
