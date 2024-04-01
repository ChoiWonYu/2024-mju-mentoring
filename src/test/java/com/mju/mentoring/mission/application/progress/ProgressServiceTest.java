package com.mju.mentoring.mission.application.progress;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

import com.mju.mentoring.mission.domain.progress.MissionProgress;
import com.mju.mentoring.mission.domain.progress.ProgressRepository;
import com.mju.mentoring.mission.exception.exceptions.AlreadyChallengeMission;
import com.mju.mentoring.mission.fake.FakeProgressRepository;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ProgressServiceTest {

    private static final Long DEFAULT_CHALLENGER_ID = 1L;
    private static final Long DEFAULT_MISSION_ID = 1L;
    private static final Long DEFAULT_GOAL = 5L;

    private ProgressService progressService;
    private ProgressRepository progressRepository;

    @BeforeEach
    void setUp() {
        progressRepository = new FakeProgressRepository();
        progressService = new ProgressService(progressRepository);
    }

    @Test
    void 미션_신청_테스트() {
        // given

        // when
        progressService.challengeMission(DEFAULT_CHALLENGER_ID, DEFAULT_MISSION_ID, DEFAULT_GOAL);
        Optional<MissionProgress> progress = progressRepository.findById(1L);

        // then
        assertThat(progress).isNotEmpty();
    }

    @Test
    void 이미_신청_중인_미션_예외_테스트() {
        // given
        progressService.challengeMission(DEFAULT_CHALLENGER_ID, DEFAULT_MISSION_ID, DEFAULT_GOAL);

        // when & then
        assertThatThrownBy(
            () -> progressService.challengeMission(
                DEFAULT_CHALLENGER_ID, DEFAULT_MISSION_ID, DEFAULT_GOAL))
            .isInstanceOf(AlreadyChallengeMission.class);
    }

    @Test
    void 진행도_증가_테스트() {
        // given
        progressService.challengeMission(DEFAULT_CHALLENGER_ID, DEFAULT_MISSION_ID, DEFAULT_GOAL);

        // when
        progressService.increaseTargetProgress(DEFAULT_CHALLENGER_ID, DEFAULT_MISSION_ID);
        Optional<MissionProgress> progress = progressRepository.findByMissionIdAndChallengerId(
            DEFAULT_MISSION_ID, DEFAULT_CHALLENGER_ID);

        // then
        assertSoftly(softly -> {
            softly.assertThat(progress).isNotEmpty();
            testProgressIncrease(progress);
        });
    }

    private void testProgressIncrease(final Optional<MissionProgress> progress) {
        MissionProgress target = progress.get();
        assertSoftly(softly -> {
            softly.assertThat(target.getCurrentInfo().getCurrentCount()).isEqualTo(1L);
            softly.assertThat(target.getCurrentInfo().getProgressInfo().getProgress())
                .isEqualTo(20);
        });
    }

}
