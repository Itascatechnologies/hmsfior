package in.co.itasca.hms.persistence.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the registrationfeemaster database table.
 * 
 */
@Entity
@NamedQuery(name="Registrationfeemaster.findAll", query="SELECT r FROM Registrationfeemaster r")
public class Registrationfeemaster implements Serializable, IDBEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="seq_regfee")
	@SequenceGenerator(name="seq_regfee", sequenceName="seq_name")

	private int id;

	private int active;

	private String approvedBy;

	@Temporal(TemporalType.DATE)
	private Date approvedOn;

	private String createdBy;

	@Temporal(TemporalType.DATE)
	private Date createdOn;

	private float fee;

	private int validityDays;

	//bi-directional many-to-one association to Registration
	@OneToMany(mappedBy="registrationfeemaster")
	private List<Registration> registrations;

	//bi-directional many-to-one association to Patientscategorymaster
	@ManyToOne
	private Patientscategorymaster patientscategorymaster;

	public Registrationfeemaster() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getActive() {
		return this.active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	public String getApprovedBy() {
		return this.approvedBy;
	}

	public void setApprovedBy(String approvedBy) {
		this.approvedBy = approvedBy;
	}

	public Date getApprovedOn() {
		return this.approvedOn;
	}

	public void setApprovedOn(Date approvedOn) {
		this.approvedOn = approvedOn;
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

	public float getFee() {
		return this.fee;
	}

	public void setFee(float fee) {
		this.fee = fee;
	}

	public int getValidityDays() {
		return this.validityDays;
	}

	public void setValidityDays(int validityDays) {
		this.validityDays = validityDays;
	}

	public List<Registration> getRegistrations() {
		return this.registrations;
	}

	public void setRegistrations(List<Registration> registrations) {
		this.registrations = registrations;
	}

	public Registration addRegistration(Registration registration) {
		getRegistrations().add(registration);
		registration.setRegistrationfeemaster(this);

		return registration;
	}

	public Registration removeRegistration(Registration registration) {
		getRegistrations().remove(registration);
		registration.setRegistrationfeemaster(null);

		return registration;
	}

	public Patientscategorymaster getPatientscategorymaster() {
		return this.patientscategorymaster;
	}

	public void setPatientscategorymaster(Patientscategorymaster patientscategorymaster) {
		this.patientscategorymaster = patientscategorymaster;
	}

}