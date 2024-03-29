package com.mju.mentoring.member.infrastructure;

import com.mju.mentoring.member.domain.BoardHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardHistoryJpaRepository extends JpaRepository<BoardHistory, Long> {

    BoardHistory save(final BoardHistory boardHistory);
}
