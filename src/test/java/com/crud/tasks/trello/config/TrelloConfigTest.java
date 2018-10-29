package com.crud.tasks.trello.config;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class TrelloConfigTest {

    @Mock
    TrelloConfig trelloConfig;

    @Test
    public void testGetTrelloApiEndpoint() {
        //given
        String trelloApiEndpoint = "https://trello.com/1";
        when(trelloConfig.getTrelloApiEndpoint()).thenReturn(trelloApiEndpoint);
        //when
        String excpectApiEndpoint = trelloConfig.getTrelloApiEndpoint();
        //then
        Assert.assertEquals(trelloApiEndpoint, excpectApiEndpoint);
    }
    @Test
    public void testGetTrelloAppKey() {
        //given
        String trelloAppKey = "b55e973c21dd792babd59511ab923874";
        when(trelloConfig.getTrelloAppKey()).thenReturn(trelloAppKey);
        //when
        String excpectTrelloAppKey = trelloConfig.getTrelloAppKey();
        //then
        Assert.assertEquals(trelloAppKey, excpectTrelloAppKey);
    }

    @Test
    public void testGetTrelloAppToken() {
        //given
        String trelloAppToken = "dd8013439ada84937ad9027438a0fe26aa0b160b3e531412c4dbc2b87dc8f85f";
        when(trelloConfig.getTrelloToken()).thenReturn(trelloAppToken);
        //when
        String excpectTrelloAppToken = trelloConfig.getTrelloToken();
        //then
        Assert.assertEquals(trelloAppToken, excpectTrelloAppToken);
    }

    @Test
    public void testGetTrelloUsername() {
        //given
        String trelloUsername = "mounting2";
        when(trelloConfig.getUsername()).thenReturn(trelloUsername);
        //when
        String excpectTrelloUsername = trelloConfig.getUsername();
        //then
        Assert.assertEquals(trelloUsername, excpectTrelloUsername);
    }
}