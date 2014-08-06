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

package com.androweed.lol.client.interfaces;

import com.androweed.lol.client.dtos.SummonerDTO;
import com.androweed.lol.client.exceptions.RateLimitExceededException;

import java.io.IOException;
import java.util.Map;

/**
 * This is the base class for the lol client.
 * @author aurelman
 */
public interface LolClient {

    /**
     * Retrieve {@link com.androweed.lol.client.dtos.SummonerDTO summoners} given their respective name
     * in the specified the region.
     *
     * @param region The region that should be requested to find the summoner.
     * @param summonerNames The summoner's names
     * @return a {@link java.util.Map} object containing the retrieved
     * {@link com.androweed.lol.client.dtos.SummonerDTO summoners} identified by their name.
     *
     * @throws RateLimitExceededException if the rate limit is exceeded during the call.
     * @throws IOException If any error occurs during retrieving data.
     */
    Map<String, SummonerDTO> retrieveSummonersByName(final Region region, final String... summonerNames)
            throws RateLimitExceededException, IOException;

    /**
     * Retrieve {@link com.androweed.lol.client.dtos.SummonerDTO summoners} given their respective id
     * in the specified the region.
     *
     * @param region The region that should be requested to find the summoner.
     * @param summonerIds The summoner's ids
     * @return a {@link java.util.Map} object containing the retrieved
     * {@link com.androweed.lol.client.dtos.SummonerDTO summoners} identified by their name.
     *
     * @throws RateLimitExceededException if the rate limit is exceeded during the call.
     * @throws IOException If any error occurs during retrieving data.
     */
    Map<String, SummonerDTO> retrieveSummonersById(final Region region, final String ... summonerIds)
            throws RateLimitExceededException, IOException;
}
