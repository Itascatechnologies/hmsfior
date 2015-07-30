package in.co.itasca.hms.persistence.model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the roles database table.
 * 
 */
@Entity
@Table(name="roles")
@NamedQuery(name="Role.findAll", query="SELECT r FROM Role r")
public class Role implements Serializable , IDBEntity{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="seq_role")
	@SequenceGenerator(name="seq_role", sequenceName="seq_name")

	private String rolename;

	private String roledescription;

	//bi-directional many-to-many association to User
	@ManyToMany(mappedBy="roles")
	private List<User> users;

	public Role() {
	}

	public String getRolename() {
		return this.rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	public String getRoledescription() {
		return this.roledescription;
	}

	public void setRoledescription(String roledescription) {
		this.roledescription = roledescription;
	}

	public List<User> getUsers() {
		return this.users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

}