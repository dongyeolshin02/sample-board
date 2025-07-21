package com.example.sample.board.dao;

import com.example.sample.board.data.Board;
import com.example.sample.board.data.PagingVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Mapper
public interface BoardMapper {

    int getListCount() throws Exception;
    List<Board.Response> getBoardList(PagingVO page) throws Exception;
    Board.Response getBoard(Map<String, Object> param) throws Exception;

    int insertBoard(Board.Request request) throws Exception ;
    int updateBoard(Board.Request request) throws Exception;
    int deleteBoard(Map<String, Object> param);
}
