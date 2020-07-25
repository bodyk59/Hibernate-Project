package com.softserve.academy.entity;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Objects;
import java.util.Set;

/**
 * @author Bohdan Kurchak, Ruslan Pryimak
 */
@Entity
@Table(name = "marathon")
public class Marathon {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private BigInteger id;

    @Column(name = "title")
    private String title;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "marathon")
    private Set<Sprint> sprint;

    @ManyToMany
    @JoinTable(name = "marathon_user",
            joinColumns = @JoinColumn(name = "marathon_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id")
    )
    private Set<Users> users;

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

    public Set<Sprint> getSprint() {
        return sprint;
    }

    public void setSprint(Set<Sprint> sprint) {
        this.sprint = sprint;
    }

    public Set<Users> getUsers() {
        return users;
    }

    public void setUsers(Set<Users> users) {
        this.users = users;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Marathon)) return false;

        Marathon marathon = (Marathon) o;

        return getTitle() != null ? getTitle().equals(marathon.getTitle()) : marathon.getTitle() == null;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title);
    }

    @Override
    public String toString() {
        return String.format("Marathon id: %s, title: %s", id, title);
    }
}
