package in.co.itasca.hms.persistence.model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * The persistent class for the department database table.
 * 
 */
@Entity
@NamedQuery(name="Department.findAll", query="SELECT d FROM Department d")
public class Department implements Serializable, IDBEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="seq_dep")
	@SequenceGenerator(name="seq_dep", sequenceName="seq_name")

	private int iddepartment;

	private int active;

	private String changedBy;

	private String createdBy;

	@Temporal(TemporalType.DATE)
	private Date createdOn;

	private String description;

	private String name;

	//bi-directional many-to-one association to Registration
	@OneToMany(mappedBy="department")
	private List<Registration> registrations;

	//bi-directional many-to-one association to Testmaster
	@OneToMany(mappedBy="department")
	private List<Testmaster> testmasters;

	public Department() {
	}

	public int getIddepartment() {
		return this.iddepartment;
	}

	public void setIddepartment(int iddepartment) {
		this.iddepartment = iddepartment;
	}

	public int getActive() {
		return this.active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	public String getChangedBy() {
		return this.changedBy;
	}

	public void setChangedBy(String changedBy) {
		this.changedBy = changedBy;
	}

	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedOn() {
		return this.createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Registration> getRegistrations() {
		return this.registrations;
	}

	public void setRegistrations(List<Registration> registrations) {
		this.registrations = registrations;
	}

	public Registration addRegistration(Registration registration) {
		getRegistrations().add(registration);
		registration.setDepartment(this);

		return registration;
	}

	public Registration removeRegistration(Registration registration) {
		getRegistrations().remove(registration);
		registration.setDepartment(null);

		return registration;
	}

	public List<Testmaster> getTestmasters() {
		return this.testmasters;
	}

	public void setTestmasters(List<Testmaster> testmasters) {
		this.testmasters = testmasters;
	}

	public Testmaster addTestmaster(Testmaster testmaster) {
		getTestmasters().add(testmaster);
		testmaster.setDepartment(this);

		return testmaster;
	}

	public Testmaster removeTestmaster(Testmaster testmaster) {
		getTestmasters().remove(testmaster);
		testmaster.setDepartment(null);

		return testmaster;
	}

	

}