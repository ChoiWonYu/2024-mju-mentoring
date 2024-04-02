package com.mju.mentoring.mission.application.progress;

import com.mju.mentoring.global.domain.OperateType;
import com.mju.mentoring.global.domain.ResourceType;
import com.mju.mentoring.mission.domain.progress.MissionProgress;
import com.mju.mentoring.mission.domain.progress.ProgressRepository;
import com.mju.mentoring.mission.exception.exceptions.AlreadyChallengeMission;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProgressService {

    private final ProgressRepository progressRepository;

    @Transactional
    public void challengeMission(final Long challengerId, final Long missionId, final Long goal) {
        Optional<MissionProgress> missionProgress = progressRepository.findByMissionIdAndChallengerId(
            challengerId, missionId);

        if (!missionProgress.isEmpty()) {
            throw new AlreadyChallengeMission();
        }

        progressRepository.save(MissionProgress.of(goal, missionId, challengerId));
    }

    @Transactional
    public void increaseTargetProgress(final Long challengerId, final OperateType getOperateType,
        final ResourceType resourceType) {
        progressRepository.findByChallengeIdAndType(
            challengerId, getOperateType, resourceType).ifPresent(MissionProgress::increaseCount);
    }
}
