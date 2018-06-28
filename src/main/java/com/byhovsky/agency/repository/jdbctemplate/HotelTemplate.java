package com.byhovsky.agency.repository.jdbctemplate;

import com.byhovsky.agency.entity.Hotel;
import com.byhovsky.agency.exception.RepositoryException;
import com.byhovsky.agency.repository.Repository;
import com.byhovsky.agency.repository.jdbctemplate.mapper.HotelMapper;
import com.byhovsky.agency.utill.SQLConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.CallableStatement;
import java.sql.Types;
import java.util.*;

/**
 * HotelTemplate  describes structure of classes that included in Repository layer
 *
 * @author Denis Byhovsky
 */

@org.springframework.stereotype.Repository
@Transactional(readOnly = true)
public class HotelTemplate extends AbstractTemplate implements Repository<Hotel> {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public HotelTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public Optional<Hotel> add(Hotel hotel) throws RepositoryException {
        List<SqlParameter> parameters = Arrays.asList(
                new SqlParameter("hotel_name", Types.VARCHAR), new SqlParameter("phone", Types.VARCHAR), new SqlParameter("stars", Types.INTEGER), new SqlParameter("country_id", Types.INTEGER), new SqlOutParameter("id", Types.INTEGER));
        Map<String, Object> t = jdbcTemplate.call(con -> {
            CallableStatement callableStatement = con.prepareCall("{call addhotel(?,?,?,?)}");
            callableStatement.setString(1, hotel.getHotelName());
            callableStatement.setString(2, hotel.getPhone());
            callableStatement.setInt(3, hotel.getStars());
            callableStatement.setInt(4, hotel.getCountry().getId());
            callableStatement.registerOutParameter(5, Types.INTEGER);
            return callableStatement;
        }, parameters);
        hotel.setHotelId((int) t.get("id"));
        return Optional.of(hotel);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Hotel> getAll() {
        List<Hotel> hotels = new ArrayList<>();
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(SQLConfiguration.HotelConfig.FIND_ALL);
        for (Map row : rows) {
            Hotel hotel = (read((Integer) row.get("hotel_id"))).get();
            hotels.add(hotel);
        }
        return hotels;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Hotel> read(Integer id) {
        return jdbcTemplate.queryForObject(SQLConfiguration.HotelConfig.READ_BY_ID, new Object[]{id}, (resultSet, i) -> {
            Hotel hotel = (new HotelMapper().mapRow(resultSet, i));
            return Optional.of(hotel);
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public boolean remove(Hotel hotel) throws RepositoryException {
        try {
            jdbcTemplate.update(SQLConfiguration.HotelConfig.DELETE_HOTEL, hotel.getHotelId());
            return true;
        } catch (RuntimeException e) {
            throw new RepositoryException("Error in delete hotel template" + e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public Optional<Hotel> update(Hotel hotel) throws RepositoryException {
        try {
            jdbcTemplate.update(SQLConfiguration.HotelConfig.UPDATE_HOTEL, hotel.getHotelName(), hotel.getPhone(), hotel.getStars(), hotel.getCountry().getId(), hotel.getHotelId());
            return Optional.of(hotel);
        } catch (RuntimeException e) {
            throw new RepositoryException("Error in hotel updating template" + e);
        }
    }
}
