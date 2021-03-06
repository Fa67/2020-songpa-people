package com.songpapeople.hashtagmap.instagram.api;

import com.songpapeople.hashtagmap.response.CustomResponse;
import com.songpapeople.hashtagmap.service.InstagramService;
import com.songpapeople.hashtagmap.taglevel.service.TagLevelCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/instagram-scheduler")
public class InstagramSchedulerApiController {
    private final InstagramService instagramService;
    private final TagLevelCommandService tagLevelCommandService;

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public CustomResponse<Void> update() {
        instagramService.update();
        tagLevelCommandService.update();
        return CustomResponse.empty();
    }
}
