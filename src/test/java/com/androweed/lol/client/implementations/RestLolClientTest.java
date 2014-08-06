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
import com.androweed.lol.client.interfaces.Region;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.Map;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RestLolClientTest {

    @Mock
    private Client restClient;

    @Mock
    private WebTarget webTarget;

    @InjectMocks
    private RestLolClient client = new RestLolClient();

    @Mock
    private Invocation.Builder builder;

    @Mock
    private Response response;

    @Test
    public void shouldReturnsAValidObjectWhenRetrievingSummonerByName() throws
            RateLimitExceededException, IOException {

        when(restClient.target(anyString())).thenReturn(webTarget);
        when(webTarget.path(anyString())).thenReturn(webTarget);
        when(webTarget.request(any(MediaType.class))).thenReturn(builder);
        when(webTarget.queryParam(anyString(), anyString())).thenReturn(webTarget);
        when(builder.get()).thenReturn(response);
        when(response.getStatus()).thenReturn(200);
        when(response.readEntity(String.class)).thenReturn(
                "{\"aurelman\": {\n" +
                "   \"id\": 42,\n" +
                "   \"name\": \"aurelman\",\n" +
                "   \"profileIconId\": 28,\n" +
                "   \"revisionDate\": 1406147667000,\n" +
                "   \"summonerLevel\": 8\n" +
                "}}");


        // Then
        Map<String, SummonerDTO> result = client.retrieveSummonersByName(Region.EUW, "aurelman");
        assertThat(result).containsKey("aurelman");
        assertThat(result.get("aurelman").getName()).isEqualTo("aurelman");
        assertThat(result.get("aurelman").getId()).isEqualTo(42);
        verify(webTarget).path(Region.EUW.getName());
        verify(webTarget).path("aurelman");
    }

    @Test
    public void shouldReturnsMultipleObjectsWhenRetrievingSummonersByName() throws RateLimitExceededException, IOException {

        when(restClient.target(anyString())).thenReturn(webTarget);
        when(webTarget.path(anyString())).thenReturn(webTarget);
        when(webTarget.request(any(MediaType.class))).thenReturn(builder);
        when(webTarget.queryParam(anyString(), anyString())).thenReturn(webTarget);
        when(builder.get()).thenReturn(response);
        when(response.getStatus()).thenReturn(200);
        when(response.readEntity(String.class)).thenReturn(
                "{\n" +
                "   \"aurelman2\": {\n" +
                "      \"id\": 123456789,\n" +
                "      \"name\": \"aurelman2\",\n" +
                "      \"profileIconId\": 663,\n" +
                "      \"revisionDate\": 1406491996000,\n" +
                "      \"summonerLevel\": 30\n" +
                "   },\n" +
                "   \"aurelman\": {\n" +
                "      \"id\": 56177666,\n" +
                "      \"name\": \"aurelman\",\n" +
                "      \"profileIconId\": 28,\n" +
                "      \"revisionDate\": 1406492389000,\n" +
                "      \"summonerLevel\": 9\n" +
                "   }\n" +
                "}");


        // Then
        Map<String, SummonerDTO> result = client.retrieveSummonersByName(Region.EUW, "aurelman", "aurelman2");
        assertThat(result).containsKey("aurelman");
        assertThat(result).containsKey("aurelman2");

        assertThat(result.get("aurelman").getName()).isEqualTo("aurelman");
        assertThat(result.get("aurelman").getId()).isEqualTo(56177666);

        assertThat(result.get("aurelman2").getName()).isEqualTo("aurelman2");
        assertThat(result.get("aurelman2").getId()).isEqualTo(123456789);

        verify(webTarget).path(Region.EUW.getName());
        verify(webTarget).path("aurelman,aurelman2");
    }

    @Test(expected = RateLimitExceededException.class)
    public void shouldThrowAnExceptionWhenRetrievingSummonerByNameAndRateLimitIsExceeded() throws
            RateLimitExceededException, IOException {

        when(restClient.target(anyString())).thenReturn(webTarget);
        when(webTarget.path(anyString())).thenReturn(webTarget);
        when(webTarget.request(any(MediaType.class))).thenReturn(builder);
        when(webTarget.queryParam(anyString(), anyString())).thenReturn(webTarget);
        when(builder.get()).thenReturn(response);
        when(response.getStatus()).thenReturn(429);

        // Then
        client.retrieveSummonersByName(Region.EUW, "aurelman");
    }
}