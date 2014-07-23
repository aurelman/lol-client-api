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
    public void shouldReturnsAValidObjectWhenSummonerExists() throws RateLimitExceededException, IOException {

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

    @Test(expected = RateLimitExceededException.class)
    public void shouldThrowAnExceptionWhenRateLimitIsExceeded() throws RateLimitExceededException, IOException {

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