package in.co.itasca.hms.persistence.model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the testratesmaster database table.
 * 
 */
@Entity
@NamedQuery(name="Testratesmaster.findAll", query="SELECT t FROM Testratesmaster t")
public class Testratesmaster implements Serializable, IDBEntity{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="seq_tstrat")
	@SequenceGenerator(name="seq_tstrat", sequenceName="seq_name")

	private int idtestrates;

	private int active;

	private float rate;

	@Temporal(TemporalType.DATE)
	private Date validFrom;

	@Temporal(TemporalType.DATE)
	private Date validTo;

	//bi-directional many-to-one association to Patientscategorymaster
	@ManyToOne
	private Patientscategorymaster patientscategorymaster;

	//bi-directional many-to-one association to Testmaster
	@ManyToOne
	@JoinColumn(name="tests_idtests")
	private Testmaster testmaster;

	//bi-directional many-to-one association to Testrequested
	@OneToMany(mappedBy="testratesmaster")
	private List<Testrequested> testrequesteds;

	public Testratesmaster() {
	}

	public int getIdtestrates() {
		return this.idtestrates;
	}

	public void setIdtestrates(int idtestrates) {
		this.idtestrates = idtestrates;
	}

	public int getActive() {
		return this.active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	public float getRate() {
		return this.rate;
	}

	public void setRate(float rate) {
		this.rate = rate;
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

	public Patientscategorymaster getPatientscategorymaster() {
		return this.patientscategorymaster;
	}

	public void setPatientscategorymaster(Patientscategorymaster patientscategorymaster) {
		this.patientscategorymaster = patientscategorymaster;
	}

	public Testmaster getTestmaster() {
		return this.testmaster;
	}

	public void setTestmaster(Testmaster testmaster) {
		this.testmaster = testmaster;
	}

	public List<Testrequested> getTestrequesteds() {
		return this.testrequesteds;
	}

	public void setTestrequesteds(List<Testrequested> testrequesteds) {
		this.testrequesteds = testrequesteds;
	}

	public Testrequested addTestrequested(Testrequested testrequested) {
		getTestrequesteds().add(testrequested);
		testrequested.setTestratesmaster(this);

		return testrequested;
	}

	public Testrequested removeTestrequested(Testrequested testrequested) {
		getTestrequesteds().remove(testrequested);
		testrequested.setTestratesmaster(null);

		return testrequested;
	}

}