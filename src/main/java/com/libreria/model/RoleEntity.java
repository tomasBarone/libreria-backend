package com.libreria.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "roles")
public class RoleEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Enumerated(EnumType.STRING) //guarda el nombre del enum como String en la BD
	@Column(name = "role_name", nullable = false)
	private RoleEnum roleName;
	
	
	public RoleEntity() {
		
	}
	
	
	public RoleEntity(RoleEnum roleName) {
		this.roleName = roleName;
	}
	
	
	public Long getId() {return id;}
	
	public RoleEnum getRoleName() { return roleName; }
	
	public void setRoleName(RoleEnum roleName) { this.roleName = roleName; }
	
	
	
}
