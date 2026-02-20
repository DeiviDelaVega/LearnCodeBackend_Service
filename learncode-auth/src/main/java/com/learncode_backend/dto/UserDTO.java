package com.learncode_backend.dto;

import java.util.UUID;

public class UserDTO {
	
	 private UUID id;
	    private String fullName;
	    private String email;
	    private String photo;
	    private String role;
	    private String status;
	    
	    public UserDTO() {
	    }

	    // constructor
	    public UserDTO(UUID id, String fullName, String email, String photo,
	                   String role, String status) {
	        this.id = id;
	        this.fullName = fullName;
	        this.email = email;
	        this.photo = photo;
	        this.role = role;
	        this.status = status;
	    }

		public UUID getId() {
			return id;
		}

		public void setId(UUID id) {
			this.id = id;
		}

		public String getFullName() {
			return fullName;
		}

		public void setFullName(String fullName) {
			this.fullName = fullName;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getPhoto() {
			return photo;
		}

		public void setPhoto(String photo) {
			this.photo = photo;
		}

		public String getRole() {
			return role;
		}

		public void setRole(String role) {
			this.role = role;
		}

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}
	    
}
