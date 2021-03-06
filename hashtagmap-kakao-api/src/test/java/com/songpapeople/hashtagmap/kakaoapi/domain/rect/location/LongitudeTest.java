package com.songpapeople.hashtagmap.kakaoapi.domain.rect.location;

import com.songpapeople.hashtagmap.kakaoapi.exception.KakaoApiException;
import com.songpapeople.hashtagmap.kakaoapi.exception.KakaoApiExceptionStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class LongitudeTest {
    @DisplayName("정상 범위로 생성했을 때")
    @ParameterizedTest
    @ValueSource(doubles = {124, 126, 132})
    public void longitudeConstructTest(double value) {
        assertThat(new Longitude(value)).isInstanceOf(Coordinate.class);
    }

    @DisplayName("정상 범위가 아닐 때")
    @ParameterizedTest
    @ValueSource(doubles = {123.9, 132.001})
    public void invalidLongitudeConstructTest(double value) {
        KakaoApiException exception = assertThrows(KakaoApiException.class,
                () -> new Longitude(value));
        assertEquals(exception.getErrorCode(), KakaoApiExceptionStatus.INVALID_LONGITUDE.getCode());
    }
}