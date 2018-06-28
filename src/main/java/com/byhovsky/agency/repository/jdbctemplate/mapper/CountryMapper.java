package com.byhovsky.agency.repository.jdbctemplate.mapper;

import com.byhovsky.agency.entity.Country;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * CountryMapper
 *
 * @author Denis Byhovsky
 */
public class CountryMapper implements RowMapper<Country> {
    /**
     * map Row
     *
     * @param resultSet
     * @param i
     * @return Country
     * @author Denis Byhovsky
     */
    @Override
    public Country mapRow(ResultSet resultSet, int i) throws SQLException {
        Country country = new Country();
        country.setId(resultSet.getInt("id"));
        country.setName(resultSet.getString("name"));
        return country;
    }
}
