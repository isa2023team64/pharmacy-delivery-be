package com.isa2023team64.pharmacydeliverybe.dto;

// DTO za change password
public class JwtChangePasswordRequest {
	
    private String username;
    private String password;
    private String newPassword;

    public JwtChangePasswordRequest() {
        super();
    }

    public JwtChangePasswordRequest(String username, String password, String newPassword) {
        this.setUsername(username);
        this.setPassword(password);
        this.setNewPassword(newPassword);
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNewPassword(){
        return this.newPassword;
    }

    public void setNewPassword(String newPassword){
        this.newPassword = newPassword;
    }

}
