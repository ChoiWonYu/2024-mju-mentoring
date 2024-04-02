package com.mju.mentoring.mission.infrastructure.progress;

import static com.mju.mentoring.mission.fixture.MissionFixture.id_없는_미션_생성;
import static org.assertj.core.api.Assertions.assertThat;

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
class ProgressQueryDslRepositoryTest {

    private final static Long DEFAULT_MISSION_ID = 1L;
    private final static Long DEFAULT_CHALLENGER_ID = 1L;
    private final static Long DEFAULT_GOAL = 5L;
    private static final ResourceType DEFAULT_RESOURCE_TYPE = ResourceType.BOARD;
    private static final OperateType DEFAULT_OPERATE_TYPE = OperateType.CREATE;

    @Autowired
    private MissionJpaRepository missionRepository;

    @Autowired
    private ProgressJpaRepository progressJpaRepository;

    @Autowired
    private ProgressQueryDslRepository queryDslRepository;

    @Test
    void when_() {
        // given
        missionRepository.save(id_없는_미션_생성());
        progressJpaRepository.save(
            MissionProgress.of(DEFAULT_GOAL, DEFAULT_MISSION_ID, DEFAULT_CHALLENGER_ID));

        // when
        Optional<MissionProgress> progress = queryDslRepository.findByChallengeIdAndType(
            DEFAULT_MISSION_ID, DEFAULT_OPERATE_TYPE, DEFAULT_RESOURCE_TYPE);

        // then
        assertThat(progress).isNotEmpty();
    }
}
