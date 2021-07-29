package com.groupproject.coursemanager.models;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity()
@Table(name="users")
public class User {
	@Id()
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

    @Size(min=2, message="Name must contain at least 2 characters")
    private String name;
    
    @Email()
    @Size(min=8, message="Email must contain at least 8 characters")
	private String email;
	
    @Size(min=5, message="Password must contain at least 5 characters")
	private String password;
	
	@Transient
    @Size(min=5, message="Password confirmation must contain at least 5 characters")
	private String passwordConfirmation;
	
//    @NotEmpty(message="Role must be specified")
	private String role;

	private Boolean darkMode;
	
	private String profilePic;

    @Column(updatable=false)
	private Date createdAt;
	private Date updatedAt;

	@OneToMany(mappedBy="teacher", cascade=CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Course> teaches;


    @OneToMany(mappedBy="student", fetch = FetchType.LAZY)
    private List<Grade> attends;
	
	@PrePersist
	protected void onCreate(){
		this.createdAt = new Date();
	}
	@PreUpdate
	protected void onUpdate(){
		this.updatedAt = new Date();
	}

	public User() {
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPasswordConfirmation() {
		return passwordConfirmation;
	}
	public void setPasswordConfirmation(String passwordConfirmation) {
		this.passwordConfirmation = passwordConfirmation;
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
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public Boolean getDarkMode() {
		return darkMode;
	}
	public void setDarkMode(Boolean darkMode) {
		this.darkMode = darkMode;
	}
	public String getProfilePic() {
		return profilePic;
	}
	public void setProfilePic(String profilePic) {
		this.profilePic = profilePic;
	}
	public List<Course> getTeaches() {
		return teaches;
	}
	public void setTeaches(List<Course> teaches) {
		this.teaches = teaches;
	}
	public List<Grade> getAttends() {
		return attends;
	}
	public void setAttends(List<Grade> attends) {
		this.attends = attends;
	}
	
}


