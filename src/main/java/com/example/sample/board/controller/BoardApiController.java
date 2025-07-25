package com.example.sample.board.controller;

import com.example.sample.board.data.Board;
import com.example.sample.board.data.PagingVO;
import com.example.sample.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class BoardApiController {

    private final BoardService service;

    @GetMapping("/api/board/list")
    public Map<String, Object> getList(@RequestParam(defaultValue = "0") int currentPage) {
        Map<String, Object> resultMap = new HashMap<String, Object>();

        try{
            PagingVO page = new PagingVO();

            int totalCount = service.getListCount();

            page.dataInit(currentPage, totalCount);
            List<Board.Response> list = service.getList(page);
            resultMap.put("data", list);
            resultMap.put("currentPage", currentPage);
            resultMap.put("total", totalCount);
            resultMap.put("totalPage", page.getTotalPage());

        }catch(Exception e){
            e.printStackTrace();
        }

        return resultMap;
    }


    @GetMapping("/api/board/{boId}")
    public Map<String, Object> getBoard(@PathVariable("boId") int boId) {
        Map<String, Object> resultMap = new HashMap<String, Object>();

        try{
            Board.Response list = service.getBoard(boId);
            resultMap.put("data", list);

        }catch(Exception e){
            e.printStackTrace();
        }

        return resultMap;
    }


    @DeleteMapping("/api/board/boId")
    public Map<String, Object> deleteBoard(@PathVariable(value="boId") Integer boardNo) {
        Map<String, Object> resultMap = new HashMap<>();
        Map<String, Object> param = new HashMap<>();

         int resultCode = 200;
         String resultMsg  ="OK";
        try{
            param.put("boardNo", boardNo);
            resultCode = service.deleteBoard(param);

            //실행을 했으나 delete 미실행일경우
            if(resultCode < 1) {
                resultCode = 0;
                resultMsg = "Fail";
            }

        }catch (Exception e) {
            e.printStackTrace();
            resultCode = 500;
            resultMsg = e.getMessage();
        }finally {
            resultMap.put("resultCode", resultCode);
            resultMap.put("resultMsg", resultMsg);
        }

        return resultMap;

    }

    @PostMapping("/api/board")
    public Map<String, Object> addBoard(@RequestBody Board.Request board) {
        Map<String, Object> resultMap = new HashMap<>();
        try{

            service.register(board);
            resultMap.put("resultCode", 200);
        }catch (Exception e) {
            resultMap.put("resultCode", 500);
            e.printStackTrace();
        }

        return resultMap;
    }


    @PutMapping(value = "/api/board/{boId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> modifyBoard(
            @PathVariable(value="boId") int boId,
            @RequestBody Board.Request updateRequest) {
        Map<String, Object> resultMap = new HashMap<>();
        Map<String, Object> param = new HashMap<>();

        int resultCode = 200;
        String resultMsg  ="OK";

        try {

            updateRequest.setBoId(boId);
            resultCode = service.updateBoard(updateRequest);

            //실행을 했으나 delete 미실행일경우
            if(resultCode < 1) {
                resultCode = 0;
                resultMsg = "Fail";
            }

        }catch (Exception e) {
            e.printStackTrace();
            resultCode = 500;
            resultMsg = e.getMessage();

        }finally {
            resultMap.put("resultCode", resultCode);
            resultMap.put("resultMsg", resultMsg);
        }

        return resultMap;

    }

}
