package com.byhovsky.agency.repository.jdbctemplate.mapper;

import com.byhovsky.agency.entity.Country;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * HotelCountryMapper
 *
 * @author Denis Byhovsky
 */
public class HotelCountryMapper implements RowMapper<Country> {
    @Override
    public Country mapRow(ResultSet resultSet, int i) throws SQLException {
        Country country = new Country();
        country.setId(resultSet.getInt("country_id"));
        country.setName(resultSet.getString("name"));
        return country;
    }
}
