package com.byhovsky.agency.repository;

import com.byhovsky.agency.entity.AbstractEntity;
import com.byhovsky.agency.exception.RepositoryException;

import java.util.List;
import java.util.Optional;

/**
 * Intreface that describes structure of classes that included in Repository layer
 *
 * @param <V> type of entity
 * @author Denis Byhovsky
 */
public interface Repository<V extends AbstractEntity> {

    /**
     * Method that add  entity  to List
     *
     * @param entity
     * @return V
     * @throws RepositoryException
     */
    Optional<V> add(V entity) throws RepositoryException;

    /**
     * Method that get all entities from List
     *
     * @return List
     */
    List<V> getAll();

    /**
     * Method that remove  entity  from List
     *
     * @param entity
     * @return boolean
     * @throws RepositoryException
     */
    boolean remove(V entity) throws RepositoryException;

    /**
     * Method that update  entity  in List
     *
     * @param entity
     * @return V
     * @throws RepositoryException
     */
    Optional<V> update(V entity) throws RepositoryException;

    /**
     * Method that read  entities
     *
     * @param id
     * @return V
     */
    Optional<V> read(Integer id);
}
