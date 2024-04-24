package com.mju.mentoring.mission.domain.progress;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class ProgressInfoTest {

    private static final Long DEFAULT_GOAL = 5L;

    @Test
    void 진행도_동기화_테스트() {
        // given
        ProgressInfo progress = createDefaultProgress();

        // when
        progress.synchronizeProgress(1L);

        // then
        assertThat(progress.getProgress())
            .isEqualTo(20);
    }

    @Test
    void 진행도_100_초과_테스트() {
        // given
        ProgressInfo progress = createDefaultProgress();

        // when
        progress.synchronizeProgress(DEFAULT_GOAL + 1L);

        // then
        assertThat(progress.getProgress())
            .isEqualTo(100);
    }

    @Test
    void 진행도_100되면_진행_상태_변경_테스트() {
        // given
        ProgressInfo progress = createDefaultProgress();

        // when
        progress.synchronizeProgress(DEFAULT_GOAL);

        // then
        assertThat(progress.isSatisfiedGoal())
            .isTrue();
    }

    private static ProgressInfo createDefaultProgress() {
        return ProgressInfo.createDefault(DEFAULT_GOAL);
    }
}
