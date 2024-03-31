package com.mju.mentoring.mission.domain.mission;

import com.mju.mentoring.global.domain.OperateType;
import com.mju.mentoring.global.domain.ResourceType;
import java.util.List;
import java.util.Optional;

public interface MissionRepository {

    List<Mission> findAll();

    Optional<Mission> findByOperateTypeAndResourceType(
        final OperateType operateType, final ResourceType resourceType);
}
