package com.byhovsky.agency.repository.jdbctemplate;

import com.byhovsky.agency.entity.Review;
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
 * OrderTemplate  describes structure of classes that included in Repository layer
 *
 * @author Denis Byhovsky
 */
@org.springframework.stereotype.Repository
@Transactional(readOnly = true)
public class ReviewTemplate extends AbstractTemplate implements Repository<Review> {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public ReviewTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public Optional<Review> add(Review review) throws RepositoryException {
        List<SqlParameter> parameters = Arrays.asList(
                new SqlParameter("user_id", Types.INTEGER), new SqlParameter("tour_id", Types.INTEGER), new SqlParameter("content", Types.VARCHAR), new SqlOutParameter("id", Types.INTEGER));
        Map<String, Object> t = jdbcTemplate.call(con -> {
            CallableStatement callableStatement = con.prepareCall("{call addreview(?,?,?,?,?)}");
            callableStatement.setInt(1, review.getUserId());
            callableStatement.setInt(2, review.getTour().getTourId());
            callableStatement.setString(3, review.getContent());
            callableStatement.registerOutParameter(4, Types.INTEGER);
            return callableStatement;
        }, parameters);
        review.setReviewId((int) t.get("id"));
        return Optional.of(review);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Review> getAll() {
        return jdbcTemplate.query(SQLConfiguration.ReviewConfig.FIND_ALL, new BeanPropertyRowMapper<>(Review.class));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public boolean remove(Review review) throws RepositoryException {
        try {
            jdbcTemplate.update(SQLConfiguration.ReviewConfig.REMOVE_REVIEW, review.getReviewId());
            return true;
        } catch (RuntimeException e) {
            throw new RepositoryException("Error in remove review template");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public Optional<Review> update(Review review) throws RepositoryException {
        jdbcTemplate.update(SQLConfiguration.ReviewConfig.UPDATE_REVIEW, review.getTour().getTourId(), review.getUserId(), review.getReviewId(), review.getContent());
        return Optional.of(review);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Review> read(Integer id) {
        return jdbcTemplate.queryForObject(SQLConfiguration.ReviewConfig.READ_BY_ID, new Object[]{id}, (resultSet, i) -> {
            Review review = (new BeanPropertyRowMapper<>(Review.class).mapRow(resultSet, i));
            return Optional.of(review);
        });
    }
}
