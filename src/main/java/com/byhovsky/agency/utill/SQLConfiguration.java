package com.byhovsky.agency.utill;

/**
 * SQLConfiguration define all queries with database
 *
 * @author Denis Byhovsky
 */
public class SQLConfiguration {

    /**
     * CountryConfig  define  all queries for table country
     */
    public static class CountryConfig {
        public static final String UPDATE_COUNTRY = "UPDATE  country SET name = ? WHERE id =?";
        public static final String FIND_ALL = "SELECT id , name FROM country";
        public static final String READ_BY_ID = "SELECT id , name FROM country WHERE id  = ?";
        public static final String REMOVE_COUNTRY = "DELETE FROM country WHERE id= ?";
    }

    /**
     * HotelConfig  define  all queries for table hotel
     */
    public static class HotelConfig {
        public static final String FIND_ALL = "SELECT hotel_id, hotel_name, stars, phone  FROM hotel";
        public static final String UPDATE_HOTEL = "UPDATE  hotel SET hotel_name = ? ,phone = ?, stars = ?, country_id = ? WHERE hotel_id = ?";
        public static final String DELETE_HOTEL = "DELETE FROM hotel WHERE hotel_id =?";
        public static final String READ_BY_ID = "SELECT hotel_id ,hotel_name ,phone,stars, country_id ,name FROM hotel JOIN country ON hotel.country_id=country.id  WHERE  hotel_id  = ?";
    }

    /**
     * OrderConfig  define all queries for table order
     */
    public static class OrderConfig {
        public static final String FIND_ALL = "SELECT order_id   FROM a_order";
        public static final String REMOVE_ORDER = "DELETE FROM a_order WHERE order_id = ?";
        public static final String UPDATE_ORDER = "UPDATE  a_order SET  user_id = ?, tour_id = ?, amt = ? WHERE order_id = ?";
        public static final String READ_BY_ID = "SELECT order_id ,user_id,amt,tour.tour_id ,description,cost,type,photo,date, hotel.hotel_id,hotel.hotel_name,phone,stars,duration, country.id,country.name FROM a_order JOIN tour ON a_order.tour_id=tour.tour_id JOIN hotel ON tour.hotel_id=hotel.hotel_id JOIN country ON tour.country_id = country.id WHERE order_id = ?";
    }

    /**
     * ReviewConfig  define  all queries for table review
     */
    public static class ReviewConfig {
        public static final String FIND_ALL = "SELECT  review_id,content FROM review";
        public static final String UPDATE_REVIEW = "UPDATE  review SET  user_id = ?, tour_id = ?, content = ? WHERE review_id = ?";
        public static final String REMOVE_REVIEW = "DELETE FROM review WHERE review_id = ?";
        public static final String READ_BY_ID = "SELECT content ,review_id , user_id  FROM review  WHERE  review_id  = ?";
    }

    /**
     * TourConfig define  all queries for table tour
     */
    public static class TourConfig {
        public static final String REMOVE_TOUR = "DELETE FROM tour WHERE tour_id = ?";
        public static final String FIND_ALL = "SELECT tour_id, description  FROM tour";
        public static final String UPDATE_TOUR = "UPDATE  tour SET description = ?, cost = ?, country_id = ?,hotel_id=?,type=?,photo=? , date = ?  WHERE tour_id = ?";
    }

    /**
     * UserConfig  define  all queries for table user
     */
    public static class UserConfig {
        public static final String READ_BY_ID = "SELECT login,password FROM a_user WHERE user_id = ?";
        public static final String FIND_ALL = "SELECT user_id  FROM a_user";
        public static final String REMOVE_USER = "DELETE FROM a_user WHERE user_id = ?";
        public static final String UPDATE_USER = "UPDATE  a_user SET login=?,password=?  WHERE user_id = ?";
        public static final String ORDER_BY_USER_ID = "SELECT order_id FROM a_order WHERE user_id = ?";
        public static final String ORDER_BY_ID = "SELECT order_id ,user_id ,amt,tour.tour_id ,description,cost,type,photo,date,phone,stars,duration,country.name FROM a_order JOIN tour ON a_order.tour_id=tour.tour_id JOIN hotel ON tour.hotel_id=hotel.hotel_id JOIN country  ON tour.country_id = country.id WHERE order_id = ?";
    }
}

