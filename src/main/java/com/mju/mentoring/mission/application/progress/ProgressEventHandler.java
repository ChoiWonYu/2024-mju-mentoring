package com.mju.mentoring.mission.application.progress;

import com.mju.mentoring.board.domain.BoardOperateEvent;
import com.mju.mentoring.mission.domain.mission.ChallengeMissionEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@RequiredArgsConstructor
public class ProgressEventHandler {

    private final ProgressService progressService;

    @EventListener
    @Async
    public void challengeMission(final ChallengeMissionEvent event) {
        progressService.challengeMission(
            event.getChallengerId(), event.getMissionId(), event.getGoal());
    }

    @TransactionalEventListener(
        classes = BoardOperateEvent.class,
        phase = TransactionPhase.AFTER_COMMIT
    )
    @Async
    public void targetOperate(final BoardOperateEvent event) {
        progressService.increaseTargetProgress(
            event.getMemberId(), event.getOperateType(), event.getResourceType());
    }
}
