package com.mju.mentoring.mission.domain.progress;

import static com.mju.mentoring.mission.domain.progress.ProgressStatus.COMPLETED;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ProgressInfo {

    private static final Double INITIAL_PROGRESS = 0.0;
    private static final Double PERCENTAGE_MULTIPLIER = 100.0;
    private static final Double MAX_PROGRESS = 100.0;

    private ProgressStatus progressStatus;
    private Long goal;
    private Double progress;

    public static ProgressInfo createDefault(final Long goal) {
        return new ProgressInfo(ProgressStatus.IN_PROGRESS, goal, INITIAL_PROGRESS);
    }

    public void synchronizeProgress(final Long currentCount) {
        progress = currentCount * PERCENTAGE_MULTIPLIER / goal;
        if (progress >= MAX_PROGRESS) {
            progress = MAX_PROGRESS;
            completeMission();
        }
    }

    public boolean isSatisfiedGoal() {
        return progressStatus.equals(COMPLETED);
    }

    private void completeMission() {
        progressStatus = COMPLETED;
    }
}
