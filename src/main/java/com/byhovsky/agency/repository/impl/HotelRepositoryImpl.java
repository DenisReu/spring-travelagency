package com.byhovsky.agency.repository.impl;

import com.byhovsky.agency.entity.Hotel;
import com.byhovsky.agency.exception.RepositoryException;
import com.byhovsky.agency.repository.Repository;

import java.util.List;
import java.util.Optional;

/**
 * HotelRepositoryImpl provides the methods of
 * manipulating  hotels data
 *
 * @author Denis Byhovsky
 */
@Deprecated
public class HotelRepositoryImpl implements Repository<Hotel> {

    private List<Hotel> hotels;

    public HotelRepositoryImpl(List<Hotel> hotels) {
        this.hotels = hotels;
    }

    /**
     * Constructs a new HotelRepositoryImpl.
     */
    public HotelRepositoryImpl() {
    }

    /**
     * Getter for property 'hotels'.
     *
     * @return Value for property 'hotels'.
     */
    public List<Hotel> getHotels() {
        return hotels;
    }

    /**
     * Setter for property 'hotels'.
     *
     * @param hotels Value to set for property 'hotels'.
     */
    public void setHotels(List<Hotel> hotels) {
        this.hotels = hotels;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Hotel> add(Hotel hotel) throws RepositoryException {
        if (hotels.contains(hotel)) {
            throw new RepositoryException("Cant add hotel");
        } else {
            hotels.add(hotel);
            return Optional.of(hotel);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Hotel> getAll() {
        return hotels;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean remove(Hotel hotel) throws RepositoryException {
        if (hotels.contains(hotel)) {
            hotels.remove(hotel);
            return true;
        } else {
            throw new RepositoryException("Cant remove hotel");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Hotel> update(Hotel hotel) throws RepositoryException {
        if (hotels.get(hotel.getHotelId()) != null) {
            hotels.set(hotel.getHotelId(), hotel);
            return Optional.of(hotel);
        } else {
            throw new RepositoryException("Cant update hotel");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Hotel> read(Integer id) {
        throw new UnsupportedOperationException();
    }
}
