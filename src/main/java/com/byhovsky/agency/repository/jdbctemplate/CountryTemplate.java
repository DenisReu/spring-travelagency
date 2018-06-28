package com.byhovsky.agency.repository.jdbctemplate;

import com.byhovsky.agency.entity.Country;
import com.byhovsky.agency.exception.RepositoryException;
import com.byhovsky.agency.repository.Repository;
import com.byhovsky.agency.repository.jdbctemplate.mapper.CountryMapper;
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
 * CountryTemplate  describes structure of classes that included in Repository layer
 *
 * @author Denis Byhovsky
 */

@org.springframework.stereotype.Repository
@Transactional(readOnly = true)
public class CountryTemplate extends AbstractTemplate implements Repository<Country> {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public CountryTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public Optional<Country> add(Country country) {
        List<SqlParameter> parameters = Arrays.asList(
                new SqlParameter("name", Types.VARCHAR), new SqlOutParameter("id", Types.INTEGER));
        Map<String, Object> t = jdbcTemplate.call(con -> {
            CallableStatement callableStatement = con.prepareCall("{call addcountry(?,?)}");
            callableStatement.setString(1, country.getName());
            callableStatement.registerOutParameter(2, Types.INTEGER);
            return callableStatement;
        }, parameters);
        country.setId((int) t.get("id"));
        return Optional.of(country);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Country> getAll() {
        return jdbcTemplate.query(SQLConfiguration.CountryConfig.FIND_ALL, new CountryMapper());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public boolean remove(Country country) throws RepositoryException {
        jdbcTemplate.update(SQLConfiguration.CountryConfig.REMOVE_COUNTRY, country.getId());
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public Optional<Country> update(Country country) {
        if (jdbcTemplate.update(SQLConfiguration.CountryConfig.UPDATE_COUNTRY, country.getName(), country.getId()) != 0) {
            return Optional.of(country);
        } else {
            throw new RepositoryException("Such object didnt find");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Country> read(Integer id) {
        return Optional.of(jdbcTemplate.queryForObject(SQLConfiguration.CountryConfig.READ_BY_ID, new Object[]{id}, new BeanPropertyRowMapper<>(Country.class)));
    }
}
