package com.mju.mentoring.member.infrastructure;

import com.mju.mentoring.member.domain.BoardHistory;
import com.mju.mentoring.member.domain.BoardHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class BoardHistoryRepositoryImpl implements BoardHistoryRepository {

    private final BoardHistoryJpaRepository boardHistoryJpaRepository;

    @Override
    public BoardHistory save(final BoardHistory history) {
        return boardHistoryJpaRepository.save(history);
    }
}
