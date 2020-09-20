package com.crud.tasks.trello.facade;

import com.crud.tasks.domain.TrelloBoard;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloList;
import com.crud.tasks.domain.TrelloListDto;
import com.crud.tasks.mapper.trellomapper.TrelloBoardMapper;
import com.crud.tasks.service.TrelloService;
import com.crud.tasks.trello.validator.TrelloValidator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.anyList;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TrelloFacadeTestSuite {
    @InjectMocks
    private TrelloFacade trelloFacade;
    @Mock
    private TrelloService trelloService;
    @Mock
    private TrelloValidator trelloValidator;
    @Mock
    private TrelloBoardMapper trelloBoardMapper;
    private List<TrelloListDto> trelloLists;
    private List<TrelloBoardDto> trelloBoards;
    private List<TrelloList> mappedTrelloLists;
    private List<TrelloBoard> mappedTrelloBoards;

    @Before
    public void setup() {
        this.trelloLists = new ArrayList<>();
        trelloLists.add(new TrelloListDto("1", "test_list", false));

        this.trelloBoards = new ArrayList<>();
        trelloBoards.add(new TrelloBoardDto("1", "test", trelloLists));

        this.mappedTrelloLists = new ArrayList<>();
        mappedTrelloLists.add(new TrelloList("1", "test_list", false));

        this.mappedTrelloBoards = new ArrayList<>();
        mappedTrelloBoards.add(new TrelloBoard("1", "test", mappedTrelloLists));
    }

    @Test
    public void shouldFetchEmptyList() {
        //Given
        when(trelloService.fetchTrelloBoards()).thenReturn(trelloBoards);
        when(trelloBoardMapper.mapToBoards(trelloBoards)).thenReturn(mappedTrelloBoards);
        when(trelloBoardMapper.mapToBoardsDto(anyList())).thenReturn(new ArrayList<>());
        when(trelloValidator.validateTrelloBoards(mappedTrelloBoards)).thenReturn(new ArrayList<>());
        //When
        List<TrelloBoardDto> trelloBoardDtos = trelloFacade.fetchTrelloBoards();
        //Then
        assertNotNull(trelloBoardDtos);
        assertEquals(0, trelloBoardDtos.size());
    }

    @Test
    public void shouldFetchTrelloBoards() {
        //Given
        when(trelloService.fetchTrelloBoards()).thenReturn(trelloBoards);
        when(trelloBoardMapper.mapToBoards(trelloBoards)).thenReturn(mappedTrelloBoards);
        when(trelloBoardMapper.mapToBoardsDto(anyList())).thenReturn(trelloBoards);
        when(trelloValidator.validateTrelloBoards(mappedTrelloBoards)).thenReturn(mappedTrelloBoards);
        //When
        List<TrelloBoardDto> trelloBoardDtos = trelloFacade.fetchTrelloBoards();
        //Then
        assertNotNull(trelloBoardDtos);
        assertEquals(1, trelloBoardDtos.size());

        trelloBoardDtos.forEach(board-> {
            assertEquals("1",board.getId());
            assertEquals("test",board.getName());

            board.getLists().forEach(list -> {
                assertEquals("1",list.getId());
                assertEquals("test_list",list.getName());
                assertFalse(list.isClosed());
            });
        });
    }
}