package com.byhovsky.agency.repository.jdbctemplate.mapper;


import com.byhovsky.agency.entity.Hotel;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * HotelMapper
 *
 * @author Denis Byhovsky
 */
public class HotelMapper implements RowMapper<Hotel> {

    /**
     * map Row
     *
     * @param resultSet
     * @param i
     * @return Hotel
     * @author Denis Byhovsky
     */
    @Override
    public Hotel mapRow(ResultSet resultSet, int i) throws SQLException {
        Hotel hotel = new Hotel();
        hotel.setHotelId(resultSet.getInt("hotel_id"));
        hotel.setHotelName(resultSet.getString("hotel_name"));
        hotel.setPhone(resultSet.getString("phone"));
        hotel.setStars(resultSet.getInt("stars"));
        hotel.setCountry(new HotelCountryMapper().mapRow(resultSet, i));
        return hotel;
    }
}
