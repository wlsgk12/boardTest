package org.koreait.models.board;

import org.koreait.controllers.boards.BoardForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardDeleteService {
    @Autowired
    private BoardDao boardDao;
    public void delete(Long id){
        Board board = new Board();
        boardDao.delete(id);
    }
}
