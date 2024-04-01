package com.mju.mentoring.mission.application.progress;

import com.mju.mentoring.mission.domain.mission.ChallengeMissionEvent;
import com.mju.mentoring.mission.domain.mission.TargetOperateEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProgressEventHandler {

    private final ProgressService progressService;

    @EventListener
    public void challengeMission(final ChallengeMissionEvent event) {
        progressService.challengeMission(
            event.getChallengerId(), event.getMissionId(), event.getGoal());
    }

    @EventListener
    public void targetOperate(final TargetOperateEvent event) {
        progressService.increaseTargetProgress(event.getChallengerId(), event.getMissionId());
    }
}
