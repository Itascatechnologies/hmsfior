package in.co.itasca.hms.api.service;

import static in.co.itasca.hms.api.Constants.ENTITYSET_CARS;
import static in.co.itasca.hms.api.Constants.ENTITYSET_DEPARTMENT;
import static in.co.itasca.hms.api.Constants.ENTITYSET_MENUSROLES;
import static in.co.itasca.hms.api.Constants.ENTITYSET_MENUS;
import static in.co.itasca.hms.api.Constants.ENTITYSET_PATIENTCATEGORYMASTER;
import static in.co.itasca.hms.api.Constants.ENTITYSET_REGISTRATIONFEEMASTER;
import static in.co.itasca.hms.api.Constants.ENTITYSET_REGISTRATION;
import static in.co.itasca.hms.api.Constants.ENTITYSET_ROLE;

import static in.co.itasca.hms.api.Constants.ENTITYSET_SEXS;

import static in.co.itasca.hms.api.Constants.ENTITYSET_TESTMASTER;

import static in.co.itasca.hms.api.Constants.ENTITYSET_TESTRATESMASTER;

import static in.co.itasca.hms.api.Constants.ENTITYSET_TESTREGISTRATION;
import static in.co.itasca.hms.api.Constants.ENTITYSET_USER;
import static in.co.itasca.hms.api.Constants.ENTITYSET_USERROLES;
 
public class ServiceFactory {
private String role ;
	
	public ServiceFactory(String role){
		this.role=role;
	}
	public static ServiceFactory getInstance(String role){
	return new ServiceFactory(role);	
	}
	
	public IService getService(String entitySetName){
		 if(entitySetName.equals(ENTITYSET_CARS)){
			 
			 return new CarsService(role);
		 } else if(entitySetName.equals(ENTITYSET_SEXS)){
			 return new SexService(role);
		 } else if (entitySetName.equals(ENTITYSET_DEPARTMENT)){
			 return new DepartmentService(role);
		 } else if (entitySetName.equals(ENTITYSET_MENUS)){
			 return new MenusService(role);
		 } else if (entitySetName.equals(ENTITYSET_MENUSROLES)){
			 return new MenusRolesService(role);
		 } else if (entitySetName.equals(ENTITYSET_PATIENTCATEGORYMASTER)){
			 return new PatientCategoryMasterService(role);
		 } else if(entitySetName.equals(ENTITYSET_REGISTRATION)){
			 return new RegistrationService(role);
		 } else if(entitySetName.equals(ENTITYSET_REGISTRATIONFEEMASTER)){
			 return new RegistrationFeeMasterService(role);
		 } else if (entitySetName.equals(ENTITYSET_ROLE)){
			 return new RolesService(role);
		 } else if (entitySetName.equals(ENTITYSET_TESTMASTER)){
			 return new TestMasterService(role);
		 } else if (entitySetName.equals(ENTITYSET_TESTRATESMASTER)){
			 return new TestRatesMasterService(role);
		 } else if (entitySetName.equals(ENTITYSET_TESTREGISTRATION)){
			 return new TestRegistrationService(role);
		 } else if (entitySetName.equals(ENTITYSET_USER)){
			 return new UsersService(role);
		 } else if (entitySetName.equals(ENTITYSET_USERROLES)){
			 return new UsersRolesServices(role);
		 }
		 
		return null;
	}
	
 
	}
	










































