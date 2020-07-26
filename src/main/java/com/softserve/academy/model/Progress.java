package com.softserve.academy.model;

import javax.persistence.*;
import java.math.BigInteger;
import java.time.LocalDate;

/**
 * @author Bohdan Kurchak, Ruslan Pryimak
 */
@Entity
@Table(name = "progress")
public class Progress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private BigInteger id;

    @Column(name = "started")
    private LocalDate started;

    @Column(name = "status")
    private String status;

    @Column(name = "updated")
    private LocalDate updated;

    @Column(name = "task_id")
    private BigInteger task_id;

    @Column(name = "trainee_id")
    private BigInteger trainee_id;

    @OneToOne(optional = false)
    @JoinColumn(name = "task_id", unique = true, nullable = false, updatable = false)
    private Task task;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {
        CascadeType.MERGE, CascadeType.PERSIST
    })
    @JoinColumn(name = "trainee_id", nullable = false)
    private Users user;

    public Progress() {}

    public BigInteger getId() {
        return id;
    }

    public LocalDate getStarted() {
        return started;
    }

    public void setStarted(LocalDate started) {
        this.started = started;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getUpdated() {
        return updated;
    }

    public void setUpdated(LocalDate updated) {
        this.updated = updated;
    }

    public BigInteger getTask_id() {
        return task_id;
    }

    public void setTask_id(BigInteger task_id) {
        this.task_id = task_id;
    }

    public BigInteger getTrainee_id() {
        return trainee_id;
    }

    public void setTrainee_id(BigInteger trainee_id) {
        this.trainee_id = trainee_id;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Progress)) return false;

        Progress progress = (Progress) o;

        if (getId() != null ? !getId().equals(progress.getId()) : progress.getId() != null) return false;
        if (getStarted() != null ? !getStarted().equals(progress.getStarted()) : progress.getStarted() != null)
            return false;
        if (getStatus() != null ? !getStatus().equals(progress.getStatus()) : progress.getStatus() != null)
            return false;
        if (getUpdated() != null ? !getUpdated().equals(progress.getUpdated()) : progress.getUpdated() != null)
            return false;
        if (getTask_id() != null ? !getTask_id().equals(progress.getTask_id()) : progress.getTask_id() != null)
            return false;
        if (getTrainee_id() != null ? !getTrainee_id().equals(progress.getTrainee_id()) : progress.getTrainee_id() != null)
            return false;
        if (getTask() != null ? !getTask().equals(progress.getTask()) : progress.getTask() != null) return false;
        return getUser() != null ? getUser().equals(progress.getUser()) : progress.getUser() == null;
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getStarted() != null ? getStarted().hashCode() : 0);
        result = 31 * result + (getStatus() != null ? getStatus().hashCode() : 0);
        result = 31 * result + (getUpdated() != null ? getUpdated().hashCode() : 0);
        result = 31 * result + (getTask_id() != null ? getTask_id().hashCode() : 0);
        result = 31 * result + (getTrainee_id() != null ? getTrainee_id().hashCode() : 0);
        result = 31 * result + (getTask() != null ? getTask().hashCode() : 0);
        result = 31 * result + (getUser() != null ? getUser().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return String.format("User " +
                        "id: %s, " +
                        "started: %s" +
                        "status: %s" +
                        "updated: %s" +
                        "task_id: %s" +
                        "trainee_id: %s",
                id, started, status, updated, task_id, trainee_id
        );
    }
}
