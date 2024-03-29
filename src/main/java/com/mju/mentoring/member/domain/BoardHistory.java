package com.mju.mentoring.member.domain;

import com.mju.mentoring.global.domain.History;
import com.mju.mentoring.global.domain.HistoryType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
public class BoardHistory extends History<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private HistoryType historyType;

    private BoardHistory(final Long ownerId, final Long targetId, final HistoryType historyType) {
        super(ownerId, targetId);
        this.historyType = historyType;
    }

    public static BoardHistory of(final Long ownerId, final Long targetId,
        final HistoryType historyType) {
        return new BoardHistory(ownerId, targetId, historyType);
    }
}
