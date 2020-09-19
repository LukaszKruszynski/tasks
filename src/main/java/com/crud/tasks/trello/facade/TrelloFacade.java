package com.crud.tasks.trello.facade;

import com.crud.tasks.domain.*;
import com.crud.tasks.mapper.trellomapper.TrelloBoardMapper;
import com.crud.tasks.mapper.trellomapper.TrelloCardMapper;
import com.crud.tasks.service.TrelloService;
import com.crud.tasks.trello.validator.TrelloValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TrelloFacade {
    private static final Logger LOGGER = LoggerFactory.getLogger(TrelloFacade.class);

    @Autowired
    private TrelloService service;

    @Autowired
    private TrelloBoardMapper trelloBoardMapper;

    @Autowired
    private TrelloCardMapper trelloCardMapper;

    @Autowired
    private TrelloValidator trelloValidator;

    public CreatedTrelloCardDto createCard(final TrelloCardDto trelloCardDto) {
        TrelloCard trelloCard = trelloCardMapper.mapToCard(trelloCardDto);
        trelloValidator.validateCard(trelloCard);
        return service.createTrelloCard(trelloCardMapper.mapToCardDto(trelloCard));
    }

    public List<TrelloBoardDto> fetchTrelloBoards() {
        List<TrelloBoard> trelloBoards = trelloBoardMapper.mapToBoards(service.fetchTrelloBoards());
        List<TrelloBoard> filteredBoards = trelloValidator.validateTrelloBoards(trelloBoards);
        return trelloBoardMapper.mapToBoardsDto(filteredBoards);
    }
}

