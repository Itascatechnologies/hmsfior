package in.co.itasca.hms.persistence.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the sequence database table.
 * 
 */
@Entity
@NamedQuery(name="Sequence.findAll", query="SELECT s FROM Sequence s")
public class Sequence implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="seq_name")
	private String seqName;

	@Column(name="seq_count")
	private int seqCount;

	public Sequence() {
	}

	public String getSeqName() {
		return this.seqName;
	}

	public void setSeqName(String seqName) {
		this.seqName = seqName;
	}

	public int getSeqCount() {
		return this.seqCount;
	}

	public void setSeqCount(int seqCount) {
		this.seqCount = seqCount;
	}

}