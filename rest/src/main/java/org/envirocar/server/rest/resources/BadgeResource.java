/*
 * Copyright (C) 2013 The enviroCar project
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.envirocar.server.rest.resources;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import org.envirocar.server.core.entities.Badges;
import org.envirocar.server.core.util.Pagination;
import org.envirocar.server.rest.MediaTypes;
import org.envirocar.server.rest.RESTConstants;
import org.envirocar.server.rest.Schemas;
import org.envirocar.server.rest.validation.Schema;

public class BadgeResource extends AbstractResource {

    public static final String BADGES = "{badges}";

	@GET
    @Schema(response = Schemas.BADGES)
    @Produces({ MediaTypes.BADGES })
    public Badges get(
            @QueryParam(RESTConstants.LIMIT) @DefaultValue("0") int limit,
            @QueryParam(RESTConstants.PAGE) @DefaultValue("0") int page) {
        return getDataService().getBadges(new Pagination(limit, page));
    }
    
	
}
