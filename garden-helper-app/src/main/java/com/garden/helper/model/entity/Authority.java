package com.garden.helper.model.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.garden.helper.model.enums.EAuthority;

@Entity
@Table(name = "Authority")
public class Authority {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	private Long id;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "name", columnDefinition = "ENUM('ROLE_USER', 'ROLE_ADMIN')")
	private EAuthority name;
	
	@ManyToMany(mappedBy = "authorities")
	private Set<User> users = new HashSet<>();
	
	public Authority() {
		super();
	}

	public Authority(EAuthority name) {
		super();
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public EAuthority getName() {
		return name;
	}

	public void setName(EAuthority name) {
		this.name = name;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	@Override
	public boolean equals(Object o) {
		if(o == this)
			return true;
		
		if(!(o instanceof Authority))
			return false;
		
		Authority other = (Authority) o;
		return id != null
				&& id.equals(other.id);
	}
	
	@Override
	public int hashCode() {
		return getClass().hashCode();
	}

}
