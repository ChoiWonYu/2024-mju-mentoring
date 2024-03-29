package com.mju.mentoring.member.application.member;

import com.mju.mentoring.board.domain.BoardHistoryEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MemberEventHandler {

    private final MemberService memberService;

    @EventListener
    public void saveBoardHistory(final BoardHistoryEvent boardSaveEvent) {
        memberService.createBoardHistory(boardSaveEvent.getMemberId(),
            boardSaveEvent.getBoardId(), boardSaveEvent.getHistoryType());
    }
}
