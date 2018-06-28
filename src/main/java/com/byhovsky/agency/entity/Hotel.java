package com.byhovsky.agency.entity;

/**
 * Hotel
 *
 * @author Denis Byhovsky
 */
public class Hotel extends AbstractEntity {
    private static final long serialVersionUID = 1L;
    private int hotelId;
    private String hotelName;
    private String phone;
    private Country country;
    private int stars;

    public Hotel(int hotelId, String hotelName, String phone, int stars) {
        this.hotelId = hotelId;
        this.hotelName = hotelName;
        this.phone = phone;
        this.stars = stars;
    }

    /**
     * Constructs a new Hotel.
     */
    public Hotel() {
    }

    /**
     * Getter for property 'hotelId'.
     *
     * @return Value for property 'hotelId'.
     */
    public int getHotelId() {
        return hotelId;
    }

    /**
     * Setter for property 'hotelId'.
     *
     * @param hotelId Value to set for property 'hotelId'.
     */
    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    /**
     * Getter for property 'hotelName'.
     *
     * @return Value for property 'hotelName'.
     */
    public String getHotelName() {
        return hotelName;
    }

    /**
     * Setter for property 'hotelName'.
     *
     * @param hotelName Value to set for property 'hotelName'.
     */
    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    /**
     * Getter for property 'phone'.
     *
     * @return Value for property 'phone'.
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Setter for property 'phone'.
     *
     * @param phone Value to set for property 'phone'.
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Getter for property 'country'.
     *
     * @return Value for property 'country'.
     */
    public Country getCountry() {
        return country;
    }

    /**
     * Setter for property 'country'.
     *
     * @param country Value to set for property 'country'.
     */
    public void setCountry(Country country) {
        this.country = country;
    }

    /**
     * Getter for property 'stars'.
     *
     * @return Value for property 'stars'.
     */
    public int getStars() {
        return stars;
    }

    /**
     * Setter for property 'stars'.
     *
     * @param stars Value to set for property 'stars'.
     */
    public void setStars(int stars) {
        this.stars = stars;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Hotel hotel = (Hotel) o;

        if (hotelId != hotel.hotelId) return false;
        if (stars != hotel.stars) return false;
        if (hotelName != null ? !hotelName.equals(hotel.hotelName) : hotel.hotelName != null) return false;
        if (phone != null ? !phone.equals(hotel.phone) : hotel.phone != null) return false;
        return country != null ? country.equals(hotel.country) : hotel.country == null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int result = hotelId;
        result = 31 * result + (hotelName != null ? hotelName.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + stars;
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "Hotel{" +
                "hotelId=" + hotelId +
                ", hotelName='" + hotelName + '\'' +
                ", phone='" + phone + '\'' +
                ", country=" + country +
                ", stars=" + stars +
                '}';
    }
}
