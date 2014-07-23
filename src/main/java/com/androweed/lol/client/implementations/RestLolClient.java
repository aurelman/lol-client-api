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
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static com.google.common.base.Objects.firstNonNull;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON_TYPE;

/**
 * @author aurelman
 */
public class RestLolClient implements LolClient {

    public static final String PARAM_NAME_API_KEY = "api_key";

    public static final String API_KEY = "";

    @Override
    public Map<String, SummonerDTO> retrieveSummonersByName(final Region region, final String... summonerNames)
            throws RateLimitExceededException, IOException {
        Client client = ClientBuilder.newClient();
        Response response = client.target(region.getEndpoint())
                .path("api")
                .path("lol")
                .path(region.getName())
                .path("v1.4")
                .path("summoner")
                .path("by-name")
                .path("aurelman")
                .queryParam(PARAM_NAME_API_KEY, API_KEY)
                .request(APPLICATION_JSON_TYPE)
                .get();

        if (response.getStatus() == 429) {
            throw new RateLimitExceededException();
        }
        Map<String, SummonerDTO> summoners = null;
        if (response.getStatus() == 200) {
            ObjectMapper mapper = new ObjectMapper();
            MapType mapType = mapper.getTypeFactory().constructMapType(HashMap.class, String.class, SummonerDTO.class);
            summoners = mapper.readValue(response.readEntity(InputStream.class), mapType);
        }
        return firstNonNull(summoners, Collections.<String, SummonerDTO>emptyMap());
    }
}
