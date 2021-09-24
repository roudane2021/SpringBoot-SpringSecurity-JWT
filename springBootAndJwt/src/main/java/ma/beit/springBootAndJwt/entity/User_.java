package ma.beit.springBootAndJwt.entity;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "user_")
public class User_ {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String job;
	private String password;
	
	@ManyToMany(fetch=FetchType.EAGER, cascade = CascadeType.ALL)
	private Collection<Role_> roles=new ArrayList<>();

	public User_() {
		// TODO Auto-generated constructor stub
	}

	public User_(Long id, String name, String job, String password, Collection<Role_> roles) {
		this.id = id;
		this.name = name;
		this.job = job;
		this.password = password;
		this.roles = roles;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}

	public Collection<Role_> getRoles() {
		return roles;
	}

	public void setRoles(Collection<Role_> roles) {
		this.roles = roles;
	}
	

}
