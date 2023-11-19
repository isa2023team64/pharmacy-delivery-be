package com.isa2023team64.pharmacydeliverybe.dto;

import com.isa2023team64.pharmacydeliverybe.model.SystemAdministrator;

public class SystemAdministratorRequestDTO {
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String city;
    private String country;
    private String phoneNumber;
    private String workplace;
    private String companyName;

    public SystemAdministratorRequestDTO() {
        super();
    }


    public SystemAdministratorRequestDTO(SystemAdministrator systemAdministrator ){
        this(systemAdministrator.getEmail(), systemAdministrator.getPassword(), systemAdministrator.getFirstName(), systemAdministrator.getLastName(),systemAdministrator.getCity(),systemAdministrator.getCountry(),systemAdministrator.getPhoneNumber(),systemAdministrator.getWorkplace(),systemAdministrator.getCompanyName());
    }

    public SystemAdministratorRequestDTO(String email, String password, String firstName, String lastName, String city, String country,String phoneNumber, String workplace, String companyName) {
        this.email = email;
        this.password = password;        
        this.firstName = firstName;
        this.lastName = lastName;
        this.city = city;
        this.country = country;
        this.phoneNumber = phoneNumber;
        this.workplace = workplace;
        this.companyName = companyName;    
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
    
    public String getFirstName() {
        return firstName;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public String getCity() {
        return city;
    }
    
    public void setCity(String city) {
        this.city = city;
    }
    
    public String getCountry() {
        return country;
    }
    
    public void setCountry(String country) {
        this.country = country;
    }
    
    public String getPhoneNumber() {
        return phoneNumber;
    }
    
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    
    public String getWorkplace() {
        return workplace;
    }
    
    public void setWorkplace(String workplace) {
        this.workplace = workplace;
    }
    
    public String getCompanyName() {
        return companyName;
    }
    
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}