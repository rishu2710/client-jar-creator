package com.example.entity;

import com.example.constants.CommonConstants;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = CommonConstants.SampleEntity.TABLE_NAME)
public class Sample {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = CommonConstants.SampleEntity.COLUMN_NAME, nullable = false, length = CommonConstants.SampleEntity.NAME_MAX_LENGTH)
    @Size(min = 2, max = CommonConstants.SampleEntity.NAME_MAX_LENGTH, message = CommonConstants.SampleEntity.NAME_SIZE_MESSAGE)
    private String name;

    @Column(name = CommonConstants.SampleEntity.COLUMN_CREATED_BY, nullable = false, length = CommonConstants.SampleEntity.CREATED_BY_MAX_LENGTH)
    @Size(max = CommonConstants.SampleEntity.CREATED_BY_MAX_LENGTH, message = CommonConstants.SampleEntity.CREATED_BY_SIZE_MESSAGE)
    private String createdBy;

    @Column(name = CommonConstants.SampleEntity.COLUMN_CREATED_ON, nullable = false)
    private LocalDateTime createdOn;

    @Column(name = CommonConstants.SampleEntity.COLUMN_MODIFIED_BY, length = CommonConstants.SampleEntity.MODIFIED_BY_MAX_LENGTH)
    @Size(max = CommonConstants.SampleEntity.MODIFIED_BY_MAX_LENGTH, message = CommonConstants.SampleEntity.MODIFIED_BY_SIZE_MESSAGE)
    private String modifiedBy;

    @Column(name = CommonConstants.SampleEntity.COLUMN_MODIFIED_ON)
    private LocalDateTime modifiedOn;

    @Column(name = CommonConstants.SampleEntity.COLUMN_STATUS, nullable = false)
    private String status;

    // Default constructor
    public Sample() {
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public LocalDateTime getModifiedOn() {
        return modifiedOn;
    }

    public void setModifiedOn(LocalDateTime modifiedOn) {
        this.modifiedOn = modifiedOn;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // PrePersist and PreUpdate methods for audit fields
    @PrePersist
    protected void onCreate() {
        if (createdOn == null) {
            createdOn = LocalDateTime.now();
        }
        if (status == null) {
            status = "INACTIVE";
        }
    }

    @PreUpdate
    protected void onUpdate() {
        modifiedOn = LocalDateTime.now();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sample sample = (Sample) o;
        return Objects.equals(id, sample.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return String.format("Sample{id=%d, name='%s', createdBy='%s', createdOn=%s, modifiedBy='%s', modifiedOn=%s, status=%s}",
                id, name, createdBy, createdOn, modifiedBy, modifiedOn, status);
    }
}
