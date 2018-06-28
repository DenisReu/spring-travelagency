package com.byhovsky.agency.repository.jdbctemplate;

import com.byhovsky.agency.entity.Order;
import com.byhovsky.agency.exception.RepositoryException;
import com.byhovsky.agency.repository.Repository;
import com.byhovsky.agency.repository.jdbctemplate.mapper.OrderMapper;
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
 * OrderTemplate  describes structure of classes that included in Repository layer
 *
 * @author Denis Byhovsky
 */
@org.springframework.stereotype.Repository
@Transactional(readOnly = true)
public class OrderTemplate extends AbstractTemplate implements Repository<Order> {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public OrderTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public Optional<Order> add(Order order) throws RepositoryException {
        List<SqlParameter> parameters = Arrays.asList(
                new SqlParameter("amt", Types.VARCHAR), new SqlParameter("user_id", Types.INTEGER), new SqlParameter("tour_id", Types.INTEGER), new SqlOutParameter("order_id", Types.INTEGER));
        Map<String, Object> t = jdbcTemplate.call(con -> {
            CallableStatement callableStatement = con.prepareCall("{call addorder(?,?,?,?,?)}");
            callableStatement.setInt(1, order.getQuantity());
            callableStatement.setInt(3, order.getUserId());
            callableStatement.setInt(2, order.getTour().getTourId());
            callableStatement.registerOutParameter(4, Types.INTEGER);
            return callableStatement;
        }, parameters);
        order.setOrderId((int) t.get("order_id"));
        return Optional.of(order);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Order> getAll() {
        List<Order> orders = new ArrayList<>();
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(SQLConfiguration.OrderConfig.FIND_ALL);
        for (Map row : rows) {
            Order order = (read((int) (row.get("order_id")))).get();
            orders.add(order);
        }
        return orders;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public boolean remove(Order order) throws RepositoryException {
        try {
            jdbcTemplate.update(SQLConfiguration.OrderConfig.REMOVE_ORDER, order.getOrderId());
            return true;
        } catch (RuntimeException e) {
            throw new RepositoryException("Error in removing order template" + e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public Optional<Order> update(Order order) throws RepositoryException {
        try {
            jdbcTemplate.update(SQLConfiguration.OrderConfig.UPDATE_ORDER, order.getUserId(), order.getTour().getTourId(), order.getQuantity(), order.getOrderId());
            return Optional.of(order);
        } catch (RuntimeException e) {
            throw new RepositoryException("Error in updating order template");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Order> read(Integer id) {
        return jdbcTemplate.queryForObject(SQLConfiguration.OrderConfig.READ_BY_ID, new Object[]{id}, (resultSet, i) -> {
            Order order = (new OrderMapper().mapRow(resultSet, i));
            return Optional.of(order);
        });
    }
}
