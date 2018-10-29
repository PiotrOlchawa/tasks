package com.crud.tasks.trello.validator;

import com.crud.tasks.domain.TrelloBoard;
import com.crud.tasks.domain.TrelloList;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class TrelloValidatorTest {

    @InjectMocks
    private TrelloValidator trelloValidator;

    @Test
    public void testValidateTrelloBoards() {
        //Given
        List<TrelloList> trelloLists = new ArrayList<>();

        List<TrelloBoard> trelloBoards = Arrays.asList(
                new TrelloBoard("1", "board1", trelloLists),
                new TrelloBoard("2", "test", trelloLists)
        );

        //When
        List<TrelloBoard> validatedBoards = trelloValidator.validateTrelloBoards(trelloBoards);

        //Then
        Assert.assertEquals(1, validatedBoards.size());

        validatedBoards.forEach(trelloBoard -> {
            Assert.assertEquals("1", trelloBoard.getId());
            Assert.assertEquals("board1", trelloBoard.getName());
            Assert.assertEquals(0, trelloBoard.getLists().size());
        });
    }
}