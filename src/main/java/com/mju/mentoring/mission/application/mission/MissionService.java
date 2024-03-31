package com.mju.mentoring.mission.application.mission;

import com.mju.mentoring.global.domain.OperateType;
import com.mju.mentoring.global.domain.ResourceType;
import com.mju.mentoring.global.event.Events;
import com.mju.mentoring.mission.domain.mission.Mission;
import com.mju.mentoring.mission.domain.mission.MissionFoundEvent;
import com.mju.mentoring.mission.domain.mission.MissionRepository;
import com.mju.mentoring.mission.exception.exceptions.NotFoundMissionException;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MissionService {

    private final MissionRepository missionRepository;

    public List<Mission> findAll() {
        return missionRepository.findAll();
    }

    public void findTargetMission(final Long challengerId, final OperateType operateType,
        final ResourceType resourceType) {
        Optional<Mission> targetMission = missionRepository.findByOperateTypeAndResourceType(
            operateType, resourceType);

        if (targetMission.isEmpty()) {
            return;
        }

        Mission mission = targetMission.get();
        Events.raise(new MissionFoundEvent(mission.getId(), challengerId));
    }

    public void challengeMission(final Long challengerId, final Long missionId) {
        Optional<Mission> mission = missionRepository.findById(missionId);
        if (mission.isEmpty()) {
            throw new NotFoundMissionException(missionId);
        }
    }
}
