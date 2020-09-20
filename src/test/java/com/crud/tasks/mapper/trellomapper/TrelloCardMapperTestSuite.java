package com.crud.tasks.mapper.trellomapper;

import com.crud.tasks.domain.TrelloCard;
import com.crud.tasks.domain.TrelloCardDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TrelloCardMapperTestSuite {
    @Mock
    private TrelloCardMapper mapper;
    private TrelloCard trelloCard;
    private TrelloCardDto trelloCardDto;


    @Before
    public void setup() {
        this.trelloCard = new TrelloCard("card test", "testing...",
                "position in test!", "267ehbdhdshwe7");
        this.trelloCardDto = new TrelloCardDto("card test", "testing...",
                "position in test!", "267ehbdhdshwe7");
    }

    @Test
    public void mapToCardDtoTest() {
        //Given
        when(mapper.mapToCardDto(trelloCard)).thenReturn(trelloCardDto);
        //When
        TrelloCardDto trelloCardDtoAfterMap = mapper.mapToCardDto(trelloCard);
        //Then
        assertEquals("card test", trelloCardDtoAfterMap.getName());
        assertEquals("testing...", trelloCardDtoAfterMap.getDescription());
        assertEquals("position in test!", trelloCardDtoAfterMap.getPos());
        assertEquals("267ehbdhdshwe7", trelloCardDtoAfterMap.getIdList());
    }

    @Test
    public void mapToCardTest() {
        //Given
        when(mapper.mapToCard(trelloCardDto)).thenReturn(trelloCard);
        //When
        TrelloCard trelloCardAfterMap = mapper.mapToCard(trelloCardDto);
        //Then
        assertEquals("card test", trelloCardAfterMap.getName());
        assertEquals("testing...", trelloCardAfterMap.getDescription());
        assertEquals("position in test!", trelloCardAfterMap.getPos());
        assertEquals("267ehbdhdshwe7", trelloCardAfterMap.getIdList());
    }
}