package com.mju.mentoring.mission.domain.progress;

import static com.mju.mentoring.mission.domain.progress.RewardStatus.RECEIVED;
import static com.mju.mentoring.mission.domain.progress.RewardStatus.WAITING;

import com.mju.mentoring.mission.exception.exceptions.CannotReceiveRewardException;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class CurrentInfo {

    private static final Long INITIAL_CURRENT_COUNT = 0L;

    private RewardStatus rewardStatus;
    private Long currentCount;
    private ProgressInfo progressInfo;

    public static CurrentInfo initProgressInfo(final Long goal) {
        return new CurrentInfo(
            RewardStatus.NOT_AVAILABLE, INITIAL_CURRENT_COUNT, ProgressInfo.createDefault(goal));
    }

    public boolean canReceiveReward() {
        return rewardStatus.canReceive();
    }

    public void increaseCount() {
        currentCount++;
        progressInfo.synchronizeProgress(currentCount);

        if (progressInfo.isSatisfiedGoal()) {
            readyToReceiveReward();
        }
    }

    public void receiveReward() {
        if (!canReceiveReward()) {
            throw new CannotReceiveRewardException();
        }
        rewardStatus = RECEIVED;
    }

    private void readyToReceiveReward() {
        rewardStatus = WAITING;
    }
}
