package com.alquilercoches.coches.models.entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;
import java.util.Objects;


@Entity
@Table(name="corporative_users")
public class Corporative_users implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

@Column(name="c_user")
private String user;


@Column(name="c_password")
private String password;





	public Corporative_users() {}


	public Long getId() {
		return id;
	}


	public String getUser() {
		return user;
}
public void setUser(String user) {
	this.user = user;
}
public String getPassword() {
		return password;
}
public void setPassword(String password) {
	this.password = password;
}


	

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Corporative_users other = (Corporative_users) obj;
		return Objects.equals(id, other.id);
	}


	private static final long serialVersionUID = 1L;
	
}
