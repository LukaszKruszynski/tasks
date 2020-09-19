package com.crud.tasks.mapper.trellomapper;

import com.crud.tasks.domain.TrelloList;
import com.crud.tasks.domain.TrelloListDto;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TrelloListMapperTestSuite {
    @Autowired
    private TrelloListMapper mapper;
    private TrelloList trelloListId1;
    private TrelloList trelloListId2;
    private List<TrelloList> listOfTrelloList;
    private TrelloListDto trelloListDtoId3;
    private TrelloListDto trelloListDtoId4;
    private List<TrelloListDto> listOfTrelloListDto;


    @Before
    public void setup() {
        this.trelloListId1 = new TrelloList("1","test1",true);
        this.trelloListId2 = new TrelloList("2","test2",false);
        this.trelloListDtoId3 = new TrelloListDto("3","dto test3",false);
        this.trelloListDtoId4 = new TrelloListDto("4","dto test4",true);
        this.listOfTrelloList = new ArrayList<>();
        this.listOfTrelloListDto = new ArrayList<>();
        listOfTrelloList.add(trelloListId1);
        listOfTrelloList.add(trelloListId2);
        listOfTrelloListDto.add(trelloListDtoId3);
        listOfTrelloListDto.add(trelloListDtoId4);

    }

    @Test
    public void mapToLists() {
        //Given
        //When
        List<TrelloList> trelloListsAfterMap = mapper.mapToLists(listOfTrelloListDto);
        //Then
        assertEquals(trelloListsAfterMap.get(0).getId(),listOfTrelloListDto.get(0).getId());
        assertEquals(trelloListsAfterMap.get(0).getName(),listOfTrelloListDto.get(0).getName());
        assertEquals(trelloListsAfterMap.get(0).isClosed(),listOfTrelloListDto.get(0).isClosed());
        assertEquals(trelloListsAfterMap.get(1).getId(),listOfTrelloListDto.get(1).getId());
        assertEquals(trelloListsAfterMap.get(1).getName(),listOfTrelloListDto.get(1).getName());
        assertEquals(trelloListsAfterMap.get(1).isClosed(),listOfTrelloListDto.get(1).isClosed());
        assertEquals(trelloListsAfterMap.size(),listOfTrelloListDto.size());
        assertFalse(trelloListsAfterMap.isEmpty());
    }

    @Test
    public void mapToListsDto() {
        //Given
        //When
        List<TrelloListDto> trelloListDtosAfterMap = mapper.mapToListsDto(listOfTrelloList);
        //Then
        assertEquals(trelloListDtosAfterMap.get(0).getId(),listOfTrelloList.get(0).getId());
        assertEquals(trelloListDtosAfterMap.get(0).getName(),listOfTrelloList.get(0).getName());
        assertEquals(trelloListDtosAfterMap.get(0).isClosed(),listOfTrelloList.get(0).isClosed());
        assertEquals(trelloListDtosAfterMap.get(1).getId(),listOfTrelloList.get(1).getId());
        assertEquals(trelloListDtosAfterMap.get(1).getName(),listOfTrelloList.get(1).getName());
        assertEquals(trelloListDtosAfterMap.get(1).isClosed(),listOfTrelloList.get(1).isClosed());
        assertEquals(trelloListDtosAfterMap.size(),listOfTrelloList.size());
        assertFalse(trelloListDtosAfterMap.isEmpty());
    }
}