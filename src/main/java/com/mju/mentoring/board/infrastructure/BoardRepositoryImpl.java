package com.mju.mentoring.board.infrastructure;

import com.mju.mentoring.board.domain.Board;
import com.mju.mentoring.board.domain.BoardRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class BoardRepositoryImpl implements BoardRepository {

    private final BoardJpaRepository boardJpaRepository;
    private final BoardQueryDslRepository boardQueryDslRepository;

    @Override
    public Board save(final Board board) {
        return boardJpaRepository.save(board);
    }

    @Override
    public List<Board> findAll(final Long boardId, final int size, final String search) {
        return boardQueryDslRepository.findAll(boardId, size, search);
    }

    @Override
    public Optional<Board> findById(final Long id) {
        return boardJpaRepository.findById(id);
    }

    @Override
    public Optional<Board> viewById(final Long id) {
        return boardJpaRepository.viewById(id);
    }

    @Override
    public void delete(final Board board) {
        boardJpaRepository.delete(board);
    }

    @Override
    public void deleteAllById(final List<Long> ids) {
        boardJpaRepository.deleteAllById(ids);
    }

    @Override
    public List<Board> findBoardsByBoardsId(final List<Long> ids) {
        return boardJpaRepository.findAllById(ids);
    }

    @Override
    public void updateWriterName(final Long writerId, final String newWriterName) {
        boardJpaRepository.updateWriterName(writerId, newWriterName);
    }
}
