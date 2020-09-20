package com.crud.tasks.mapper.trellomapper;

import com.crud.tasks.domain.TrelloBoard;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloList;
import com.crud.tasks.domain.TrelloListDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TrelloBoardMapperTestSuite {
    @Mock
    TrelloBoardMapper mapper;

    private TrelloBoard trelloBoardId1;
    private TrelloBoard trelloBoardId2;
    private TrelloBoardDto trelloBoardDtoId1;
    private TrelloBoardDto trelloBoardDtoId2;
    private List<TrelloBoard> boardsList;
    private List<TrelloBoardDto> boardDtosList;

    @Before
    public void setup() {
        this.trelloBoardId1 = new TrelloBoard("1", "test name id 1", new ArrayList<>());
        this.trelloBoardId2 = new TrelloBoard("2", "test name id 2", new ArrayList<>());
        this.trelloBoardDtoId1 = new TrelloBoardDto("1", "test name id 1", new ArrayList<>());
        this.trelloBoardDtoId2 = new TrelloBoardDto("2", "test name id 2", new ArrayList<>());
        this.boardsList = new ArrayList<>();
        this.boardDtosList = new ArrayList<>();
        boardsList.add(trelloBoardId1);
        boardsList.add(trelloBoardId2);
        boardDtosList.add(trelloBoardDtoId1);
        boardDtosList.add(trelloBoardDtoId2);

    }

    @Test
    public void mapToBoardTest() {
        //Given
        when(mapper.mapToBoard(trelloBoardDtoId1)).thenReturn(trelloBoardId1);
        //When
        TrelloBoard trelloBoardAfterMap = mapper.mapToBoard(trelloBoardDtoId1);
        //Then
        assertEquals("1", trelloBoardAfterMap.getId());
        assertEquals(0, trelloBoardAfterMap.getLists().size());
        assertEquals("test name id 1", trelloBoardAfterMap.getName());
    }

    @Test
    public void mapToBoardsTest() {
        //Given
        when(mapper.mapToBoards(boardDtosList)).thenReturn(boardsList);
        //When
        List<TrelloBoard> boardsListAfterMap = mapper.mapToBoards(boardDtosList);
        //Then
        assertEquals(2, boardsListAfterMap.size());
        assertEquals("1", boardsListAfterMap.get(0).getId());
        assertEquals("test name id 1", boardsListAfterMap.get(0).getName());
        assertTrue(boardsListAfterMap.get(0).getLists().isEmpty());
        assertEquals("2", boardsListAfterMap.get(1).getId());
        assertEquals("test name id 2", boardsListAfterMap.get(1).getName());
        assertTrue(boardsListAfterMap.get(1).getLists().isEmpty());
    }

    @Test
    public void mapToBoardDtoTest() {
        //Given
        when(mapper.mapToBoardDto(trelloBoardId2)).thenReturn(trelloBoardDtoId2);
        //When
        TrelloBoardDto trelloBoardDtoAfterMap = mapper.mapToBoardDto(trelloBoardId2);
        //Then
        assertEquals("2", trelloBoardDtoAfterMap.getId());
        assertEquals(0, trelloBoardDtoAfterMap.getLists().size());
        assertEquals("test name id 2", trelloBoardDtoAfterMap.getName());
    }

    @Test
    public void mapToBoardsDtoTest() {
        //Given
        when(mapper.mapToBoardsDto(boardsList)).thenReturn(boardDtosList);
        //When
        List<TrelloBoardDto> boardsListAfterMap = mapper.mapToBoardsDto(boardsList);
        //Then
        assertEquals(2, boardsListAfterMap.size());
        assertEquals("1", boardsListAfterMap.get(0).getId());
        assertEquals("test name id 1", boardsListAfterMap.get(0).getName());
        assertTrue(boardsListAfterMap.get(0).getLists().isEmpty());
        assertEquals("2", boardsListAfterMap.get(1).getId());
        assertEquals("test name id 2", boardsListAfterMap.get(1).getName());
        assertTrue(boardsListAfterMap.get(1).getLists().isEmpty());
    }
}