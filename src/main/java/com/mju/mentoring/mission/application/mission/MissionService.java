package com.mju.mentoring.mission.application.mission;

import com.mju.mentoring.global.domain.OperateType;
import com.mju.mentoring.global.domain.ResourceType;
import com.mju.mentoring.global.event.Events;
import com.mju.mentoring.mission.domain.mission.ChallengeMissionEvent;
import com.mju.mentoring.mission.domain.mission.Mission;
import com.mju.mentoring.mission.domain.mission.TargetOperateEvent;
import com.mju.mentoring.mission.domain.mission.MissionRepository;
import com.mju.mentoring.mission.exception.exceptions.NotFoundMissionException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MissionService {

    private final MissionRepository missionRepository;

    public List<Mission> findAll() {
        return missionRepository.findAll();
    }

    public void findTargetMission(final Long challengerId, final OperateType operateType,
        final ResourceType resourceType) {
        missionRepository.findByOperateTypeAndResourceType(operateType, resourceType)
            .ifPresent(
                mission -> Events.raise(new TargetOperateEvent(mission.getId(), challengerId)));
    }

    public void challengeMission(final Long challengerId, final Long missionId) {
        missionRepository.findById(missionId)
            .ifPresentOrElse(mission -> Events.raise(
                    new ChallengeMissionEvent(challengerId, missionId, mission.getGoal())),
                () -> {
                    throw new NotFoundMissionException(missionId);
                });
    }
}
