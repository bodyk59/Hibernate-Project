package com.softserve.academy.entity;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Set;

/**
 * @author Bohdan Kurchak, Ruslan Pryimak
 */
@Entity
@Table(name = "users")
public class Users {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private BigInteger id;

    @Column(name = "email")
    private String email;

    @Column(name = "first_name")
    private String first_name;

    @Column(name = "last_name", length = 20)
    private String last_name;

    @Column(name = "password")
    private String password;

    @Column(name = "role")
    private String role;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "users")
    private Set<Marathon> marathons;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
    private Set<Progress> progresses;

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Set<Marathon> getMarathons() {
        return marathons;
    }

    public void setMarathons(Set<Marathon> marathons) {
        this.marathons = marathons;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Users)) return false;

        Users users = (Users) o;

        if (getEmail() != null ? !getEmail().equals(users.getEmail()) : users.getEmail() != null) return false;
        if (getFirst_name() != null ? !getFirst_name().equals(users.getFirst_name()) : users.getFirst_name() != null)
            return false;
        if (getLast_name() != null ? !getLast_name().equals(users.getLast_name()) : users.getLast_name() != null)
            return false;
        return getRole() != null ? getRole().equals(users.getRole()) : users.getRole() == null;
    }

    @Override
    public int hashCode() {
        int result = getEmail() != null ? getEmail().hashCode() : 0;
        result = 31 * result + (getFirst_name() != null ? getFirst_name().hashCode() : 0);
        result = 31 * result + (getLast_name() != null ? getLast_name().hashCode() : 0);
        result = 31 * result + (getRole() != null ? getRole().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return String.format("User " +
                "id: %s, " +
                "email: %s" +
                "first_name: %s" +
                "last_name: %s" +
                "password: %s" +
                "role: %s",
                id, email, first_name, last_name, password, role
         );
    }
}
