/*
 * Copyright 2016 RedRoma, Inc..
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

 
package tech.redroma.yelp;


import tech.sirwellington.alchemy.annotations.concurrency.Mutable;
import tech.sirwellington.alchemy.annotations.concurrency.ThreadUnsafe;
import tech.sirwellington.alchemy.annotations.objects.Pojo;

import static tech.sirwellington.alchemy.arguments.Arguments.checkThat;
import static tech.sirwellington.alchemy.arguments.assertions.GeolocationAssertions.validLatitude;
import static tech.sirwellington.alchemy.arguments.assertions.GeolocationAssertions.validLongitude;

/**
 * Represents a Geo-Coordinate of latitude and longitude;
 * 
 * @author SirWellington
 */
@Pojo 
@Mutable
@ThreadUnsafe
public class Coordinate 
{

    double latitude;
    double longitude;

    Coordinate()
    {
    }
    
    public Coordinate(double latitude, double longitude)
    {
        checkThat(latitude).is(validLatitude());
        checkThat(longitude).is(validLongitude());
        
        this.latitude = latitude;
        this.longitude = longitude;
    }

    /**
     * Creates a {@code Coordinate} object from the specified latitude and longitude.
     * 
     * @param latitude  A valid latitude: {@code (-90...90)}.
     * @param longitude A valid longitude: {@code (-180...180)}.
     * @return
     * @throws IllegalArgumentException If the coordinate is invalid.
     */
    public static Coordinate of(double latitude, double longitude) throws IllegalArgumentException
    {
        return new Coordinate(latitude, longitude);
    }
    
    public double getLatitude()
    {
        return latitude;
    }

    public double getLongitude()
    {
        return longitude;
    }
    
    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 59 * hash + (int) (Double.doubleToLongBits(this.latitude) ^ (Double.doubleToLongBits(this.latitude) >>> 32));
        hash = 59 * hash + (int) (Double.doubleToLongBits(this.longitude) ^ (Double.doubleToLongBits(this.longitude) >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
        {
            return true;
        }
        if (obj == null)
        {
            return false;
        }
        if (getClass() != obj.getClass())
        {
            return false;
        }
        final Coordinate other = (Coordinate) obj;
        if (Double.doubleToLongBits(this.latitude) != Double.doubleToLongBits(other.latitude))
        {
            return false;
        }
        if (Double.doubleToLongBits(this.longitude) != Double.doubleToLongBits(other.longitude))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "Coordinate{" + "latitude=" + latitude + ", longitude=" + longitude + '}';
    }

}
