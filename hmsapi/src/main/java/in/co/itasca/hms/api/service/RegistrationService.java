package in.co.itasca.hms.api.service;

import in.co.itasca.hms.persistence.dao.DepartmentDAO;
import in.co.itasca.hms.persistence.dao.PatientCategoryMasterDAO;
import in.co.itasca.hms.persistence.dao.RegistrationDAO;
import in.co.itasca.hms.persistence.dao.RegistrationDAO;
import in.co.itasca.hms.persistence.dao.SexDAO;
import in.co.itasca.hms.persistence.model.Department;
import in.co.itasca.hms.persistence.model.Patientscategorymaster;
import in.co.itasca.hms.persistence.model.Registration;
import in.co.itasca.hms.persistence.model.Registrationfeemaster;
import in.co.itasca.hms.persistence.model.Sex;
import in.co.itasca.hms.persistence.model.utilities.IUtility;
import in.co.itasca.hms.persistence.model.utilities.RegistrationUtility;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.edit.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

public class RegistrationService extends BaseService {
 
 

	public RegistrationService(String role) {
		super(role);
		super.idao=new RegistrationDAO();
		super.utility= new  RegistrationUtility();
	}

	@Override
	public List<Map<String, Object>> getRecords() {
try {
			
			
			List<Map<String, Object>> collection = new ArrayList<Map<String, Object>>();
			RegistrationDAO	 dao = (RegistrationDAO) idao;
					
			List<Registration> list =dao.getAll();
			for (Iterator iterator = list.iterator(); iterator.hasNext();) {
				Registration entity = (Registration) iterator.next();
						Map<String, Object> map= new HashMap<String, Object>();
				collection.add(utility.convertModelTOEDM(map, entity));
			}
			 return collection;
			} catch (Exception e) {
			return  null;
			}
	}

	@Override
	public List<Map<String, Object>> getAssociation(int id, String associatedEdm) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> getRecord(int id) {
		try {
			RegistrationDAO dao = (RegistrationDAO) idao;
				Registration entity =			dao.getById(id);
				Map<String, Object> map= new HashMap<String, Object>();
				return utility.convertModelTOEDM(map, entity);
		} catch (Exception e) {
return null;
		}
	}

	@Override
	public Map<String, Object> createNewRecord(Map<String, Object> record) {
		try {
			 Registration entity = new Registration();
			 RegistrationUtility  ut = (RegistrationUtility) utility;
			entity.setActive(1);  
		//	entity.setAgedate((Date) record.get(entity.AGE));
			String age =(String) record.get(ut.AGE);
			entity.setAgein((String) age +"" + (record.get(ut.AGEINWORDS)));
			Integer iddepInt = (Integer) record.get(ut.IDDEPARTMENT);
			int iddepartment=iddepInt.intValue();
			DepartmentDAO daoDepartment = new DepartmentDAO();
			Department department = daoDepartment.getById(iddepartment);
			entity.setDepartment(department);
			Integer idPatCatInt = (Integer) record.get(ut.IDPAIENTCATEGORY);
			int idPatientCategory = idPatCatInt.intValue();
			PatientCategoryMasterDAO daoPatientCategory = new PatientCategoryMasterDAO();
			Patientscategorymaster patientCategory =daoPatientCategory.getById(idPatientCategory);
			
			Registrationfeemaster regisgrationFee =patientCategory.getRegistrationfeemasters().get(0);
			
			entity.setName((String) record.get(ut.NAME));
			entity.setPrice(regisgrationFee.getFee());
			entity.setRegistrationfeemaster(regisgrationFee);
			Integer sexIdInt = (Integer) record.get(ut.IDSEX);
			int sexId = sexIdInt.intValue();
			SexDAO daoSex = new SexDAO();
			Sex sex =daoSex.getById(sexId);
			entity.setSexBean(sex);
			
			entity.setCreatedOn(new Date());
			entity.setCreatedBy("SANJAY");
			
			entity.setValidFrom(new Date());
			entity.setValidTo(new Date());
			RegistrationDAO dao =(RegistrationDAO) super.idao;
			entity =dao.saveNew(entity);
						Map<String, Object> map= new HashMap<String, Object>();
			
			return utility.convertModelTOEDM(map, entity);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public Map<String, Object> updateRecord(int id, Map<String, Object> record) {
		try {
			RegistrationDAO dao = (RegistrationDAO) idao;
			Registration entity ;
			entity =dao.getById(id);
		//	boolean active = (boolean) data.get(entity.ACTIVE);
			entity.setChangedBy("AMIT");
			// 	entity.setName((String) data.get(entity.NAME));
			entity =dao.save(entity);
						Map<String, Object> map= new HashMap<String, Object>();
			return utility.convertModelTOEDM(map, entity);
			 
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	@Override
	public boolean deleteUpdate(int id) {
		try {
			RegistrationDAO dao = (RegistrationDAO) idao;
			Registration entity =dao.getById(id);
			
		dao.delete(entity);
		return true;
		} catch (Exception e) {
			return false;
		}
 
	}
	
	public PDDocument getPDF( int id ){
		
		try {
		//	 Map<String, Object> result = getRecord(id);
			RegistrationDAO dao = new RegistrationDAO();
			Registration registration =dao.getById(id);
			PDDocument document = new PDDocument();
			  PDPage page = new PDPage();
			  document.addPage( page );

			  // Create a new font object selecting one of the PDF base fonts
			  PDFont font = PDType1Font.HELVETICA_BOLD;
			  
			  // Start a new content stream which will "hold" the to be created content
			  PDPageContentStream contentStream = new PDPageContentStream(document, page);

			  // Define a text content stream using the selected font, moving the cursor and drawing the text "Hello World"
			  contentStream.beginText();
			  contentStream.setFont( font, 30 );
			  contentStream.moveTextPositionByAmount( 100, 700 );
			  contentStream.drawString( "Government Hospital, Roorkee" );
			  contentStream.endText();
			  contentStream.beginText();
			  contentStream.setFont( font, 12 );
			  contentStream.moveTextPositionByAmount( 100, 680 );
			  contentStream.drawString("Registration No. : "+registration.getIdregistration()) ;
			  contentStream.drawString("                       Date : "+registration.getCreatedOn()) ;
			  contentStream.endText();
			  
			  contentStream.beginText();
			  contentStream.setFont( font, 12 );
			  contentStream.moveTextPositionByAmount( 100, 660 );
			  contentStream.drawString("Patient Category No. : "+registration.getRegistrationfeemaster().getPatientscategorymaster().getPatientsTypeDescription()) ;
			  contentStream.drawString("                      Fee : "+registration.getPrice()) ;
			  contentStream.endText();
				  
			  contentStream.beginText();
			  contentStream.setFont( font, 12 );
			  contentStream.moveTextPositionByAmount( 100, 640 );
			  contentStream.drawString("Patient's Name : "+registration.getName()) ;
 			   contentStream.drawString("                      Sex : "+registration.getSexBean().getSexText()) ;
			  contentStream.drawString("       Age   : "+registration.getAgein()) ;
			  contentStream.endText();
				  
			  contentStream.drawLine(0, 620, 600, 620);
			  // Make sure that the content stream is closed:
			  contentStream.close();
			  return document;
		} catch (Exception e) {
return null;
		}
	}

}
