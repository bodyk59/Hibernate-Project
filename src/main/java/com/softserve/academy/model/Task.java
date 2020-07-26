package com.softserve.academy.model;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Date;
import java.util.Objects;

/**
 * @author Bohdan Kurchak, Ruslan Pryimak
 */
@Entity
@Table(name = "task")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private BigInteger id;

    @Column(name = "created")
    private Date created;

    @Column(name = "title")
    private String title;

    @Column(name = "updated")
    private Date updated;

    @OneToOne(optional = false, mappedBy = "task")
    private Progress progress;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {
            CascadeType.MERGE, CascadeType.PERSIST
    })
    @JoinColumn(name = "sprint_id", nullable = false)
    private Sprint sprint;

    public enum TaskStatus {
        PASS, FAIL, PENDING
    }

    public Task() {}

    public BigInteger getId() {
        return id;
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

    public Progress getProgress() {
        return progress;
    }

    public void setProgress(Progress progress) {
        this.progress = progress;
    }

    public Sprint getSprint() {
        return sprint;
    }

    public void setSprint(Sprint sprint) {
        this.sprint = sprint;
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
                Objects.equals(getProgress(), task.getProgress());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getCreated(), getTitle(), getUpdated(), getProgress());
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
