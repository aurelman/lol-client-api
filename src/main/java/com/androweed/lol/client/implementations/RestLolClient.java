package com.androweed.lol.client.implementations;

import com.androweed.lol.client.dtos.SummonerDTO;
import com.androweed.lol.client.exceptions.RateLimitExceededException;
import com.androweed.lol.client.interfaces.LolClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.MapType;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * @author aurelman
 */
public class RestLolClient implements LolClient {

    public static final String PARAM_NAME_API_KEY = "api_key";

    public static final String EUROPE_TARGET_BASE_URL = "https://euw.api.pvp.net";

    public static final String API_KEY = "";

    @Override
    public Map<String, SummonerDTO> getSummonerByName(final String region, final String... summonerNames)
            throws RateLimitExceededException, IOException {
        Client client = ClientBuilder.newClient();
        Invocation.Builder target = client.target(EUROPE_TARGET_BASE_URL)
                .path("api")
                .path("lol")
                .path("euw")
                .path("v1.4")
                .path("summoner")
                .path("by-name")
                .path("aurelman")
                .queryParam(PARAM_NAME_API_KEY, API_KEY)
                .request(MediaType.APPLICATION_JSON_TYPE);


        Response response = target.get();

        if (response.getStatus() == 429) {
            throw new RateLimitExceededException();
        }
        Map<String, SummonerDTO> is = null;
        if (response.getStatus() == 200) {
            ObjectMapper mapper = new ObjectMapper();
            MapType mapType = mapper.getTypeFactory().constructMapType(HashMap.class, String.class, SummonerDTO.class);
            is = mapper.readValue(response.readEntity(InputStream.class), mapType);
        }
        return is;
    }
}
