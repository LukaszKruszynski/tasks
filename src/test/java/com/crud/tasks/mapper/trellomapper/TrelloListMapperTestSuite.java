package com.crud.tasks.mapper.trellomapper;

import com.crud.tasks.domain.TrelloList;
import com.crud.tasks.domain.TrelloListDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TrelloListMapperTestSuite {
    @Mock
    private TrelloListMapper mapper;
    private TrelloList trelloListId1;
    private TrelloList trelloListId2;
    private List<TrelloList> listOfTrelloList;
    private TrelloListDto trelloListDtoId1;
    private TrelloListDto trelloListDtoId2;
    private List<TrelloListDto> listOfTrelloListDto;

    @Before
    public void setup() {
        this.trelloListId1 = new TrelloList("1", "test1", true);
        this.trelloListId2 = new TrelloList("2", "test2", false);
        this.trelloListDtoId1 = new TrelloListDto("1", "test1", true);
        this.trelloListDtoId2 = new TrelloListDto("2", "test2", false);
        this.listOfTrelloList = new ArrayList<>();
        this.listOfTrelloListDto = new ArrayList<>();
        listOfTrelloList.add(trelloListId1);
        listOfTrelloList.add(trelloListId2);
        listOfTrelloListDto.add(trelloListDtoId1);
        listOfTrelloListDto.add(trelloListDtoId2);

    }

    @Test
    public void mapToLists() {
        //Given
        when(mapper.mapToLists(listOfTrelloListDto)).thenReturn(listOfTrelloList);
        //When
        List<TrelloList> trelloListsAfterMap = mapper.mapToLists(listOfTrelloListDto);
        //Then
        assertEquals(2, trelloListsAfterMap.size());
        assertEquals("1", trelloListsAfterMap.get(0).getId());
        assertEquals("test1", trelloListsAfterMap.get(0).getName());
        assertTrue(trelloListsAfterMap.get(0).isClosed());
        assertEquals("2", trelloListsAfterMap.get(1).getId());
        assertEquals("test2", trelloListsAfterMap.get(1).getName());
        assertFalse(trelloListsAfterMap.get(1).isClosed());
    }

    @Test
    public void mapToListsDto() {
        //Given
        when(mapper.mapToListsDto(listOfTrelloList)).thenReturn(listOfTrelloListDto);
        //When
        List<TrelloListDto> trelloListsAfterMap = mapper.mapToListsDto(listOfTrelloList);
        //Then
        assertEquals(2, trelloListsAfterMap.size());
        assertEquals("1", trelloListsAfterMap.get(0).getId());
        assertEquals("test1", trelloListsAfterMap.get(0).getName());
        assertTrue(trelloListsAfterMap.get(0).isClosed());
        assertEquals("2", trelloListsAfterMap.get(1).getId());
        assertEquals("test2", trelloListsAfterMap.get(1).getName());
        assertFalse(trelloListsAfterMap.get(1).isClosed());
    }
}