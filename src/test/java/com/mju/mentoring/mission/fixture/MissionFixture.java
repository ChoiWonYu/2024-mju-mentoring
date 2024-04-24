package com.mju.mentoring.mission.fixture;

import com.mju.mentoring.global.domain.OperateType;
import com.mju.mentoring.global.domain.ResourceType;
import com.mju.mentoring.mission.domain.mission.Mission;

public class MissionFixture {

    public static Mission id_없는_미션_생성() {
        return Mission.builder()
            .title("title")
            .goal(5L)
            .reward(1000L)
            .targetType(ResourceType.BOARD)
            .operateType(OperateType.CREATE)
            .build();
    }
}
