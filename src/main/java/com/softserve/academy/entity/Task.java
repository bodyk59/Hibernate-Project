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
@Table(name = "task")
public class Task {
    @Id
    @Column(name = "id")
    private BigInteger id;

    @Column(name = "created")
    private Date created;

    @Column(name = "title")
    private String title;

    @Column(name = "updated")
    private Date updated;

    @OneToMany
    @JoinTable(name = "progress",
            joinColumns = @JoinColumn(name = "task_id"))
    private List<Progress> progressList;

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public List<Progress> getProgressList() {
        return progressList;
    }

    public void setProgressList(List<Progress> progressList) {
        this.progressList = progressList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Task)) return false;
        Task task = (Task) o;
        return Objects.equals(getId(), task.getId()) &&
                Objects.equals(getCreated(), task.getCreated()) &&
                Objects.equals(getTitle(), task.getTitle()) &&
                Objects.equals(getUpdated(), task.getUpdated()) &&
                Objects.equals(getProgressList(), task.getProgressList());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getCreated(), getTitle(), getUpdated(), getProgressList());
    }

    @Override
    public String toString() {
        return String.format("Task " +
                "id: %s" +
                ", created: %s" +
                ", title: %s" +
                ", updated: %s",
                id, created, title, updated
        );
    }
}
