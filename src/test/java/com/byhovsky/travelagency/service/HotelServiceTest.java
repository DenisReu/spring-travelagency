package com.byhovsky.travelagency.service;

import com.byhovsky.agency.entity.Country;
import com.byhovsky.agency.entity.Hotel;
import com.byhovsky.agency.exception.RepositoryException;
import com.byhovsky.agency.repository.Repository;
import com.byhovsky.agency.service.impl.HotelServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

/**
 * HotelServiceTest
 *
 * @author Denis Byhovsky
 */
@RunWith(org.mockito.junit.MockitoJUnitRunner.class)
public class HotelServiceTest {

    @InjectMocks
    private HotelServiceImpl hotelService;
    @Mock
    private Repository<Hotel> hotelRepository;

    private CopyOnWriteArrayList<Hotel> hotels;
    private Hotel hotel;
    private Hotel hotel1;
    private Country country;

    @Before
    public void init() {
        hotelService = new HotelServiceImpl(hotelRepository);
        hotels = new CopyOnWriteArrayList<>();
        country = new Country("Russia");
        hotel = new Hotel(2, "Russia-hotel", "37434", 4);
        hotel1 = new Hotel(1, "Belarus-hotel", "33243", 3);
        hotels.add(hotel);
        hotels.add(hotel1);
    }

    @Test
    public void addHotelTest() throws RepositoryException {
        when(hotelRepository.add(hotel)).thenReturn(Optional.of(hotel));
        assertEquals(hotel, hotelService.create(hotel));
    }

    @Test
    public void addHotelVerTest() throws RepositoryException {
        hotelRepository.add(hotel);
        verify(hotelRepository, times(1)).add(hotel);
    }

    @Test
    public void updateHotelTest() throws RepositoryException {
        when(hotelRepository.update(hotel)).thenReturn(Optional.of(hotel));
        assertEquals(hotel, hotelService.update(hotel));
    }

    @Test
    public void updateHotelVerTest() throws RepositoryException {
        hotelRepository.update(hotel);
        verify(hotelRepository, times(1)).update(hotel);
    }

    @Test
    public void deleteHotelTest() throws RepositoryException {
        when(hotelRepository.remove(hotel)).thenReturn(true);
        assertTrue(hotelService.delete(hotel));
    }

    @Test
    public void deleteHotelVerTest() throws RepositoryException {
        hotelRepository.remove(hotel);
        verify(hotelRepository, times(1)).remove(hotel);
    }
}

