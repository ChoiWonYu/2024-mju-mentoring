package com.mju.mentoring.mission.domain.progress;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class MissionProgress {

    private Long id;
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
