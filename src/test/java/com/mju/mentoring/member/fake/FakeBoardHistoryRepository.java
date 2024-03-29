package com.mju.mentoring.member.fake;

import com.mju.mentoring.member.domain.BoardHistory;
import com.mju.mentoring.member.domain.BoardHistoryRepository;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class FakeBoardHistoryRepository implements BoardHistoryRepository {

    Map<Long, BoardHistory> db = new HashMap<>();

    private Long id = 1L;

    @Override
    public BoardHistory save(final BoardHistory boardHistory) {
        db.put(id, BoardHistory.builder()
            .id(id)
            .historyType(boardHistory.getHistoryType())
            .ownerId(boardHistory.getOwnerId())
            .targetId(boardHistory.getTargetId())
            .createdAt(LocalDateTime.now())
            .build());

        return db.get(id++);
    }
}
