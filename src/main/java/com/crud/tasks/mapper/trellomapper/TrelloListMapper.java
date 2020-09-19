package com.crud.tasks.mapper.trellomapper;

import com.crud.tasks.domain.TrelloList;
import com.crud.tasks.domain.TrelloListDto;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Component
public class TrelloListMapper {

    public List<TrelloList> mapToLists(final List<TrelloListDto> trelloListsDto) {
        return trelloListsDto.stream()
                .map(trelloListDto1 -> new TrelloList(
                        trelloListDto1.getId(),
                        trelloListDto1.getName(),
                        trelloListDto1.isClosed()))
                .collect(toList());
    }

    public List<TrelloListDto> mapToListsDto(final List<TrelloList> trelloLists) {
        return trelloLists.stream()
                .map(trelloList1 -> new TrelloListDto(
                        trelloList1.getId(),
                        trelloList1.getName(),
                        trelloList1.isClosed()))
                .collect(toList());
    }
}
