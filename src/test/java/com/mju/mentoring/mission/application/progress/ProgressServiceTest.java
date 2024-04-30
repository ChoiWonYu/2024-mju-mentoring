package com.mju.mentoring.mission.application.progress;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.mju.mentoring.mission.domain.progress.MissionProgress;
import com.mju.mentoring.mission.domain.progress.MissionProgressRepository;
import com.mju.mentoring.mission.exception.exceptions.AlreadyChallengeMission;
import com.mju.mentoring.mission.fake.FakeMissionProgressRepository;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ProgressServiceTest {

    private static final Long DEFAULT_CHALLENGER_ID = 1L;
    private static final Long DEFAULT_MISSION_ID = 1L;
    private static final Long DEFAULT_REWARD = 1000L;
    private static final Long DEFAULT_GOAL = 5L;

    private ProgressService progressService;
    private MissionProgressRepository missionProgressRepository;

    @BeforeEach
    void setUp() {
        missionProgressRepository = new FakeMissionProgressRepository();
        progressService = new ProgressService(missionProgressRepository);
    }

    @Test
    void 미션_신청_테스트() {
        // given

        // when
        progressService.challengeMission(DEFAULT_CHALLENGER_ID, DEFAULT_MISSION_ID, DEFAULT_REWARD,
            DEFAULT_GOAL);
        Optional<MissionProgress> progress = missionProgressRepository.findById(1L);

        // then
        assertThat(progress).isNotEmpty();
    }

    @Test
    void 이미_신청_중인_미션_예외_테스트() {
        // given
        progressService.challengeMission(
            DEFAULT_CHALLENGER_ID, DEFAULT_MISSION_ID, DEFAULT_REWARD, DEFAULT_GOAL);

        // when & then
        assertThatThrownBy(
            () -> progressService.challengeMission(
                DEFAULT_CHALLENGER_ID, DEFAULT_MISSION_ID, DEFAULT_REWARD, DEFAULT_GOAL))
            .isInstanceOf(AlreadyChallengeMission.class);
    }
}
