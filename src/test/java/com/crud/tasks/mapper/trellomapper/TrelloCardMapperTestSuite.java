package com.crud.tasks.mapper.trellomapper;

import com.crud.tasks.domain.TrelloCard;
import com.crud.tasks.domain.TrelloCardDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TrelloCardMapperTestSuite {
    @Autowired
    private TrelloCardMapper mapper;
    private TrelloCard trelloCard;
    private TrelloCardDto trelloCardDto;


    @Before
    public void setup() {
        this.trelloCard = new TrelloCard("card test", "testing...",
                "position in test!", "267ehbdhdshwe7");
        this.trelloCardDto = new TrelloCardDto("card dto test","dto testing...",
                "dto position in test!","823jen23j");
    }

    @Test
    public void mapToCardDtoTest() {
        //Given
        //When
        TrelloCardDto trelloCardDtoAfterMap = mapper.mapToCardDto(trelloCard);
        //Then
        assertEquals(trelloCardDtoAfterMap.getName(), trelloCard.getName());
        assertEquals(trelloCardDtoAfterMap.getDescription(), trelloCard.getDescription());
        assertEquals(trelloCardDtoAfterMap.getPos(), trelloCard.getPos());
        assertEquals(trelloCardDtoAfterMap.getIdList(), trelloCard.getIdList());
    }

    @Test
    public void mapToCardTest() {
        //Given
        //When
        TrelloCard trelloCardAfterMap = mapper.mapToCard(trelloCardDto);
        //Then
        assertEquals(trelloCardAfterMap.getName(), trelloCardDto.getName());
        assertEquals(trelloCardAfterMap.getDescription(), trelloCardDto.getDescription());
        assertEquals(trelloCardAfterMap.getPos(), trelloCardDto.getPos());
        assertEquals(trelloCardAfterMap.getIdList(), trelloCardDto.getIdList());
    }
}