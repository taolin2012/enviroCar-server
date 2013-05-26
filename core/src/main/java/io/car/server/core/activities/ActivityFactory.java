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
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package io.car.server.core.activities;

import com.google.inject.assistedinject.Assisted;

import io.car.server.core.entities.Group;
import io.car.server.core.entities.Measurement;
import io.car.server.core.entities.Track;
import io.car.server.core.entities.User;

/**
 *
 * @author Christian Autermann <autermann@uni-muenster.de>
 */
public interface ActivityFactory {
    Activity createActivity(ActivityType type,
                            User user);

    GroupActivity createGroupActivity(ActivityType type,
                                      User user,
                                      Group group);

    MeasurementActivity createMeasurementActivity(ActivityType type,
                                                  User user,
                                                  Measurement measurement);

    TrackActivity createTrackActivity(ActivityType type,
                                      User user,
                                      Track track);

    UserActivity createUserActivity(ActivityType type,
                                    @Assisted("user") User user,
                                    @Assisted("other") User other);
}
