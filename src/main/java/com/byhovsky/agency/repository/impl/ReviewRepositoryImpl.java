package com.byhovsky.agency.repository.impl;

import com.byhovsky.agency.entity.Review;
import com.byhovsky.agency.exception.RepositoryException;
import com.byhovsky.agency.repository.Repository;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * ReviewRepositoryImpl provides the methods of
 * manipulating  reviews  data
 *
 * @author Denis Byhovsky
 */
@Deprecated
public class ReviewRepositoryImpl implements Repository<Review> {

    private List<Review> reviews;

    public ReviewRepositoryImpl(List<Review> reviews) {
        this.reviews = reviews;
    }

    /**
     * Constructs a new ReviewRepositoryImpl.
     */
    public ReviewRepositoryImpl() {
    }

    /**
     * Getter for property 'reviews'.
     *
     * @return Value for property 'reviews'.
     */
    public List<Review> getReviews() {
        return reviews;
    }

    /**
     * Setter for property 'reviews'.
     *
     * @param reviews Value to set for property 'reviews'.
     */
    public void setReviews(CopyOnWriteArrayList<Review> reviews) {
        this.reviews = reviews;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Review> add(Review review) throws RepositoryException {
        if (reviews.contains(review)) {
            throw new RepositoryException("Cant add review");
        } else {
            reviews.add(review);
            return Optional.of(review);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Review> getAll() {
        return reviews;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean remove(Review review) throws RepositoryException {
        if (reviews.contains(review)) {
            reviews.remove(review);
            return true;
        } else {
            throw new RepositoryException("Cant remove review");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Review> update(Review review) throws RepositoryException {
        if (reviews.get(review.getReviewId()) != null) {
            reviews.set(review.getReviewId(), review);
            return Optional.of(review);
        } else {
            throw new RepositoryException("Cant update review");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Review> read(Integer id) {
        throw new UnsupportedOperationException();
    }
}
