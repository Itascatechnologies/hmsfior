package in.co.itasca.hms.persistence.model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;


/**
 * The persistent class for the testrequested database table.
 * 
 */
@Entity
@NamedQuery(name="Testrequested.findAll", query="SELECT t FROM Testrequested t")
public class Testrequested implements Serializable, IDBEntity{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="seq_tstreq")
	@SequenceGenerator(name="seq_tstreq", sequenceName="seq_name")

	private int id;

	@Temporal(TemporalType.DATE)
	private Date date;

	private float testrate;

	//bi-directional many-to-one association to Testratesmaster
	@ManyToOne
	private Testratesmaster testratesmaster;

	//bi-directional many-to-one association to Testregistration
	@ManyToOne
	private Testregistration testregistration;

	public Testrequested() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public float getTestrate() {
		return this.testrate;
	}

	public void setTestrate(float testrate) {
		this.testrate = testrate;
	}

	public Testratesmaster getTestratesmaster() {
		return this.testratesmaster;
	}

	public void setTestratesmaster(Testratesmaster testratesmaster) {
		this.testratesmaster = testratesmaster;
	}

	public Testregistration getTestregistration() {
		return this.testregistration;
	}

	public void setTestregistration(Testregistration testregistration) {
		this.testregistration = testregistration;
	}

}