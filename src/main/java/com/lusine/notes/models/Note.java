package com.lusine.notes.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.Instant;

@Entity
@Table(name = "notes")
public class Note implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name="user_id")
    private int userId;

    @NotBlank(message = "Title cannot be blank")
    @Length(max = 50, message = "Title character count cannot exceed 50 characters")
    private String title;

    @Length(max = 50, message = "Note character count cannot exceed 1000 characters")
    private String note;

    @Column(name="created_at")
    private long createdAt;

    @Column(name="updated_at")
    private long updatedAt;

    public Note() {
        this.createdAt = Instant.now().toEpochMilli();
        this.updatedAt = Instant.now().toEpochMilli();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    public int getUserId() {
        return userId;
    }

    @JsonIgnore
    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    public long getCreatedAt() {
        return createdAt;
    }

    @JsonIgnore
    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    public long getUpdatedAt() {
        return updatedAt;
    }

    @JsonIgnore
    public void setUpdatedAt(long updatedAt) {
        this.updatedAt = updatedAt;
    }
}
