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
package org.envirocar.server.rest.encoding.rdf.linker;

import javax.ws.rs.core.UriBuilder;

import org.envirocar.server.core.GroupService;
import org.envirocar.server.core.UserService;
import org.envirocar.server.core.entities.Group;
import org.envirocar.server.core.entities.User;
import org.envirocar.server.rest.encoding.rdf.RDFLinker;
import org.envirocar.server.rest.resources.RootResource;
import org.envirocar.server.rest.resources.UsersResource;
import org.envirocar.server.rest.rights.AccessRights;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.sparql.vocabulary.FOAF;

/**
 *
 * @author Arne de Wall
 *
 */
public class GroupFOAFLinker implements RDFLinker<Group> {
    public static final String PREFIX = "foaf";
    private final GroupService groupService;
    private final UserService userService;

    @Inject
    public GroupFOAFLinker(GroupService groupService, UserService userService) {
        this.groupService = groupService;
        this.userService = userService;
    }

    @Override
    public void link(Model m, Group t, AccessRights rights,
                     String uri, Provider<UriBuilder> uriBuilder) {
        UriBuilder userURIBuilder = uriBuilder.get().path(RootResource.class)
                .path(RootResource.USERS).path(UsersResource.USER);

        m.setNsPrefix(PREFIX, FOAF.NS);
        Resource p = m.createResource(uri, FOAF.Group);
        p.addLiteral(FOAF.name, t.getName());
        p.addProperty(FOAF.maker, m.createResource(
                userURIBuilder.build(t.getOwner().getName()).toASCIIString(),
                FOAF.Person));
        for (User u : groupService.getGroupMembers(t, null)) {
            p.addProperty(FOAF.member, m.createResource(
                    userURIBuilder.build(u.getName()).toASCIIString(),
                    FOAF.Person));
        }
    }
}
