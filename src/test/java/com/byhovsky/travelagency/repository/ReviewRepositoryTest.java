package com.byhovsky.travelagency.repository;

import com.byhovsky.agency.entity.*;
import com.byhovsky.agency.exception.RepositoryException;
import com.byhovsky.agency.repository.impl.ReviewRepositoryImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * ReviewRepositoryTest
 *
 * @author Denis Byhovsky
 */
public class ReviewRepositoryTest {

    private ReviewRepositoryImpl reviewRepository;
    private Review review;
    private Review review1;
    private Review review3;
    private Tour tour;
    private Country country;
    private Hotel hotel;

    @Before
    public void init() {
        CopyOnWriteArrayList<Review> reviews = new CopyOnWriteArrayList<>();
        country = new Country("Russia");
        hotel = new Hotel(3,"Russia-hotel", "37434",  4);
        tour = new Tour( 19,"1.jpg", new BigDecimal(343), "For two person", 5, country, TourType.BICUCLE, hotel,LocalDate.of(2017, 05, 06));
        review = new Review("good", tour);
        review1 = new Review( "perfect", tour);
        reviews.add(review);
        reviews.add(review1);
        reviewRepository = new ReviewRepositoryImpl(reviews);
    }

    @Test
    public void addReviewTest() throws RepositoryException {
        review3 = new Review("so-so", tour);
        Optional<Review> optionalReview = reviewRepository.add(review3);
        Assert.assertTrue(optionalReview.isPresent());
        Assert.assertEquals(review3, optionalReview.get());
    }

    @Test
    public void readReviewTest() {
        Assert.assertNotNull(reviewRepository.getAll());
    }

    @Test
    public void updateReviewTest() throws RepositoryException {
        review3 = new Review( "so-so", tour);
        Optional<Review> optionalReview = reviewRepository.update(review3);
        Assert.assertTrue(optionalReview.isPresent());
        Assert.assertEquals(review3, optionalReview.get());
    }

    @Test
    public void deleteReviewTest() throws RepositoryException {
        Assert.assertTrue(reviewRepository.remove(review));
    }
}
