package com.byhovsky.agency.entity;

import java.util.Arrays;
import java.util.List;

/**
 * User
 *
 * @author Denis Byhovsky
 */
public class User extends AbstractEntity {
    private static final long serialVersionUID = 1L;
    int userId;
    String login;
    char[] password;
    private List<Order> orders;

    public User() {
    }

    public User(String login, char[] password) {
        this.login = login;
        this.password = password;
    }


    /**
     * Getter for property 'orders'.
     *
     * @return Value for property 'orders'.
     */
    public List<Order> getOrders() {
        return orders;
    }

    /**
     * Setter for property 'orders'.
     *
     * @param orders Value to set for property 'orders'.
     */
    public void setOrders(List<Order> orders) {
        this.orders = orders;
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

    /**
     * Getter for property 'login'.
     *
     * @return Value for property 'login'.
     */
    public String getLogin() {
        return login;
    }

    /**
     * Setter for property 'login'.
     *
     * @param login Value to set for property 'login'.
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * Getter for property 'password'.
     *
     * @return Value for property 'password'.
     */
    public char[] getPassword() {
        return password;
    }

    /**
     * Setter for property 'password'.
     *
     * @param password Value to set for property 'password'.
     */
    public void setPassword(char[] password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (userId != user.userId) return false;
        if (login != null ? !login.equals(user.login) : user.login != null) return false;
        return Arrays.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        int result = userId;
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + Arrays.hashCode(password);
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", login='" + login + '\'' +
                ", password=" + Arrays.toString(password) +
                ", orders=" + orders +
                '}';
    }
}
