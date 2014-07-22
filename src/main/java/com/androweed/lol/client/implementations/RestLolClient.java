/*
 * Copyright (C) 2014 Manoury Aurélien
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along
 * with this program; if not, write to the Free Software Foundation, Inc.,
 * 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 */

package com.androweed.lol.client.implementations;

import com.androweed.lol.client.dtos.SummonerDTO;
import com.androweed.lol.client.exceptions.RateLimitExceededException;
import com.androweed.lol.client.interfaces.LolClient;
import com.androweed.lol.client.interfaces.Region;
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

    public static final String API_KEY = "";

    @Override
    public Map<String, SummonerDTO> retrieveSummonerByName(final String region, final String... summonerNames)
            throws RateLimitExceededException, IOException {
        Client client = ClientBuilder.newClient();
        Invocation.Builder target = client.target(Region.EUW.getEndpoint())
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
