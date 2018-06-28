package com.byhovsky.agency.service;

import com.byhovsky.agency.entity.AbstractEntity;
import com.byhovsky.agency.exception.RepositoryException;
import com.byhovsky.agency.exception.ServiceException;

import java.util.List;

/**
 * Intreface that describes structure of Service layer
 *
 * @author Denis Byhovsky
 */
public interface Service<T extends AbstractEntity> {
    /**
     * Method create
     *
     * @param entity
     * @return T
     * @throws RepositoryException
     * @throws ServiceException
     */
    T create(T entity) throws RepositoryException, ServiceException;

    /**
     * Method read
     *
     * @return List
     */
    List<T> read();

    /**
     * Method update
     *
     * @param entity
     * @return T
     * @throws RepositoryException
     * @throws ServiceException
     */
    T update(T entity) throws RepositoryException, ServiceException;

    /**
     * Method delete
     *
     * @param entity
     * @return boolean
     * @throws RepositoryException
     */
    boolean delete(T entity) throws RepositoryException;
}
