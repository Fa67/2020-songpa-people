package com.songpapeople.hashtagmap.instagram.domain.repository;

import com.songpapeople.hashtagmap.instagram.domain.model.Instagram;
import com.songpapeople.hashtagmap.place.domain.model.Category;
import com.songpapeople.hashtagmap.place.domain.model.Location;
import com.songpapeople.hashtagmap.place.domain.model.Place;
import com.songpapeople.hashtagmap.place.domain.model.Point;
import com.songpapeople.hashtagmap.place.domain.repository.PlaceRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class InstagramRepositoryCustomImplTest {
    private static final String ROAD_ADDRESS_NAME = "서울시 송파구";

    @Autowired
    private InstagramRepository instagramRepository;

    @Autowired
    private PlaceRepository placeRepository;

    @DisplayName("인스타그램 도메인 페치조인으로 가져오기")
    @Test
    void findAllFetch() {
        Place place = Place.builder()
                .id(1L)
                .kakaoId("1234")
                .category(Category.CAFE)
                .location(new Location(new Point("33", "127"), ROAD_ADDRESS_NAME))
                .placeName("starbucks")
                .build();
        place = placeRepository.save(place);

        Instagram instagram = Instagram.builder()
                .place(place)
                .hashtagCount(123L)
                .hashtagName("스타벅스")
                .build();
        instagramRepository.save(instagram);

        List<Instagram> instagrams = instagramRepository.findAllFetch();

        assertThat(instagrams.size()).isEqualTo(1);
    }

    @DisplayName("Hashtag 개수를 오름차순으로 정렬한다.")
    @Test
    void name() {
        // given
        List<Long> hashtagCounts = Arrays.asList(3L, 2L, 1L);

        List<Instagram> instagrams = new ArrayList<>();
        for (int i = 0; i < hashtagCounts.size(); i++) {
            instagrams.add(Instagram.builder()
                    .hashtagCount(hashtagCounts.get(i))
                    .build());
        }
        instagramRepository.saveAll(instagrams);

        // when
        List<Long> actaul = instagramRepository.findAllHashtagCountByOrderAsc();

        // then
        Assertions.assertEquals(actaul, Arrays.asList(1L, 2L, 3L));
    }

    @AfterEach
    void tearDown() {
        instagramRepository.deleteAll();
        placeRepository.deleteAll();
    }
}
