package com.mju.mentoring.mission.domain.progress;

import lombok.Getter;

@Getter
public class MissionProgress {

    private CurrentInfo currentInfo;
    private Long missionId;
    private Long challengerId;

    private MissionProgress(final CurrentInfo currentInfo, final Long missionId,
        final Long challengerId) {
        this.currentInfo = currentInfo;
        this.missionId = missionId;
        this.challengerId = challengerId;
    }

    public static MissionProgress of(final Long goal, final Long missionId, final Long challengerId) {
        return new MissionProgress(CurrentInfo.initProgressInfo(goal), missionId, challengerId);
    }

    public void increaseCount() {
        currentInfo.increaseCount();
    }

    public boolean canReceiveReward() {
        return currentInfo.canReceiveReward();
    }

    public void receiveReward() {
        currentInfo.receiveReward();
    }
}
