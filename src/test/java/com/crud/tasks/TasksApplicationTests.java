package com.crud.tasks;

import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.trello.client.TrelloClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TasksApplicationTests {
	@Autowired
	TrelloClient trelloClient;
	@Test
	void contextLoads() {
		TrelloCardDto trelloCardDto = new TrelloCardDto("Test test","testing...","top","5eda9be4a3c4e72078a5bb95");
		System.out.println(trelloClient.buildUrlNewCard(trelloCardDto));
	}
}
