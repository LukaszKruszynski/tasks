package com.crud.tasks.controller;

import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.trello.client.TrelloClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/trello")
public class TrelloController {

    @Autowired
    private TrelloClient trelloClient;

    @RequestMapping(method = RequestMethod.GET, value = "getTrelloBoards")
    public void getTrelloBoards() {

        List<TrelloBoardDto> trelloBoards = trelloClient.getTrelloBoards();

        List<TrelloBoardDto> collect = trelloBoards.stream()
                .filter(trelloBoardDto -> trelloBoardDto.getName().length()>0)
                .filter(trelloBoardDto -> trelloBoardDto.getId().length()>0)
                .filter(trelloBoardDto -> trelloBoardDto.getName().contains("Kodilla"))
                .collect(Collectors.toList());
        collect.forEach(s-> System.out.println(s.getId() + s.getName()));

    }
}