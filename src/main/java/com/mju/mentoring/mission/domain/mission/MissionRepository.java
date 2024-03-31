package com.mju.mentoring.mission.domain.mission;

import com.mju.mentoring.global.domain.OperateType;
import com.mju.mentoring.global.domain.ResourceType;
import java.util.List;
import java.util.Optional;

public interface MissionRepository {

    Mission save(final Mission mission);

    List<Mission> findAll();

    Optional<Mission> findById(final Long id);

    Optional<Mission> findByOperateTypeAndResourceType(
        final OperateType operateType, final ResourceType resourceType);
}
