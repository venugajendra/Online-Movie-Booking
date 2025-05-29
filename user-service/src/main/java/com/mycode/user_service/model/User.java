package com.mycode.user_service.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String password; // Store hashed password
    private String phone;

//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public void setPhone(String phone) {
//        this.phone = phone;
//    }
//
    public String getPassword() { // Added getPassword() method
        return password;
    }

    public void setPassword(String password) { // Added setPassword
        this.password = password;
    }
//
//    public String getEmail() { // Added getPassword() method
//        return email;
//    }
//
//    public void setId(Long id) { // Added setPassword
//        this.id = id;
//    }

}
