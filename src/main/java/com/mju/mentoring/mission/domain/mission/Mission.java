package com.mju.mentoring.mission.domain.mission;

import com.mju.mentoring.global.domain.OperateType;
import com.mju.mentoring.global.domain.ResourceType;

public class Mission {

    private String title;
    private ResourceType targetType;
    private OperateType operateType;
    private Long goal;
    private Long reward;
}
