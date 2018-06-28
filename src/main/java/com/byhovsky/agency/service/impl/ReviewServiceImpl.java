package com.byhovsky.agency.service.impl;

import com.byhovsky.agency.entity.Review;
import com.byhovsky.agency.exception.RepositoryException;
import com.byhovsky.agency.repository.Repository;
import com.byhovsky.agency.service.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

/**
 * ReviewServiceImpl  provides the methods of
 * service layer
 *
 * @author Denis Byhovsky
 */
@org.springframework.stereotype.Service
public class ReviewServiceImpl extends AbstractService<Review> implements Service<Review> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReviewServiceImpl.class.getName());

    private Repository<Review> repository;

    @Autowired
    public ReviewServiceImpl(Repository<Review> repository) {
        super(repository);
        this.repository = repository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Review create(Review review) {
        Optional<Review> optionalReview = repository.add(review);
        LOGGER.info("Review was created successfully");
        return optionalReview.orElseThrow(
                () -> new RepositoryException("Cant create review in review update method")
        );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Review update(Review review) {
        Optional<Review> optionalReview = repository.update(review);
        LOGGER.info("Review was updated successfully");
        return optionalReview.orElseThrow(
                () -> new RepositoryException("Cant update review in review update method")
        );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean delete(Review review) throws RepositoryException {
        repository.remove(review);
        LOGGER.info("Review was deleted successfully");
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Review> read() {
        return repository.getAll();
    }
}
