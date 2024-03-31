package com.mju.mentoring.mission.ui.mission;

import static com.mju.mentoring.mission.fixture.MissionFixture.id_없는_미션_생성;

import com.mju.mentoring.mission.domain.mission.Mission;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MissionAcceptanceTest extends MissionAcceptanceTestFixture {

    private static final String 미션_전체_조회_url = "/missions";

    private Mission 미션;
    private String 토큰;

    @BeforeEach
    void setUp() {
        미션 = id_없는_미션_생성();
        토큰 = tokenManager.create(1L);
    }

    @Test
    void 미션_전체_조회_테스트() {
        // given
        도전자를_생성한다();
        var 저장_미션 = 미션을_저장한다(미션);

        // when
        var 조회_결과 = 모든_미션을_조회한다(토큰, 미션_전체_조회_url);

        // then
        여러_미션_검증(조회_결과, 저장_미션);
    }
}
