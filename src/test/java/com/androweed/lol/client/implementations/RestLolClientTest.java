package com.androweed.lol.client.implementations;

import com.androweed.lol.client.dtos.SummonerDTO;
import org.junit.Test;

import java.util.Map;

public class RestLolClientTest {

    @Test
    public void testGetSummonerByName() throws Exception {
        Map<String, SummonerDTO> result = new RestLolClient().getSummonerByName("euw", "aurelman");
    }
}