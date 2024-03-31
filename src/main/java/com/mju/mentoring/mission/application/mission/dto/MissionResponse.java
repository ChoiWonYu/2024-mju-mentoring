package com.mju.mentoring.mission.application.mission.dto;

import com.mju.mentoring.global.domain.OperateType;
import com.mju.mentoring.global.domain.ResourceType;
import com.mju.mentoring.mission.domain.mission.Mission;

public record MissionResponse(String title, ResourceType resourceType, OperateType operateType,
                              Long goal, Long reward) {

    public static MissionResponse from(final Mission mission) {
        return new MissionResponse(mission.getTitle(), mission.getTargetType(),
            mission.getOperateType(), mission.getGoal(), mission.getReward());
    }
}
