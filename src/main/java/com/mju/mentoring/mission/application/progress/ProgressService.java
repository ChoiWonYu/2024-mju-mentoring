package com.mju.mentoring.mission.application.progress;

import com.mju.mentoring.global.domain.OperateType;
import com.mju.mentoring.global.domain.ResourceType;
import com.mju.mentoring.mission.application.progress.dto.ProgressResponse;
import com.mju.mentoring.mission.domain.progress.MissionProgress;
import com.mju.mentoring.mission.domain.progress.MissionProgressRepository;
import com.mju.mentoring.mission.domain.progress.ProgressStatus;
import com.mju.mentoring.mission.domain.progress.RewardStatus;
import com.mju.mentoring.mission.exception.exceptions.AlreadyChallengeMission;
import com.mju.mentoring.mission.infrastructure.progress.dto.CurrentProgress;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProgressService {

    private final MissionProgressRepository missionProgressRepository;

    @Transactional
    public void challengeMission(
        final Long challengerId, final Long missionId, final Long reward, final Long goal) {
        if (missionProgressRepository.hasChallengedMission(challengerId, missionId)) {
            throw new AlreadyChallengeMission();
        }

        missionProgressRepository.save(MissionProgress.of(goal, missionId, reward, challengerId));
    }

    @Transactional
    public void increaseTargetProgress(final Long challengerId, final OperateType getOperateType,
        final ResourceType resourceType) {
        missionProgressRepository.findByChallengeIdAndType(
            challengerId, getOperateType, resourceType).ifPresent(MissionProgress::increaseCount);
    }

    public List<ProgressResponse> findAll(
        final ProgressStatus progressStatus, final RewardStatus rewardStatus) {
        List<CurrentProgress> progress = missionProgressRepository.findAll(
            progressStatus, rewardStatus);
        return progress.stream()
            .map(CurrentProgress::toResponse)
            .toList();
    }
}
