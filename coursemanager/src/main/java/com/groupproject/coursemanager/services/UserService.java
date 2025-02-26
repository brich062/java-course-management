package com.groupproject.coursemanager.services;

import java.util.List;
import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.groupproject.coursemanager.models.User;
import com.groupproject.coursemanager.repositories.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;
    
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    // register user and hash their password
    public User registerUser(User user) {
        String hashed = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
        user.setPassword(hashed);
        return userRepository.save(user);
    }
    
    // find user by email
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    
    // find user by id
    public User findUserById(Long id) {
    	Optional<User> u = userRepository.findById(id);
    	
    	if(u.isPresent()) {
            return u.get();
    	} else {
    	    return null;
    	}
    }
    
    // authenticate user
    public boolean authenticateUser(String email, String password) {
        // first find the user by email
        User user = userRepository.findByEmail(email);
        // if we can't find it by email, return false
        if(user == null) {
            return false;
        } else {
            // if the passwords match, return true, else, return false
            if(BCrypt.checkpw(password, user.getPassword())) {
                return true;
            } else {
                return false;
            }
        }
    }
	//Display All
	public List<User> getAllUsers(){
		return this.userRepository.findAll();
	}
	//lightMode
	public User lightMode(User user) {
		user.setDarkMode(false);
		return this.userRepository.save(user);
	}
	//darkMode
	public User darkMode(User user) {
		user.setDarkMode(true);
		return this.userRepository.save(user);
	}
	public void getRole(User user) {
		user.getRole();
	}
	//profile pic
	public void uploadProfilePic(User user, String image_url) {
		user.setProfilePic(image_url);
		this.userRepository.save(user);
	}
    // find user by role
    public List<User> findByRole(String role) {
        return userRepository.findByRole(role);
    }	
	
}