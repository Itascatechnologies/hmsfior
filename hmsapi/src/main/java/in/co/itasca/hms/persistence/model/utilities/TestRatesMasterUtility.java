package in.co.itasca.hms.persistence.model.utilities;

import in.co.itasca.hms.persistence.model.IDBEntity;
import in.co.itasca.hms.persistence.model.Testratesmaster;

import java.util.Map;

public class TestRatesMasterUtility implements IUtility {

	public TestRatesMasterUtility() {
		// TODO Auto-generated constructor stub
	}
	
	private static final long serialVersionUID = 1L;

	public  final String ID="Id";
	public  final String IDTESTMASTER="IdTestMaster";
	public  final String IDPATIENTCATEGORYMASTER="IdPatientCategoryMaster";
	public  final String NAMETEST="NameTest";
	public  final String NAMEPATIENTCATEGORY="NamePatientCategory";
	public  final String VALIDFROM="validFrom";
	public  final String VALIDTO="validTo";
	public  final String RATE="rate";
	public  final String ACTIVE="active";

 

	@Override
	public void populate(Map<String, Object> map , IDBEntity lentity) {
		
			}



	@Override
	public Map<String, Object> convertModelTOEDM(Map<String, Object> map,
			IDBEntity lentity) {
		try {
			
			 Testratesmaster entity = (Testratesmaster) lentity;
				map.put(ID, entity.getIdtestrates());
				map.put(IDPATIENTCATEGORYMASTER, entity.getPatientscategorymaster().getIdpatientsType());
				map.put(IDTESTMASTER, entity.getTestmaster().getIdtests());
				map.put(RATE, entity.getRate());
				map.put(VALIDFROM, entity.getValidFrom());
				map.put(VALIDTO	, entity.getValidTo());
			 	return map; 	 
			} catch (Exception e) {
			return null;	
			}
			
	}

		
	}
