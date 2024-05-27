// User.java
package com.projectA1.model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity(name = "f_user")
public class User extends Person {

    private Date birthDate; // 생년월일
    
}