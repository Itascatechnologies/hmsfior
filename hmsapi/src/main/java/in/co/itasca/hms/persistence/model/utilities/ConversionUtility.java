package in.co.itasca.hms.persistence.model.utilities;

public class ConversionUtility {

	private ConversionUtility() {

	}
	
	public static ConversionUtility getInstance(){
		
		return new ConversionUtility();
	}
	
	public boolean active(int active){
		if(active==1)
			return true;
		else 
		return false;
		
	}
	
	public int active(boolean active){
		
		
		if(active)
			return 1;
		else 
		return 0;
	
		
	}
}
