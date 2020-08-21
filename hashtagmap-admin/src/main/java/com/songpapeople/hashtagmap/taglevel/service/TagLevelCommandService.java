package com.songpapeople.hashtagmap.taglevel.service;

import com.songpapeople.hashtagmap.instagram.domain.model.HashtagCounts;
import com.songpapeople.hashtagmap.instagram.domain.repository.InstagramRepository;
import com.songpapeople.hashtagmap.taglevel.model.TagLevels;
import com.songpapeople.hashtagmap.taglevel.repository.TagLevelRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class TagLevelCommandService {
    private final InstagramRepository instagramRepository;
    private final TagLevelRepository tagLevelRepository;

    @Transactional
    public void update() {
        TagLevels tagLevels = new TagLevels(tagLevelRepository.findAll());
        HashtagCounts hashtagCounts = new HashtagCounts(instagramRepository.findAllHashtagCountByOrderAsc());
        tagLevels.update(hashtagCounts);
    }
}
