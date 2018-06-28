package com.byhovsky.agency.service.impl;

import com.byhovsky.agency.entity.AbstractEntity;
import com.byhovsky.agency.repository.Repository;
import com.byhovsky.agency.service.Service;

/**
 * AbstractService
 *
 * @author Denis Byhovsky
 */
public abstract class AbstractService<T extends AbstractEntity> implements Service<T> {

    private Repository<T> repository;

    AbstractService(Repository<T> repository) {
        this.repository = repository;
    }
}
