package com.mju.mentoring.mission.infrastructure.mission;

import com.mju.mentoring.global.domain.OperateType;
import com.mju.mentoring.global.domain.ResourceType;
import com.mju.mentoring.mission.domain.mission.Mission;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MissionJpaRepository extends JpaRepository<Mission, Long> {

    Mission save(final Mission mission);

    List<Mission> findAll();

    Optional<Mission> findByOperateTypeAndTargetType(
        final OperateType operateType, final ResourceType resourceType);
}
