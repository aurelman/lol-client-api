package com.androweed.lol.client.interfaces;

import com.androweed.lol.client.dtos.SummonerDTO;
import com.androweed.lol.client.exceptions.RateLimitExceededException;
import com.fasterxml.jackson.core.JsonParseException;

import java.io.IOException;
import java.util.Map;

/**
 * This is the base class for the lol client.
 * @author aurelman
 */
public interface LolClient {

    Map<String, SummonerDTO> getSummonerByName(final String region, final String ... summonerNames) throws RateLimitExceededException, IOException;
}
