package com.mju.mentoring.mission.application.progress;

import com.mju.mentoring.global.domain.OperateType;
import com.mju.mentoring.global.domain.ResourceType;
import com.mju.mentoring.mission.domain.progress.MissionProgress;
import com.mju.mentoring.mission.domain.progress.MissionProgressRepository;
import com.mju.mentoring.mission.exception.exceptions.AlreadyChallengeMission;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProgressService {

    private final MissionProgressRepository missionProgressRepository;

    @Transactional
    public void challengeMission(final Long challengerId, final Long missionId, final Long goal) {
        Optional<MissionProgress> missionProgress = missionProgressRepository.findByMissionIdAndChallengerId(
            challengerId, missionId);

        if (!missionProgress.isEmpty()) {
            throw new AlreadyChallengeMission();
        }

        missionProgressRepository.save(MissionProgress.of(goal, missionId, challengerId));
    }

    @Transactional
    public void increaseTargetProgress(final Long challengerId, final OperateType getOperateType,
        final ResourceType resourceType) {
        missionProgressRepository.findByChallengeIdAndType(
            challengerId, getOperateType, resourceType).ifPresent(MissionProgress::increaseCount);
    }
}
