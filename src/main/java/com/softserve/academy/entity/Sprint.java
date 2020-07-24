package com.softserve.academy.entity;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @author Bohdan Kurchak, Ruslan Pryimak
 */
@Entity
@Table(name = "sprint")
public class Sprint {
    @Id
    @Column(name = "id")
    private BigInteger id;

    @Column(name = "finish")
    private Date finish;

    @Column(name = "start_date")
    private Date start_date;

    @Column(name = "title")
    private String title;

    @OneToMany
    @JoinTable(name = "marathon",
            joinColumns = @JoinColumn(name = "marathon_id"))
    private List<Marathon> marathonList;

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public Date getFinish() {
        return finish;
    }

    public void setFinish(Date finish) {
        this.finish = finish;
    }

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Marathon> getMarathonList() {
        return marathonList;
    }

    public void setMarathonList(List<Marathon> marathonList) {
        this.marathonList = marathonList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Sprint)) return false;
        Sprint sprint = (Sprint) o;
        return Objects.equals(getId(), sprint.getId()) &&
                Objects.equals(getFinish(), sprint.getFinish()) &&
                Objects.equals(getStart_date(), sprint.getStart_date()) &&
                Objects.equals(getTitle(), sprint.getTitle()) &&
                Objects.equals(getMarathonList(), sprint.getMarathonList());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getFinish(), getStart_date(), getTitle(), getMarathonList());
    }

    @Override
    public String toString() {
        return String.format("Sprint " +
                "id: %s" +
                ", finish: %s" +
                ", start_date: %s" +
                ", title: %s",
                id, finish, start_date, title
        );
    }
}
