package in.co.itasca.hms.persistence.model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;
import java.util.Map;


/**
 * The persistent class for the menus database table.
 * 
 */
@Entity
@NamedQueries({
@NamedQuery(name="Menus.findAll", query="SELECT m FROM Menus m")
,
@NamedQuery(name="Menus.getMenusFromRoot", query="SELECT m FROM Menus m where m.fkId is NULL"),
@NamedQuery(name="Menus.findById", query="SELECT m FROM Menus m where m.fkId = :id"),
})


public class Menus implements Serializable, IDBEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private String icon;

	private String info;

	private String menuKey;

	private String menunumber;

	private String numberunit;

	private String targetPage;

	private String title;
	
	@Column(name="Menus_id", nullable=true, updatable= false, insertable=false)
	private int fkId;


	//bi-directional many-to-one association to Menus
	@ManyToOne(optional=true)
	@JoinColumn(name="Menus_id", referencedColumnName="id" , nullable=true)

	private Menus menus;

	public int getFkId() {
		return fkId;
	}

	public void setFkId(int fkId) {
		this.fkId = fkId;
	}

	//bi-directional many-to-one association to Menus
	@OneToMany(mappedBy="menus")
	private List<Menus> menuses;

	public Menus() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIcon() {
		return this.icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getInfo() {
		return this.info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getMenuKey() {
		return this.menuKey;
	}

	public void setMenuKey(String menuKey) {
		this.menuKey = menuKey;
	}

	public String getMenunumber() {
		return this.menunumber;
	}

	public void setMenunumber(String menunumber) {
		this.menunumber = menunumber;
	}

	public String getNumberunit() {
		return this.numberunit;
	}

	public void setNumberunit(String numberunit) {
		this.numberunit = numberunit;
	}

	public String getTargetPage() {
		return this.targetPage;
	}

	public void setTargetPage(String targetPage) {
		this.targetPage = targetPage;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Menus getMenus() {
		return this.menus;
	}

	public void setMenus(Menus menus) {
		this.menus = menus;
	}

	public List<Menus> getMenuses() {
		return this.menuses;
	}

	public void setMenuses(List<Menus> menuses) {
		this.menuses = menuses;
	}

	public Menus addMenus(Menus menus) {
		getMenuses().add(menus);
		menus.setMenus(this);

		return menus;
	}

	public Menus removeMenus(Menus menus) {
		getMenuses().remove(menus);
		menus.setMenus(null);

		return menus;
	}

	

}