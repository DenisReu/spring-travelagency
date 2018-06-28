package com.byhovsky.agency.repository.jdbctemplate;

import com.byhovsky.agency.entity.Order;
import com.byhovsky.agency.entity.User;
import com.byhovsky.agency.exception.RepositoryException;
import com.byhovsky.agency.repository.Repository;
import com.byhovsky.agency.repository.jdbctemplate.mapper.OrderMapper;
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
import java.util.*;

/**
 * UserTemplate  describes structure of classes that included in Repository layer
 *
 * @author Denis Byhovsky
 */
@org.springframework.stereotype.Repository
@Transactional(readOnly = true)
public class UserTemplate extends AbstractTemplate implements Repository<User> {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public UserTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public Optional<User> add(User user) throws RepositoryException {
        List<SqlParameter> parameters = Arrays.asList(
                new SqlParameter("login", Types.VARCHAR), new SqlParameter("password", Types.VARCHAR), new SqlOutParameter("id", Types.INTEGER));
        Map<String, Object> t = jdbcTemplate.call(con -> {
            CallableStatement callableStatement = con.prepareCall("{call adduser(?,?,?)}");
            callableStatement.setString(1, user.getLogin());
            callableStatement.setString(2, Arrays.toString(user.getPassword()));
            callableStatement.registerOutParameter(3, Types.INTEGER);
            return callableStatement;
        }, parameters);
        user.setUserId((int) t.get("id"));
        return Optional.of(user);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(SQLConfiguration.UserConfig.FIND_ALL);
        for (Map row : rows) {
            User user = (read((int) (row.get("user_id")))).get();
            users.add(user);
        }
        return users;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public boolean remove(User user) throws RepositoryException {
        try {
            jdbcTemplate.update(SQLConfiguration.UserConfig.REMOVE_USER, user.getUserId());
            return true;
        } catch (RuntimeException e) {
            throw new RepositoryException(e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public Optional<User> update(User user) throws RepositoryException {
        jdbcTemplate.update(SQLConfiguration.UserConfig.UPDATE_USER, user.getLogin(), Arrays.toString(user.getPassword()), user.getUserId());
        return Optional.of(user);
    }

    private List<Order> findAllOrders(Integer id) {
        List<Order> userOrders = new ArrayList<>();
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(SQLConfiguration.UserConfig.ORDER_BY_USER_ID, id);
        for (Map row : rows) {
            int orderId = (int) (row.get("order_id"));
            Order order = jdbcTemplate.queryForObject(SQLConfiguration.UserConfig.ORDER_BY_ID, new Object[]{orderId}, (resultSet, i) -> new OrderMapper().mapRow(resultSet, i));
            userOrders.add(order);
        }
        return userOrders;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<User> read(Integer id) {
        return jdbcTemplate.queryForObject(SQLConfiguration.UserConfig.READ_BY_ID, new Object[]{id}, (resultSet, i) -> {
            User user = (new BeanPropertyRowMapper<>(User.class).mapRow(resultSet, i));
            user.setUserId(id);
            user.setOrders(findAllOrders(user.getUserId()));
            return Optional.of(user);
        });
    }
}
