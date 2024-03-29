package com.mju.mentoring.board.domain;

import com.mju.mentoring.global.domain.HistoryType;
import com.mju.mentoring.global.event.Event;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class BoardHistoryEvent extends Event {

    private final Long memberId;
    private final Long boardId;
    private final HistoryType historyType;

    public static BoardHistoryEvent of(final Long writerId, final Long boardId,
        final HistoryType historyType) {
        return new BoardHistoryEvent(writerId, boardId, historyType);
    }
}
