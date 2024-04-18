package com.mju.mentoring.mission.infrastructure.progress;

import static com.mju.mentoring.mission.fixture.MissionFixture.id_없는_미션_생성;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

import com.mju.mentoring.global.DatabaseCleaner;
import com.mju.mentoring.global.config.TestQuerydslConfig;
import com.mju.mentoring.global.domain.OperateType;
import com.mju.mentoring.global.domain.ResourceType;
import com.mju.mentoring.mission.domain.progress.MissionProgress;
import com.mju.mentoring.mission.infrastructure.mission.MissionJpaRepository;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

@DataJpaTest
@DatabaseCleaner
@Import(TestQuerydslConfig.class)
class MissionProgressQueryDslRepositoryTest {

    private final static Long DEFAULT_MISSION_ID = 1L;
    private final static Long DEFAULT_CHALLENGER_ID = 1L;
    private final static Long DEFAULT_GOAL = 5L;
    private static final ResourceType DEFAULT_RESOURCE_TYPE = ResourceType.BOARD;
    private static final OperateType DEFAULT_OPERATE_TYPE = OperateType.CREATE;

    @Autowired
    private MissionJpaRepository missionRepository;

    @Autowired
    private MissionProgressJpaRepository missionProgressJpaRepository;

    @Autowired
    private MissionProgressQueryDslRepository queryDslRepository;

    @Test
    void 도전자와_미션_타입으로_미션_진행도_조회_테스트() {
        // given
        missionRepository.save(id_없는_미션_생성());
        missionProgressJpaRepository.save(
            MissionProgress.of(DEFAULT_GOAL, DEFAULT_MISSION_ID, DEFAULT_CHALLENGER_ID));

        // when
        Optional<MissionProgress> progress = queryDslRepository.findByChallengeIdAndType(
            DEFAULT_MISSION_ID, DEFAULT_OPERATE_TYPE, DEFAULT_RESOURCE_TYPE);

        // then
        assertThat(progress).isNotEmpty();
    }

    @Test
    void 도전자가_이미_미션을_수행하고_있는지_조회_테스트() {
        // given
        missionProgressJpaRepository.save(
            MissionProgress.of(DEFAULT_GOAL, DEFAULT_MISSION_ID, DEFAULT_CHALLENGER_ID));

        // when & then
        assertSoftly(softly -> {
            softly.assertThat(queryDslRepository.hasAlreadyChallengedMission(
                    DEFAULT_CHALLENGER_ID, DEFAULT_MISSION_ID))
                .isTrue();
            softly.assertThat(queryDslRepository.hasAlreadyChallengedMission(
                    2L, 2L))
                .isFalse();
        });
    }
}
