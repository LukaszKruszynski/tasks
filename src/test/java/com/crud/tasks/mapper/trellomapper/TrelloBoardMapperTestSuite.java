package com.crud.tasks.mapper.trellomapper;

import com.crud.tasks.domain.TrelloBoard;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloList;
import com.crud.tasks.domain.TrelloListDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
@SpringBootTest
@RunWith(SpringRunner.class)
public class TrelloBoardMapperTestSuite {
    @Autowired
    TrelloBoardMapper mapper;

    private TrelloBoard trelloBoardId1;
    private TrelloBoard trelloBoardId2;
    private TrelloBoardDto trelloBoardDtoId3;
    private TrelloBoardDto trelloBoardDtoId4;
    private List<TrelloBoard> boardsList;
    private List<TrelloBoardDto> boardDtosList;

    @Before
    public void setup() {
        this.trelloBoardId1 = new TrelloBoard("1", "test name id 1", new ArrayList<TrelloList>());
        this.trelloBoardId2 = new TrelloBoard("2", "test name id 2", new ArrayList<TrelloList>());
        this.trelloBoardDtoId3 = new TrelloBoardDto("3", "test name dto id 3", new ArrayList<TrelloListDto>());
        this.trelloBoardDtoId4 = new TrelloBoardDto("4", "test name dto id 4", new ArrayList<TrelloListDto>());
        this.boardsList = new ArrayList<>();
        this.boardDtosList = new ArrayList<>();
        boardsList.add(trelloBoardId1);
        boardsList.add(trelloBoardId2);
        boardDtosList.add(trelloBoardDtoId3);
        boardDtosList.add(trelloBoardDtoId4);

    }

    @Test
    public void mapToBoardTest() {
        //Given
        //When
        TrelloBoard trelloBoardAfterMap = mapper.mapToBoard(trelloBoardDtoId3);
        //Then
        assertEquals(trelloBoardDtoId3.getId(),trelloBoardAfterMap.getId());
        assertEquals(trelloBoardDtoId3.getLists(),trelloBoardAfterMap.getLists());
        assertEquals(trelloBoardDtoId3.getName(),trelloBoardAfterMap.getName());
    }

    @Test
    public void mapToBoardsTest() {
        //Given
        //When
        List<TrelloBoard> boardsListAfterMap = mapper.mapToBoards(boardDtosList);
        //Then
        assertFalse(boardsListAfterMap.isEmpty());
        assertEquals(boardsListAfterMap.size(),boardDtosList.size());
        assertEquals(boardsListAfterMap.get(0).getId(),boardDtosList.get(0).getId());
        assertEquals(boardsListAfterMap.get(0).getName(),boardDtosList.get(0).getName());
        assertEquals(boardsListAfterMap.get(0).getLists(),boardDtosList.get(0).getLists());
        assertEquals(boardsListAfterMap.get(1).getId(),boardDtosList.get(1).getId());
        assertEquals(boardsListAfterMap.get(1).getName(),boardDtosList.get(1).getName());
        assertEquals(boardsListAfterMap.get(1).getLists(),boardDtosList.get(1).getLists());
    }

    @Test
    public void mapToBoardDtoTest() {
        //Given
        //When
        TrelloBoardDto trelloBoardDtoAfterMap = mapper.mapToBoardDto(trelloBoardId1);
        //Then
        assertEquals(trelloBoardDtoAfterMap.getId(),trelloBoardId1.getId());
        assertEquals(trelloBoardDtoAfterMap.getLists(),trelloBoardId1.getLists());
        assertEquals(trelloBoardDtoAfterMap.getName(),trelloBoardId1.getName());
    }

    @Test
    public void mapToBoardsDtoTest() {
        //Given
        //When
        List<TrelloBoardDto> boardDtosListAfterMap = mapper.mapToBoardsDto(boardsList);
        //Then
        assertEquals(boardDtosListAfterMap.size(),boardsList.size());
        assertFalse(boardDtosListAfterMap.isEmpty());
        assertEquals(boardDtosListAfterMap.get(0).getName(),boardsList.get(0).getName());
        assertEquals(boardDtosListAfterMap.get(0).getLists(),boardsList.get(0).getLists());
        assertEquals(boardDtosListAfterMap.get(0).getId(),boardsList.get(0).getId());
        assertEquals(boardDtosListAfterMap.get(1).getName(),boardsList.get(1).getName());
        assertEquals(boardDtosListAfterMap.get(1).getLists(),boardsList.get(1).getLists());
        assertEquals(boardDtosListAfterMap.get(1).getId(),boardsList.get(1).getId());
    }
}