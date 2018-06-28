package com.byhovsky.travelagency.service;

import com.byhovsky.agency.entity.*;
import com.byhovsky.agency.exception.RepositoryException;
import com.byhovsky.agency.repository.Repository;
import com.byhovsky.agency.service.impl.ReviewServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

/**
 * ReviewServiceTest
 *
 * @author Denis Byhovsky
 */
@RunWith(org.mockito.junit.MockitoJUnitRunner.class)
public class ReviewServiceTest {

    @InjectMocks
    private ReviewServiceImpl reviewService;
    @Mock
    private Repository<Review> reviewRepository;

    private CopyOnWriteArrayList<Review> reviews;
    private Review review;
    private Review review1;
    private Tour tour;
    private Country country;
    private Hotel hotel;

    @Before
    public void init() {
        reviewService = new ReviewServiceImpl(reviewRepository);
        reviews = new CopyOnWriteArrayList<>();
        country = new Country("Russia");
        hotel = new Hotel(1, "Russia-hotel", "37434", 4);
        tour = new Tour(18, "1.jpg", new BigDecimal(343), "For two person", 5, country, TourType.BICUCLE, hotel, LocalDate.of(2017, 05, 06));
        review = new Review("good", tour);
        review1 = new Review("perfect", tour);
        reviews.add(review);
        reviews.add(review1);
    }

    @Test
    public void addReviewTest() throws RepositoryException {
        when(reviewRepository.add(review)).thenReturn(Optional.of(review));
        assertEquals(review, reviewService.create(review));
    }

    @Test
    public void addReviewVerTest() throws RepositoryException {
        reviewRepository.add(review);
        verify(reviewRepository, times(1)).add(review);
    }

    @Test
    public void updateReviewTest() throws RepositoryException {
        when(reviewRepository.update(review)).thenReturn(Optional.of(review));
        assertEquals(review, reviewService.update(review));
    }

    @Test
    public void updateReviewVerTest() throws RepositoryException {
        reviewRepository.update(review);
        verify(reviewRepository, times(1)).update(review);
    }

    @Test
    public void deleteReviewTest() throws RepositoryException {
        when(reviewRepository.remove(review)).thenReturn(true);
        assertTrue(reviewService.delete(review));
    }

    @Test
    public void deleteReviewVerTest() throws RepositoryException {
        reviewRepository.remove(review);
        verify(reviewRepository, times(1)).remove(review);
    }
}
