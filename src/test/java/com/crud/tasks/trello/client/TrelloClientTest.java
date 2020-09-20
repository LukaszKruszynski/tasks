package com.crud.tasks.trello.client;

import com.crud.tasks.domain.CreatedTrelloCardDto;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.trello.config.TrelloConfig;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.Silent.class)
public class TrelloClientTest {
    @InjectMocks
    private TrelloClient client;

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private TrelloConfig config;

    @Before
    public void init() {
        Mockito.when(config.getTrelloApiEndpoint()).thenReturn("http://test.com");
        Mockito.when(config.getTrelloAppKey()).thenReturn("test");
        Mockito.when(config.getTrelloToken()).thenReturn("test");
        Mockito.when(config.getTrelloMemberId()).thenReturn("lukaszkruszynski");
    }

    @Test
    public void shouldFetchTrelloBoards() throws URISyntaxException {
        //Given
        TrelloBoardDto[] trelloBoards = new TrelloBoardDto[1];
        trelloBoards[0] = new TrelloBoardDto("test_id", "test_board", new ArrayList<>());

        URI uri = new URI("http://test.com/members/lukaszkruszynski/boards?key=test&token=test&fields=name,id&lists=all");

        Mockito.when(restTemplate.getForObject(uri, TrelloBoardDto[].class)).thenReturn(trelloBoards);

        //When
        List<TrelloBoardDto> fetchedTrelloBoards = client.getTrelloBoards();

        //Then
        Assert.assertEquals(1, fetchedTrelloBoards.size());
        Assert.assertEquals("test_id", fetchedTrelloBoards.get(0).getId());
        Assert.assertEquals("test_board", fetchedTrelloBoards.get(0).getName());
        Assert.assertEquals(new ArrayList<>(), fetchedTrelloBoards.get(0).getLists());
    }

    @Test
    public void shouldCreateCard() throws URISyntaxException {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto(
                "Test task",
                "Test Description",
                "top",
                "test_id"
        );

        URI uri = new URI("http://test.com/cards?key=test&token=test&name=Test%20task&desc=Test%20Description&pos=top&idList=test_id");

        CreatedTrelloCardDto createdTrelloCard = new CreatedTrelloCardDto(
                "1",
                "Test task",
                "http://test.com"
        );
        Mockito.when(restTemplate.postForObject(uri, null, CreatedTrelloCardDto.class)).thenReturn(createdTrelloCard);

        //When
        CreatedTrelloCardDto card = client.CreateNewCard(trelloCardDto);

        //Then
        Assert.assertEquals("1", card.getId());
        Assert.assertEquals("Test task", card.getName());
        Assert.assertEquals("http://test.com", card.getShortUrl());
    }

    @Test
    public void shouldReturnEmptyList() throws URISyntaxException {
        //Given
        URI uri = new URI("");
        Mockito.when(restTemplate.getForObject(uri, TrelloBoardDto.class)).thenReturn(null);

        //When
        List<TrelloBoardDto> trelloBoards = client.getTrelloBoards();

        //Then
        Assert.assertTrue(trelloBoards.isEmpty());
    }
}