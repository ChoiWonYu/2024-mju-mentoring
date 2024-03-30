package com.mju.mentoring.mission.domain.progress;

import com.mju.mentoring.global.domain.OperateType;
import com.mju.mentoring.global.domain.ResourceType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class TargetInfo {

    private Long targetId;
    private OperateType operateType;
    private ResourceType resourceType;

    public static TargetInfo of(
        final Long targetId, final OperateType operateType, final ResourceType resourceType) {
        return new TargetInfo(targetId, operateType, resourceType);
    }
}
