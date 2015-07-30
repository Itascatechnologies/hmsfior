package in.co.itasca.hms.api.service;

import java.util.List;
import java.util.Map;

import org.apache.olingo.odata2.api.exception.ODataException;
import org.apache.olingo.odata2.api.processor.ODataResponse;
import org.apache.olingo.odata2.api.uri.info.GetEntitySetUriInfo;
import org.apache.olingo.odata2.api.uri.info.GetEntityUriInfo;

public interface IService {

	
	 public List<Map<String, Object>> getRecords();
	 public List<Map<String, Object>> getAssociation(int id, String associatedEdm);
	 public Map<String, Object> getRecord(int id);
	 public Map<String, Object> createNewRecord(Map<String, Object> record);
	 public Map<String, Object> updateRecord(int id, Map<String, Object> record);
	 public boolean deleteUpdate(int id);


}
