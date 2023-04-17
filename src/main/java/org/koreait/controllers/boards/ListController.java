package org.koreait.controllers.boards;

import org.koreait.models.board.Board;
import org.koreait.models.board.BoardDao;
import org.koreait.models.board.BoardDeleteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/board")
public class ListController {
    @Autowired
    private BoardDeleteService service;
    @Autowired
    BoardDao boardDao;

    @GetMapping("/list")
    public String list(Model model){
        List<Board> boards = new ArrayList<>();
        boards=boardDao.gets();
        model.addAttribute("boards",boards);

        return "board/list";
    }

    @GetMapping ("/list/{id}")
    public String idMapping(@PathVariable Long id, Model model){
        Board board = boardDao.get(id);
        model.addAttribute("board",board);

        return "board/content";
    }
    @GetMapping("/content")
    public String content(){
        return "board/content";
    }

    @GetMapping("/delete/{id}")
    public String deletePs(@PathVariable Long id){

        service.delete(id);
        return "redirect:/board/list";
    }
}
