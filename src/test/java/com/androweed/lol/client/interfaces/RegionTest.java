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

import org.junit.Test;

import static org.fest.assertions.api.Assertions.assertThat;

public class RegionTest {

    @Test
    public void shouldReturnCorrectEuwAttribute() throws Exception {
        assertThat(Region.EUW.getName()).isEqualTo("euw");
        assertThat(Region.EUW.getEndpoint()).isEqualTo("https://euw.api.pvp.net");
    }
}