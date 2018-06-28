package com.byhovsky.agency.repository.impl;

import com.byhovsky.agency.entity.Order;
import com.byhovsky.agency.exception.RepositoryException;
import com.byhovsky.agency.repository.Repository;

import java.util.List;
import java.util.Optional;

@Deprecated
public class OrderRepositoryImpl implements Repository<Order> {

    private List<Order> orders;

    public OrderRepositoryImpl(List<Order> orders) {
        this.orders = orders;
    }

    /**
     * Constructs a new OrderRepositoryImpl.
     */
    public OrderRepositoryImpl() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Order> add(Order order) throws RepositoryException {
        if (orders.contains(order)) {
            throw new RepositoryException("Cant add order");
        } else {
            orders.add(order);
            return Optional.of(order);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Order> getAll() {
        return orders;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean remove(Order order) throws RepositoryException {
        if (orders.contains(order)) {
            orders.remove(order);
            return true;
        } else {
            throw new RepositoryException("Cant remove order");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Order> update(Order order) throws RepositoryException {
        if (orders.get(order.getOrderId()) != null) {
            orders.set(order.getOrderId(), order);
            return Optional.of(order);
        } else {
            throw new RepositoryException("Cant update order");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Order> read(Integer id) {
        throw new UnsupportedOperationException();
    }
}
