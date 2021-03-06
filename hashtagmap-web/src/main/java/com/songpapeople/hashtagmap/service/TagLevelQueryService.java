package com.songpapeople.hashtagmap.service;

import com.songpapeople.hashtagmap.dto.TagLevelResponse;
import com.songpapeople.hashtagmap.taglevel.model.TagLevels;
import com.songpapeople.hashtagmap.taglevel.repository.TagLevelQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class TagLevelQueryService {
    private final TagLevelQueryRepository tagLevelQueryRepository;

    public List<TagLevelResponse> findTagLevels() {
        TagLevels tagLevels = new TagLevels(tagLevelQueryRepository.findFiveByModifiedDateOrderById());
        return TagLevelResponse.of(tagLevels);
    }
}
