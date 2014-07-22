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

/**
 * The {@link com.androweed.lol.client.interfaces.Region} is used to describe the different type of regions
 * available as a target of the lol API.
 *
 * @author aurelman
 */
public enum Region {

    EUW("euw", "https://euw.api.pvp.net");

    private final String name;

    private final String endpoint;

    private Region(final String name, final String endpoint) {
        this.name = name;
        this.endpoint = endpoint;
    }

    /**
     * The identifier of the region.
     *
     * @return The identifier of the region.
     */
    public String getName() {
        return name;
    }

    /**
     * The target corresponding to the region.
     *
     * @return The target corresponding to the region
     */
    public String getEndpoint() {
        return endpoint;
    }
}
