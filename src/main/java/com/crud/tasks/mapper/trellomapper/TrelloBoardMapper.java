package com.crud.tasks.mapper.trellomapper;

import com.crud.tasks.domain.TrelloBoard;
import com.crud.tasks.domain.TrelloBoardDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.stream.Collectors.toList;
@Component
public class TrelloBoardMapper {
    @Autowired
    private TrelloListMapper trelloListMapper;

    public TrelloBoard mapToBoard(final TrelloBoardDto trelloBoardDto) {
        return new TrelloBoard(
                trelloBoardDto.getId(),
                trelloBoardDto.getName(),
                trelloListMapper.mapToLists(trelloBoardDto.getLists()));
    }

    public List<TrelloBoard> mapToBoards(final List<TrelloBoardDto> trelloBoardsDto) {
        return trelloBoardsDto.stream()
                .map(this::mapToBoard)
                .collect(toList());
    }

    public TrelloBoardDto mapToBoardDto(final TrelloBoard trelloBoard) {
        return new TrelloBoardDto(
                trelloBoard.getId(),
                trelloBoard.getName(),
                trelloListMapper.mapToListsDto(trelloBoard.getLists()));
    }

    public List<TrelloBoardDto> mapToBoardsDto(final List<TrelloBoard> trelloBoards) {
        return trelloBoards.stream()
                .map(this::mapToBoardDto)
                .collect(toList());
    }
}
