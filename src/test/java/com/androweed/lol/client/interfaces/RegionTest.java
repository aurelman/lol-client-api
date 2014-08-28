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
    public void shouldReturnTheCorrectUrlEndpoint() throws Exception {
        assertThat(Region.BR.host()).isEqualTo("br.api.pvp.net");
        assertThat(Region.EUNE.host()).isEqualTo("eune.api.pvp.net");
        assertThat(Region.EUW.host()).isEqualTo("euw.api.pvp.net");
        assertThat(Region.KR.host()).isEqualTo("kr.api.pvp.net");
        assertThat(Region.LAS.host()).isEqualTo("las.api.pvp.net");
        assertThat(Region.LAN.host()).isEqualTo("lan.api.pvp.net");
        assertThat(Region.NA.host()).isEqualTo("na.api.pvp.net");
        assertThat(Region.OCE.host()).isEqualTo("oce.api.pvp.net");
        assertThat(Region.TR.host()).isEqualTo("tr.api.pvp.net");
        assertThat(Region.RU.host()).isEqualTo("ru.api.pvp.net");
        assertThat(Region.GLOBAL.host()).isEqualTo("global.api.pvp.net");
    }

    @Test
    public void shouldReturnTheRightUrlPath() throws Exception {
        assertThat(Region.BR.urlPath()).isEqualTo("br");
        assertThat(Region.EUNE.urlPath()).isEqualTo("eune");
        assertThat(Region.EUW.urlPath()).isEqualTo("euw");
        assertThat(Region.KR.urlPath()).isEqualTo("kr");
        assertThat(Region.LAS.urlPath()).isEqualTo("las");
        assertThat(Region.LAN.urlPath()).isEqualTo("lan");
        assertThat(Region.NA.urlPath()).isEqualTo("na");
        assertThat(Region.OCE.urlPath()).isEqualTo("oce");
        assertThat(Region.TR.urlPath()).isEqualTo("tr");
        assertThat(Region.RU.urlPath()).isEqualTo("ru");
        assertThat(Region.GLOBAL.urlPath()).isEqualTo("global");
    }
}