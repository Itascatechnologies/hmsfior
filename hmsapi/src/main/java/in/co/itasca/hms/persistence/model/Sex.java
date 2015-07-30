package in.co.itasca.hms.persistence.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the sex database table.
 * 
 */
@Entity
@NamedQuery(name="Sex.findAll", query="SELECT s FROM Sex s")
public class Sex implements Serializable , IDBEntity{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="sex_name")
	@SequenceGenerator(name="sex_name", sequenceName="seq_name")

	private int idsex;

	private int active;

	private String changedBy;

	@Temporal(TemporalType.DATE)
	private Date changedOn;

	private String createdBy;

	@Temporal(TemporalType.DATE)
	private Date createdOn;

	private String description;

	private String sexText;

	//bi-directional many-to-one association to Registration
	@OneToMany(mappedBy="sexBean")
	private List<Registration> registrations;

	public Sex() {
	}

	public int getIdsex() {
		return this.idsex;
	}

	public void setIdsex(int idsex) {
		this.idsex = idsex;
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

	public Date getChangedOn() {
		return this.changedOn;
	}

	public void setChangedOn(Date changedOn) {
		this.changedOn = changedOn;
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

	public String getSexText() {
		return this.sexText;
	}

	public void setSexText(String sexText) {
		this.sexText = sexText;
	}

	public List<Registration> getRegistrations() {
		return this.registrations;
	}

	public void setRegistrations(List<Registration> registrations) {
		this.registrations = registrations;
	}

	public Registration addRegistration(Registration registration) {
		getRegistrations().add(registration);
		registration.setSexBean(this);

		return registration;
	}

	public Registration removeRegistration(Registration registration) {
		getRegistrations().remove(registration);
		registration.setSexBean(null);

		return registration;
	}

}