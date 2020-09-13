package com.songpapeople.hashtagmap.event.model;

import com.songpapeople.hashtagmap.place.domain.model.Category;
import com.songpapeople.hashtagmap.place.domain.model.Zone;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@DiscriminatorValue("KAKAO")
public class KakaoEventHistory extends EventHistory {
    public KakaoEventHistory(final Category category, final Zone zone, final EventStatus eventStatus) {
        super(eventStatus);
        this.category = category;
        this.zone = zone;
    }

    public static KakaoEventHistory ready(final Category category, final Zone zone) {
        return new KakaoEventHistory(category, zone, EventStatus.READY);
    }

    @Enumerated(EnumType.STRING)
    private Category category;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ZONE_ID", foreignKey = @ForeignKey(name = "FK_KAKAO_EVENT_ZONE"))
    private Zone zone;

    public void fail() {
        this.eventStatus = EventStatus.FAIL;
    }

    public void success() {
        this.eventStatus = EventStatus.SUCCESS;
    }
}
