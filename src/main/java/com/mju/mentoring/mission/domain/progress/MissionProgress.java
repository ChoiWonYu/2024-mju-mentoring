package com.mju.mentoring.mission.domain.progress;

import com.mju.mentoring.global.domain.OperateType;
import com.mju.mentoring.global.domain.ResourceType;
import lombok.Getter;

@Getter
public class MissionProgress {

    private TargetInfo targetInfo;
    private CurrentInfo currentInfo;
    private Long missionId;
    private Long challengerId;

    private MissionProgress(final TargetInfo targetInfo, final CurrentInfo currentInfo,
        final Long missionId,
        final Long challengerId) {
        this.targetInfo = targetInfo;
        this.currentInfo = currentInfo;
        this.missionId = missionId;
        this.challengerId = challengerId;
    }

    public static MissionProgress of(
        final OperateType operateType, final ResourceType resourceType, final Long targetId,
        final Long goal, final Long missionId, final Long challengerId) {
        TargetInfo targetInfo = TargetInfo.of(targetId, operateType, resourceType);
        return MissionProgress.of(targetInfo, goal, missionId, challengerId);
    }

    public static MissionProgress of(final TargetInfo targetInfo, final Long goal,
        final Long missionId, final Long challengerId) {
        return new MissionProgress(
            targetInfo, CurrentInfo.initProgressInfo(goal), missionId, challengerId);
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
