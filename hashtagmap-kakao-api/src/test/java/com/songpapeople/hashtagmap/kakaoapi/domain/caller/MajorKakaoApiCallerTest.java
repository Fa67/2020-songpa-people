package com.songpapeople.hashtagmap.kakaoapi.domain.caller;

import com.songpapeople.hashtagmap.kakaoapi.domain.dto.KakaoPlaceDto;
import com.songpapeople.hashtagmap.kakaoapi.domain.rect.Rect;
import com.songpapeople.hashtagmap.kakaoapi.domain.rect.location.Latitude;
import com.songpapeople.hashtagmap.kakaoapi.domain.rect.location.Longitude;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class MajorKakaoApiCallerTest {
    @Autowired
    private KakaoApiCaller kakaoApiCaller;

    @Disabled
    @DisplayName("정의한 범위 내에서 카페 카테고리에 대한 Kakao API 호출")
    @Test
    public void KakaoPlaceCallerTest() {
        Rect rect = new Rect(new Latitude(37.569449), new Longitude(126.979533), 0.02);
        KakaoPlaceDto result = kakaoApiCaller.findPlaceByCategory("CE7", rect, 1);

        Integer totalCount = result.getMeta().getTotalCount();
        int documentsSize = result.getDocuments().size();
        String categoryGroupCode = result.getDocuments().get(0).getCategoryGroupCode();

        assertThat(result).isNotNull();
        assertThat(totalCount).isNotEqualTo(0);
        assertThat(documentsSize).isNotEqualTo(0);
        assertThat(categoryGroupCode).isEqualTo("CE7");
    }
}