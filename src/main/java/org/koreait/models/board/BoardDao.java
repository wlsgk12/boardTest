package org.koreait.models.board;

import lombok.RequiredArgsConstructor;
import org.springframework.data.relational.core.sql.SQL;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

@Component
@RequiredArgsConstructor
public class BoardDao {
    private final JdbcTemplate jdbcTemplate;
    //게시글 추가
    public boolean insert(Board board){
        String sql= "INSERT INTO BOARD (ID, SUBJECT, CONTENT) " +
                " VALUES (SEQ_BOARD.nextval, ? , ? )";
        int cnt = jdbcTemplate.update(sql,board.getSubject(),board.getContent());
        return cnt >0;
    }

    //게시글 조회
    public Board get(Long id){
        try{
            String sql = "SELECT * FROM BOARD WHERE ID = ?";
            Board board = jdbcTemplate.queryForObject(sql,this::rowMapper,id);
            return board;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    public List<Board> gets() {
        String sql = "SELECT * FROM BOARD ORDER BY ID";
        List<Board> boards = jdbcTemplate.query(sql,this::rowMapper);
        return boards;
    }
    private Board rowMapper(ResultSet rs, int i) throws SQLException{
        Board board = new Board();
        board.setId(rs.getLong("ID"));
        board.setSubject(rs.getString("SUBJECT"));
        board.setContent(rs.getString("CONTENT"));
        board.setRegDt(rs.getTimestamp("REGDT").toLocalDateTime());

        return board;
    }
    public boolean delete(Long id){
        String sql = "DELETE FROM BOARD WHERE ID = ?";
        Board board = this.get(id);

        int cnt = jdbcTemplate.update(sql, id);

        return cnt > 0;
    }
}
