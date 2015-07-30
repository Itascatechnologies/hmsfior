package in.co.itasca.hms.api.service;

import in.co.itasca.hms.api.CarDataStore;

import java.util.List;
import java.util.Map;

import org.apache.olingo.odata2.api.exception.ODataException;
import org.apache.olingo.odata2.api.processor.ODataResponse;
import org.apache.olingo.odata2.api.uri.info.GetEntitySetUriInfo;
import org.apache.olingo.odata2.api.uri.info.GetEntityUriInfo;

public class CarsService extends BaseService {

	public CarsService(String role) {
		super(role);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Map<String, Object>> getRecords() {
		CarDataStore ds = new CarDataStore();
		return ds.getCars();
	}

	@Override
	public List<Map<String, Object>> getAssociation(int id, String associatedEdm) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> getRecord(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> createNewRecord(Map<String, Object> record) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> updateRecord(int id, Map<String, Object> record) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteUpdate(int id) {
		// TODO Auto-generated method stub
		return false;
	}
 
	

}
