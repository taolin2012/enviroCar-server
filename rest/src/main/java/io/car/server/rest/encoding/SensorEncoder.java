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

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.hp.hpl.jena.rdf.model.Model;

import io.car.server.core.entities.Sensor;
import io.car.server.rest.JSONConstants;
import io.car.server.rest.MediaTypes;
import io.car.server.rest.resources.RootResource;
import io.car.server.rest.resources.SensorsResource;

/**
 * @author Christian Autermann <autermann@uni-muenster.de>
 */
public class SensorEncoder extends AbstractEntityEncoder<Sensor> {

    @Override
    public ObjectNode encodeJSON(Sensor t, MediaType mediaType) {
        ObjectNode sensor = getJsonFactory().objectNode()
                .put(JSONConstants.NAME_KEY, t.getName());
        if (mediaType.equals(MediaTypes.SENSOR_TYPE)) {
            sensor.put(JSONConstants.CREATED_KEY,
                       getDateTimeFormat().print(t.getCreationDate()));
            sensor.put(JSONConstants.MODIFIED_KEY,
                       getDateTimeFormat().print(t.getLastModificationDate()));
        } else {
            URI href = getUriInfo().getBaseUriBuilder()
                    .path(RootResource.class)
                    .path(RootResource.SENSORS)
                    .path(SensorsResource.SENSOR)
                    .build(t.getName());
            sensor.put(JSONConstants.HREF_KEY, href.toString());
        }
        return sensor;
    }

    @Override
    public Model encodeRDF(Sensor t, MediaType mt) {
        /* TODO implement io.car.server.rest.encoding.SensorEncoder.encodeRDF() */
        throw new UnsupportedOperationException("io.car.server.rest.encoding.SensorEncoder.encodeRDF() not yet implemented");
    }
}
