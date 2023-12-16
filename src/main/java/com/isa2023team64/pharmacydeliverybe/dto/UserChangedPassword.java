package com.isa2023team64.pharmacydeliverybe.dto;

// DTO koji enkapsulira odgovor na promenu lozinke
public class UserChangedPassword {
	
    private boolean success;
    private String message;

    public UserChangedPassword() {
        this.success = false;
        this.message = "";
    }

    public UserChangedPassword(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}