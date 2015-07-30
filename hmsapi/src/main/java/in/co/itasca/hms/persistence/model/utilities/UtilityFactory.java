package in.co.itasca.hms.persistence.model.utilities;
import static  in.co.itasca.hms.api.Constants.ENTITYSET_DEPARTMENT;
import static  in.co.itasca.hms.api.Constants.ENTITYSET_MENUS;
import static  in.co.itasca.hms.api.Constants.ENTITYSET_MENUSROLES;
import static  in.co.itasca.hms.api.Constants.ENTITYSET_PATIENTCATEGORYMASTER;
import static  in.co.itasca.hms.api.Constants.ENTITYSET_REGISTRATION;
import static  in.co.itasca.hms.api.Constants.ENTITYSET_REGISTRATIONFEEMASTER;
import static  in.co.itasca.hms.api.Constants.ENTITYSET_ROLE;
import static  in.co.itasca.hms.api.Constants.ENTITYSET_SEXS;
import static  in.co.itasca.hms.api.Constants.ENTITYSET_TESTMASTER;
import static  in.co.itasca.hms.api.Constants.ENTITYSET_TESTRATESMASTER;
import static  in.co.itasca.hms.api.Constants.ENTITYSET_TESTREGISTRATION;
import static  in.co.itasca.hms.api.Constants.ENTITYSET_USER;
import static  in.co.itasca.hms.api.Constants.ENTITYSET_USERROLES;

public  class UtilityFactory {

	private UtilityFactory() {
		// TODO Auto-generated constructor stub
	}

	public static UtilityFactory getInstance (){
		return new UtilityFactory();
	}
	
	public IUtility getInstance ( String key){
		if (key.equals(ENTITYSET_DEPARTMENT)){
			return new DepartmentsUtility();
		} else if (key.equals(ENTITYSET_MENUS)){
			return new MenusUtility();
		}else if (key.equals(ENTITYSET_MENUSROLES)){
			return null;
		}else if (key.equals(ENTITYSET_PATIENTCATEGORYMASTER)){
			return new PatientCategoryMasterUtility();
		} else if (key.equals(ENTITYSET_REGISTRATION)){
			return new RegistrationUtility();
		}else if (key.equals(ENTITYSET_REGISTRATIONFEEMASTER)){
			return new RegistrationFeeMasterUtility();
		}else if (key.equals(ENTITYSET_ROLE)){
			return new RoleUtility();
		}else if (key.equals(ENTITYSET_SEXS)){
			return new SexUtility();
		}else if (key.equals(ENTITYSET_TESTMASTER)){
			return new MenusUtility();
		}else if (key.equals(ENTITYSET_TESTRATESMASTER)){
			return new TestRatesMasterUtility();
		} else if (key.equals(ENTITYSET_TESTREGISTRATION)){
			return new TestRegistrationUtility();
		}else if (key.equals(ENTITYSET_USER)){
			return null;
		}else if (key.equals(ENTITYSET_USERROLES)){
			return null;
		}
		return null;
	}
}
