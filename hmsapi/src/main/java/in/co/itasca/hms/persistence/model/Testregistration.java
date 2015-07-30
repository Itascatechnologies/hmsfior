package in.co.itasca.hms.persistence.model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the testregistration database table.
 * 
 */
@Entity
@NamedQuery(name="Testregistration.findAll", query="SELECT t FROM Testregistration t")
public class Testregistration implements Serializable , IDBEntity{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="seq_tstreg")
	@SequenceGenerator(name="seq_tstreg", sequenceName="seq_name")

	private int idtest;

	@Temporal(TemporalType.DATE)
	private Date date;

	private float totalprive;

	//bi-directional many-to-one association to Testrequested
	@OneToMany(mappedBy="testregistration")
	private List<Testrequested> testrequesteds;

	//bi-directional many-to-one association to Registration
	@ManyToOne
	private Registration registration;

	public Testregistration() {
	}

	public int getIdtest() {
		return this.idtest;
	}

	public void setIdtest(int idtest) {
		this.idtest = idtest;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public float getTotalprive() {
		return this.totalprive;
	}

	public void setTotalprive(float totalprive) {
		this.totalprive = totalprive;
	}

	public List<Testrequested> getTestrequesteds() {
		return this.testrequesteds;
	}

	public void setTestrequesteds(List<Testrequested> testrequesteds) {
		this.testrequesteds = testrequesteds;
	}

	public Testrequested addTestrequested(Testrequested testrequested) {
		getTestrequesteds().add(testrequested);
		testrequested.setTestregistration(this);

		return testrequested;
	}

	public Testrequested removeTestrequested(Testrequested testrequested) {
		getTestrequesteds().remove(testrequested);
		testrequested.setTestregistration(null);

		return testrequested;
	}

	public Registration getRegistration() {
		return this.registration;
	}

	public void setRegistration(Registration registration) {
		this.registration = registration;
	}

}