package com.mju.mentoring.board.domain;

import com.mju.mentoring.global.domain.OperateType;
import com.mju.mentoring.global.domain.ResourceType;
import com.mju.mentoring.global.event.Event;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class BoardOperateEvent extends Event {

    private final Long memberId;
    private final Long boardId;
    private final OperateType operateType;
    private final ResourceType resourceType;

    public static BoardOperateEvent of(
        final Long writerId, final Long boardId, final OperateType operateType) {
        return new BoardOperateEvent(writerId, boardId, operateType, ResourceType.BOARD);
    }
}
