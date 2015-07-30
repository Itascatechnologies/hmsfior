package in.co.itasca.hms.persistence.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the registration database table.
 * 
 */
@Entity
@NamedQuery(name="Registration.findAll", query="SELECT r FROM Registration r")
public class Registration implements Serializable, IDBEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="seq_reg")
	@SequenceGenerator(name="seq_reg", sequenceName="seq_name")
	

	private int idregistration;

	private int active;

	private String address;

	@Temporal(TemporalType.DATE)
	private Date agedate;

	private String agein;

	private String changedBy;

	@Temporal(TemporalType.DATE)
	private Date changedOn;

	private String contactno;

	private String createdBy;

	@Temporal(TemporalType.DATE)
	private Date createdOn;

	private String fathername;

	private String name;

	private float price;

	@Temporal(TemporalType.DATE)
	private Date validFrom;

	@Temporal(TemporalType.DATE)
	private Date validTo;

	//bi-directional many-to-one association to Department
	@ManyToOne
	private Department department;

	//bi-directional many-to-one association to Registrationfeemaster
	@ManyToOne
	private Registrationfeemaster registrationfeemaster;

	//bi-directional many-to-one association to Sex
	@ManyToOne
	@JoinColumn(name="sex")
	private Sex sexBean;

	//bi-directional many-to-one association to Testregistration
	@OneToMany(mappedBy="registration")
	private List<Testregistration> testregistrations;

	public Registration() {
	}

	public int getIdregistration() {
		return this.idregistration;
	}

	public void setIdregistration(int idregistration) {
		this.idregistration = idregistration;
	}

	public int getActive() {
		return this.active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getAgedate() {
		return this.agedate;
	}

	public void setAgedate(Date agedate) {
		this.agedate = agedate;
	}

	public String getAgein() {
		return this.agein;
	}

	public void setAgein(String agein) {
		this.agein = agein;
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

	public String getContactno() {
		return this.contactno;
	}

	public void setContactno(String contactno) {
		this.contactno = contactno;
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

	public String getFathername() {
		return this.fathername;
	}

	public void setFathername(String fathername) {
		this.fathername = fathername;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getPrice() {
		return this.price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public Date getValidFrom() {
		return this.validFrom;
	}

	public void setValidFrom(Date validFrom) {
		this.validFrom = validFrom;
	}

	public Date getValidTo() {
		return this.validTo;
	}

	public void setValidTo(Date validTo) {
		this.validTo = validTo;
	}

	public Department getDepartment() {
		return this.department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Registrationfeemaster getRegistrationfeemaster() {
		return this.registrationfeemaster;
	}

	public void setRegistrationfeemaster(Registrationfeemaster registrationfeemaster) {
		this.registrationfeemaster = registrationfeemaster;
	}

	public Sex getSexBean() {
		return this.sexBean;
	}

	public void setSexBean(Sex sexBean) {
		this.sexBean = sexBean;
	}

	public List<Testregistration> getTestregistrations() {
		return this.testregistrations;
	}

	public void setTestregistrations(List<Testregistration> testregistrations) {
		this.testregistrations = testregistrations;
	}

	public Testregistration addTestregistration(Testregistration testregistration) {
		getTestregistrations().add(testregistration);
		testregistration.setRegistration(this);

		return testregistration;
	}

	public Testregistration removeTestregistration(Testregistration testregistration) {
		getTestregistrations().remove(testregistration);
		testregistration.setRegistration(null);

		return testregistration;
	}

}