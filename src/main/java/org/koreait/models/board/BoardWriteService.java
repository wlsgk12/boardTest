package org.koreait.models.board;

import org.koreait.controllers.boards.BoardForm;
import org.koreait.validator.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardWriteService implements Validator<BoardForm> {
    @Autowired
    private BoardDao boardDao;


    //검증 메서드
    @Override
    public void check(BoardForm boardForm) {
        Board board = new Board();
        board.setSubject(boardForm.getSubject());
        board.setContent(boardForm.getContent());


        String subject = board.getSubject();
        String content = board.getContent();

        // 제목 null 체크
        if (subject == null || subject.isBlank()) {
            throw new BoardValidationException("제목을 입력하세요.");
        }

        // 내용 null 체크
        if (content == null || content.isBlank()) {
            throw new BoardValidationException("내용을 입력하세요.");
        }
        boardDao.insert(board);
    }
}
