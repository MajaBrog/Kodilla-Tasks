package com.crud.tasks.trello.validator;

import com.crud.tasks.domain.TrelloBoard;
import com.crud.tasks.domain.TrelloCard;
import com.crud.tasks.domain.TrelloList;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TrelloValidatorTest {
    @Autowired
    private TrelloValidator validator;

    @Test
    public void shouldValidateTrelloBoards() {
        //Given
        List<TrelloList> trelloLists = new ArrayList<>();
        trelloLists.add(new TrelloList("1", "my_list", false));

        List<TrelloBoard> trelloBoards = new ArrayList<>();
        trelloBoards.add(new TrelloBoard("1", "my_task", trelloLists));

        //When
        List<TrelloBoard> validatedBoards = validator.validateTrelloBoards(trelloBoards);
        //Then
        assertEquals(1, validatedBoards.size());
    }

    @Test
    public void shouldNotValidateTrelloBoardsTest() {
        //Given
        List<TrelloList> trelloLists = new ArrayList<>();
        trelloLists.add(new TrelloList("1", "my_list", false));

        List<TrelloBoard> trelloBoards = new ArrayList<>();
        trelloBoards.add(new TrelloBoard("1", "test", trelloLists));

        //When
        List<TrelloBoard> validatedBoards = validator.validateTrelloBoards(trelloBoards);
        //Then
        assertEquals(0, validatedBoards.size());
    }

    @Test
    public void shouldValidateCard(){
        //When
        TrelloCard trelloCard = new TrelloCard("test facade", "test fascade", "pos", "listId");
        //When & Then
        validator.validateCard(trelloCard);
    }
}