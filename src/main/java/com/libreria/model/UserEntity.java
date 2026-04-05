package com.libreria.model;

import java.util.Set;

import jakarta.persistence.CascadeType;
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
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;


@Entity
@Table(name = "usuarios")
public class UserEntity {

 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long id;
 
 @Column(unique = true, nullable = false)
 private String username;
 
 @Column(nullable = false)
 private String password;
 
 
 //usamos Set para los roles
 /*@ElementCollection(fetch = FetchType.EAGER)
 @CollectionTable(name = "usuario_roles", joinColumns = @JoinColumn(name =  "usuario_id"))
 @Column(name = "rol")*/
 
 
 
 
 @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE) // CascadeType.ALL para que guarde roles nuevos automáticamente
 @JoinTable(
     name = "user_roles", // Nombre de la tabla intermedia
     joinColumns = @JoinColumn(name = "user_id"),
     inverseJoinColumns = @JoinColumn(name = "role_id")
 )
 
 
 private Set<RoleEntity> roles;
	
 
 public Set<RoleEntity> getRoles() {
	    return roles;
	}

	public void setRoles(Set<RoleEntity> roles) {
	    this.roles = roles;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	
	
 
 
 
}
