package com.groupproject.coursemanager.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity()
@Table(name="courses")
public class Course {
	@Id()
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

    @Size(min=5, message="Name must contain at least 5 characters")
    private String name;

    @Size(min=5, message="Code must contain at least 5 characters")
    private String code;

    @NotEmpty(message="Semester must be specified")
    private String semester;

    @NotNull(message="Please specify course credits")
    @Min(value=1, message="Credits must be greater than or equal to 1")
    @Max(value=3, message="Credits must be less than or equal to 3")
    
    private Integer credits;

    @NotNull(message="Please specify course capacity")
    @Min(value=10, message="Capacity must be greater than or equal to 10")
    @Max(value=50, message="Capacity must be less than or equal to 50")
    private Integer capacity;

    @Column(updatable=false)
	private Date createdAt;
	private Date updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="teacher_id", nullable=false)
    private User teacher;
    
	
    @OneToMany(mappedBy="course", fetch = FetchType.LAZY)
    private List<Grade> grades;

    @PrePersist
	protected void onCreate(){
		this.createdAt = new Date();
	}
	@PreUpdate
	protected void onUpdate(){
		this.updatedAt = new Date();
	}

	public Course() {
		
	}
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
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getSemester() {
		return semester;
	}
	public void setSemester(String semester) {
		this.semester = semester;
	}
	public Integer getCredits() {
		return credits;
	}
	public void setCredits(Integer credits) {
		this.credits = credits;
	}
	public Integer getCapacity() {
		return capacity;
	}
	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public Date getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	public User getTeacher() {
		return teacher;
	}
	public void setTeacher(User teacher) {
		this.teacher = teacher;
	}
	public List<Grade> getGrades() {
		return grades;
	}
	public void setGrades(List<Grade> grades) {
		this.grades = grades;
	}
	
	
}
