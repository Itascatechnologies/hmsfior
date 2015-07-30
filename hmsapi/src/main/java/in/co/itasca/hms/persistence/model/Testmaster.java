package in.co.itasca.hms.persistence.model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the testmaster database table.
 * 
 */
@Entity
@NamedQuery(name="Testmaster.findAll", query="SELECT t FROM Testmaster t")
public class Testmaster implements Serializable , IDBEntity{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="seq_tstmst")
	@SequenceGenerator(name="seq_tstmst", sequenceName="seq_name")

	private int idtests;

	private int active;

	private String changedBy;

	@Temporal(TemporalType.DATE)
	private Date changedON;

	private String createdBy;

	@Temporal(TemporalType.DATE)
	private Date createdOn;

	private String testDescription;

	private String testName;

	//bi-directional many-to-one association to Department
	@ManyToOne
	private Department department;

	//bi-directional many-to-one association to Testratesmaster
	@OneToMany(mappedBy="testmaster")
	private List<Testratesmaster> testratesmasters;

	public Testmaster() {
	}

	public int getIdtests() {
		return this.idtests;
	}

	public void setIdtests(int idtests) {
		this.idtests = idtests;
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

	public Date getChangedON() {
		return this.changedON;
	}

	public void setChangedON(Date changedON) {
		this.changedON = changedON;
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

	public String getTestDescription() {
		return this.testDescription;
	}

	public void setTestDescription(String testDescription) {
		this.testDescription = testDescription;
	}

	public String getTestName() {
		return this.testName;
	}

	public void setTestName(String testName) {
		this.testName = testName;
	}

	public Department getDepartment() {
		return this.department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public List<Testratesmaster> getTestratesmasters() {
		return this.testratesmasters;
	}

	public void setTestratesmasters(List<Testratesmaster> testratesmasters) {
		this.testratesmasters = testratesmasters;
	}

	public Testratesmaster addTestratesmaster(Testratesmaster testratesmaster) {
		getTestratesmasters().add(testratesmaster);
		testratesmaster.setTestmaster(this);

		return testratesmaster;
	}

	public Testratesmaster removeTestratesmaster(Testratesmaster testratesmaster) {
		getTestratesmasters().remove(testratesmaster);
		testratesmaster.setTestmaster(null);

		return testratesmaster;
	}

}