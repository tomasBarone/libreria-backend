package com.libreria.model;

import java.util.Set;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.JoinColumn;

@Entity
@Table(name = "usuarios")
public class UserEntity {

 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long id;
 
 @Column(unique = true)
 private String username;
 
 private String password;
 
 
 //usamos Set para los roles
 @ElementCollection(fetch = FetchType.EAGER)
 @CollectionTable(name = "usuario_roles", joinColumns = @JoinColumn(name =  "usuario_id"))
 @Column(name = "rol")
 private Set<String> roles;
	
}
