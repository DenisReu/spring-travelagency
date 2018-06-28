package com.byhovsky.travelagency.repository;


import com.byhovsky.agency.entity.Country;
import com.byhovsky.agency.entity.Hotel;
import com.byhovsky.agency.exception.RepositoryException;
import com.byhovsky.agency.repository.impl.HotelRepositoryImpl;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * HotelRepositoryTest
 *
 * @author Denis Byhovsky
 */
public class HotelRepositoryTest {

    private HotelRepositoryImpl hotelRepository;
    private Hotel hotel;
    private Hotel hotel1;
    private Hotel hotel3;
    private Country country;

    @Before
    public void init() {
        CopyOnWriteArrayList<Hotel> hotels = new CopyOnWriteArrayList<>();
        country = new Country("Russia");
        hotel = new Hotel(1, "Russia-hotel", "37434", 4);
        hotel1 = new Hotel(2, "Belarus-hotel", "33243", 3);
        hotel3 = new Hotel(1, "Italy-hotel", "33243", 2);
        hotels.add(hotel);
        hotels.add(hotel1);
        hotelRepository = new HotelRepositoryImpl(hotels);
    }

    @Test
    public void addHotelTest() throws RepositoryException {
        hotel3 = new Hotel(4, "Italy-hotel", "33243", 2);
        Optional<Hotel> optionalHotel = hotelRepository.add(hotel3);
        Assert.assertTrue(optionalHotel.isPresent());
        Assert.assertEquals(hotel3, optionalHotel.get());
    }

    @Test
    public void readHotelTest() {
        Assert.assertNotNull(hotelRepository.getAll());
    }

    @Test
    public void updateHotelTest() throws RepositoryException {
        Optional<Hotel> optionalHotel = hotelRepository.update(hotel3);
        Assert.assertTrue(optionalHotel.isPresent());
        Assert.assertEquals(hotel3, optionalHotel.get());
    }

    @Test
    public void deleteHotelTest() throws RepositoryException {
        Assert.assertTrue(hotelRepository.remove(hotel));
    }
}
