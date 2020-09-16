package com.crud.tasks.trello.client;

import com.crud.tasks.domain.CreatedTrelloCard;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.trello.config.TrelloConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class TrelloClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(TrelloClient.class);

    @Autowired
    private TrelloConfig configurator;

    @Autowired
    private RestTemplate restTemplate;

    private URI buildUrlTrelloBoards() {
        return UriComponentsBuilder.fromHttpUrl(configurator.getTrelloApiEndpoint() + "/members/" + configurator.getTrelloMemberId() + "/boards")
                .queryParam("key", configurator.getTrelloAppKey())
                .queryParam("token", configurator.getTrelloToken())
                .queryParam("fields", "name,id")
                .queryParam("lists", "all")
                .build().encode().toUri();
    }

    private URI buildUrlNewCard(TrelloCardDto trelloCardDto) {
        return UriComponentsBuilder.fromHttpUrl(configurator.getTrelloApiEndpoint() + "/cards")
                .queryParam("key", configurator.getTrelloAppKey())
                .queryParam("token", configurator.getTrelloToken())
                .queryParam("name", trelloCardDto.getName())
                .queryParam("desc", trelloCardDto.getDescription())
                .queryParam("pos", trelloCardDto.getPos())
                .queryParam("idList", trelloCardDto.getIdList())
                .build().encode().toUri();
    }

    public List<TrelloBoardDto> getTrelloBoards() {
        TrelloBoardDto[] boardsResponse = restTemplate.getForObject(buildUrlTrelloBoards(), TrelloBoardDto[].class);
        return Arrays.asList(Optional.ofNullable(boardsResponse).orElse(new TrelloBoardDto[0]));
    }

    public CreatedTrelloCard CreateNewCard(TrelloCardDto trelloCardDto) {
        System.out.println(buildUrlNewCard(trelloCardDto));
        return restTemplate.postForObject(buildUrlNewCard(trelloCardDto), null, CreatedTrelloCard.class);
    }
}
