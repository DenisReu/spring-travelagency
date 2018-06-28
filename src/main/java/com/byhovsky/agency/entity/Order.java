package com.byhovsky.agency.entity;

public class Order extends AbstractEntity {
    private static final long serialVersionUID = 1L;
    private int orderId;
    private Tour tour;
    private int quantity;
    private int userId;

    /**
     * Getter for property 'orderId'.
     *
     * @return Value for property 'orderId'.
     */
    public int getOrderId() {
        return orderId;
    }

    /**
     * Setter for property 'orderId'.
     *
     * @param orderId Value to set for property 'orderId'.
     */
    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    /**
     * Getter for property 'tour'.
     *
     * @return Value for property 'tour'.
     */
    public Tour getTour() {
        return tour;
    }

    /**
     * Setter for property 'tour'.
     *
     * @param tour Value to set for property 'tour'.
     */
    public void setTour(Tour tour) {
        this.tour = tour;
    }

    /**
     * Getter for property 'quantity'.
     *
     * @return Value for property 'quantity'.
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Setter for property 'quantity'.
     *
     * @param quantity Value to set for property 'quantity'.
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Getter for property 'userId'.
     *
     * @return Value for property 'userId'.
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Setter for property 'userId'.
     *
     * @param userId Value to set for property 'userId'.
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /** {@inheritDoc} */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (orderId != order.orderId) return false;
        if (quantity != order.quantity) return false;
        if (userId != order.userId) return false;
        return tour != null ? tour.equals(order.tour) : order.tour == null;
    }

    /** {@inheritDoc} */
    @Override
    public int hashCode() {
        int result = orderId;
        result = 31 * result + (tour != null ? tour.hashCode() : 0);
        result = 31 * result + quantity;
        result = 31 * result + userId;
        return result;
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", tour=" + tour +
                ", quantity=" + quantity +
                ", userId=" + userId +
                '}';
    }
}
