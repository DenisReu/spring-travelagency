package com.byhovsky.agency.repository.jdbctemplate.mapper;

import com.byhovsky.agency.entity.Country;
import com.byhovsky.agency.entity.Hotel;
import com.byhovsky.agency.entity.Order;
import com.byhovsky.agency.entity.Tour;
import com.byhovsky.agency.repository.jdbctemplate.AbstractTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * OrderMapper
 *
 * @author Denis Byhovsky
 */
public class OrderMapper implements RowMapper<Order> {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderMapper.class.getName());

    /**
     * map Row
     *
     * @param resultSet
     * @param i
     * @return Order
     * @author Denis Byhovsky
     */

    @Override
    public Order mapRow(ResultSet resultSet, int i) throws SQLException {
        Country country = (new CountryMapper().mapRow(resultSet, i));
        Hotel hotel = (new HotelMapper().mapRow(resultSet, i));
        Order order = (new BeanPropertyRowMapper<>(Order.class).mapRow(resultSet, i));
        Tour tour = (new BeanPropertyRowMapper<>(Tour.class).mapRow(resultSet, i));
        hotel.setCountry(country);
        tour.setHotel(hotel);
        tour.setCountry(country);
        order.setTour(tour);
        LOGGER.info("Get order successfully");
        return order;
    }
}
