package com.example.sample.board.service;

import com.example.sample.board.dao.BoardMapper;
import com.example.sample.board.data.Board;
import com.example.sample.board.data.PagingVO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class BoardService {
    // 의존성 주입 받은 후 객체가 생성되면 수정불가 를 해주기 위해 final 키워드를 붙인다.
    private final BoardMapper mapper;

    public BoardService(BoardMapper mapper) {
        this.mapper = mapper;
    }


    public  int getListCount() throws Exception {
        return  this.mapper.getListCount();
    }

    public List<Board.Response> getList(PagingVO page) throws Exception {
        return mapper.getBoardList(page);
    }

    public int register(Board.Request request) throws Exception {
        return mapper.insertBoard(request);
    }

    public Board.Response getBoard(Map<String, Object> param) throws  Exception {
        return mapper.getBoard(param);
    }


    public int updateBoard(Board.Request request) throws Exception {
        return  mapper.updateBoard(request);
    }

    public int deleteBoard(Map<String, Object> param) throws Exception {
        return  mapper.deleteBoard(param);
    }

}
