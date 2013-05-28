/*
 * Copyright (C) 2013  Christian Autermann, Jan Alexander Wirwahn,
 *                     Arne De Wall, Dustin Demuth, Saqib Rasheed
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
package io.car.server.rest.encoding;

import java.net.URI;

import javax.ws.rs.core.MediaType;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.hp.hpl.jena.rdf.model.Model;

import io.car.server.core.entities.Track;
import io.car.server.core.entities.Tracks;
import io.car.server.rest.JSONConstants;

public class TracksEncoder extends AbstractEntityEncoder<Tracks> {
    @Override
    public ObjectNode encodeJSON(Tracks t, MediaType mediaType) {
        ObjectNode root = getJsonFactory().objectNode();
        ArrayNode tracks = root.putArray(JSONConstants.TRACKS_KEY);
        for (Track u : t) {
            URI uri = getUriInfo().getAbsolutePathBuilder()
                    .path(u.getIdentifier()).build();
            ObjectNode track = tracks.addObject();
            track.put(JSONConstants.IDENTIFIER_KEY, u.getIdentifier());
            track.put(JSONConstants.MODIFIED_KEY, getDateTimeFormat().print(u
                    .getLastModificationDate()));
            if (u.getName() != null) {
                track.put(JSONConstants.NAME_KEY, u.getName());
            }
            track.put(JSONConstants.HREF_KEY, uri.toString());
        }
        return root;
    }

    @Override
    public Model encodeRDF(Tracks t, MediaType mt) {
        /* TODO implement io.car.server.rest.encoding.TracksEncoder.encodeRDF() */
        throw new UnsupportedOperationException("io.car.server.rest.encoding.TracksEncoder.encodeRDF() not yet implemented");
    }
}
