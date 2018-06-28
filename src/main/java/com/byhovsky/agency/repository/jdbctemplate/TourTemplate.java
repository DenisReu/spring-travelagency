package com.byhovsky.agency.repository.jdbctemplate;

import com.byhovsky.agency.entity.Tour;
import com.byhovsky.agency.exception.RepositoryException;
import com.byhovsky.agency.repository.Repository;
import com.byhovsky.agency.utill.SQLConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import java.sql.CallableStatement;
import java.sql.Types;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * TourTemplate  describes structure of classes that included in Repository layer
 *
 * @author Denis Byhovsky
 */
@org.springframework.stereotype.Repository
@Transactional(readOnly = true)
public class TourTemplate extends AbstractTemplate implements Repository<Tour> {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public TourTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public Optional<Tour> add(Tour tour) throws RepositoryException {
        List<SqlParameter> parameters = Arrays.asList(
                new SqlParameter("date", Types.DATE), new SqlParameter("description", Types.VARCHAR), new SqlParameter("cost", Types.INTEGER), new SqlParameter("country_id", Types.INTEGER), new SqlParameter("hotel_id", Types.INTEGER), new SqlParameter("type", Types.VARCHAR), new SqlParameter("photo", Types.VARCHAR), new SqlParameter("duration", Types.INTEGER), new SqlOutParameter("id", Types.INTEGER));
        Map<String, Object> t = jdbcTemplate.call(con -> {
            CallableStatement callableStatement = con.prepareCall("{call addtour(?,?,?,?,?,?,?,?,?)}");
            callableStatement.setObject(1, java.sql.Date.valueOf(tour.getDate()));
            callableStatement.setString(2, tour.getDescription());
            callableStatement.setBigDecimal(3, tour.getCost());
            callableStatement.setInt(4, tour.getCountry().getId());
            callableStatement.setInt(5, tour.getHotel().getHotelId());
            callableStatement.setInt(6, tour.getType().ordinal());
            callableStatement.setString(7, tour.getPhoto());
            callableStatement.setInt(8, tour.getDuration());
            callableStatement.registerOutParameter(9, Types.INTEGER);
            return callableStatement;
        }, parameters);
        tour.setTourId((int) t.get("id"));
        return Optional.of(tour);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Tour> getAll() {
        return jdbcTemplate.query(SQLConfiguration.TourConfig.FIND_ALL, new BeanPropertyRowMapper<>(Tour.class));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public boolean remove(Tour tour) throws RepositoryException {
        try {
            jdbcTemplate.update(SQLConfiguration.TourConfig.REMOVE_TOUR, tour.getTourId());
            return true;
        } catch (RuntimeException e) {
            throw new RepositoryException("Error in remove tour template" + e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public Optional<Tour> update(Tour tour) throws RepositoryException {
        try {
            jdbcTemplate.update(SQLConfiguration.TourConfig.UPDATE_TOUR, tour.getDate(), tour.getDescription(), tour.getCost(), tour.getHotel().getHotelId(), tour.getCountry().getId(), tour.getPhoto(), tour.getType().ordinal(), tour.getTourId());
            return Optional.of(tour);
        } catch (RuntimeException e) {
            throw new RepositoryException("Error in update tour template" + e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Tour> read(Integer id) {
        throw new UnsupportedOperationException();
    }
}
