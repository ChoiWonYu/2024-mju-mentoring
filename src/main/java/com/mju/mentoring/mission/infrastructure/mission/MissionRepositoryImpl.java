package com.mju.mentoring.mission.infrastructure.mission;

import com.mju.mentoring.global.domain.OperateType;
import com.mju.mentoring.global.domain.ResourceType;
import com.mju.mentoring.mission.domain.mission.Mission;
import com.mju.mentoring.mission.domain.mission.MissionRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MissionRepositoryImpl implements MissionRepository {

    private final MissionJpaRepository missionJpaRepository;

    @Override
    public Mission save(final Mission mission) {
        return missionJpaRepository.save(mission);
    }

    @Override
    public List<Mission> findAll() {
        return missionJpaRepository.findAll();
    }

    @Override
    public Optional<Mission> findByOperateTypeAndResourceType(final OperateType operateType,
        final ResourceType resourceType) {
        return missionJpaRepository.findByOperateTypeAndTargetType(operateType, resourceType);
    }
}
