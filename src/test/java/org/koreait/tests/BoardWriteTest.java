package org.koreait.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.koreait.controllers.boards.BoardForm;
import org.koreait.models.board.Board;
import org.koreait.models.board.BoardDao;
import org.koreait.models.board.BoardWriteService;
import org.koreait.models.board.BoardValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
public class BoardWriteTest {
    @Autowired
    private BoardDao boardDao;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private BoardWriteService service;
    private Board board;
    private BoardForm boardForm;
    @BeforeEach
    public void init() {
        board = new Board();
        board.setSubject("제목!!");
        board.setContent("내용!!!");
        boardForm = new BoardForm();
    }
    @Test
    @DisplayName("게시글 추가 테스트")
    public void insertTest(){
        boolean result = boardDao.insert(board);

    }
    @Test
    @DisplayName("커맨드객체 boardForm 제목 내용이 null값이면 예외 발생")
    public void nullTest(){

        assertThrows(BoardValidationException.class, () -> {
            boardForm.setSubject(null);
            service.check(boardForm);
        });

    }
    @Test
    @DisplayName("게시글 작성 성공시 /board/list로 이동")
    public void writeSuccessTest() throws Exception {
        mockMvc.perform(post("/board/write")
                        .param("subject", "제목")
                        .param("content", "내용"))
                .andExpect(redirectedUrl("/board/list"));
    }

    @Test
    @DisplayName("제목, 내용 필수 체크")
    public void validationTest1() throws Exception {
        String body = mockMvc.perform(post("/board/write"))
                .andDo(print())
                .andReturn().getResponse().getContentAsString();

        // 제목 유효성 검사 체크
        assertTrue(body.contains("제목"));

        // 내용 유효성 검사 체크
        assertTrue(body.contains("내용"));
    }

}
