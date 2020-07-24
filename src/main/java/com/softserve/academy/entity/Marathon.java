package com.softserve.academy.entity;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.List;

/**
 * @author Bohdan Kurchak, Ruslan Pryimak
 */
@Entity
@Table(name = "marathon")
public class Marathon {
    @Id
    @Column(name = "id")
    private BigInteger id;

    @Column(name = "title")
    private String title;

    @ManyToMany
    @JoinTable(name = "marathon_user",
            joinColumns = @JoinColumn(name = "marathon_id"))
    private List<Users> usersList;

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Users> getUsersList() {
        return usersList;
    }

    public void setUsersList(List<Users> usersList) {
        this.usersList = usersList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Marathon)) return false;

        Marathon marathon = (Marathon) o;

        if (getId() != null ? !getId().equals(marathon.getId()) : marathon.getId() != null) return false;
        return getTitle() != null ? getTitle().equals(marathon.getTitle()) : marathon.getTitle() == null;
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getTitle() != null ? getTitle().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return String.format("Marathon id: %s, title: %s", id, title);
    }
}
