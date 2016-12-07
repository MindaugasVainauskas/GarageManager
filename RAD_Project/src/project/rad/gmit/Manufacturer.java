package project.rad.gmit;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class Manufacturer {
	// ------------ Variables ------------------------------
	private String manuCode;
	private String manuName;
	private String manuDetails;
	
	// -------------------- Constructors -------------------------------
	public Manufacturer(){
		
	}

	public Manufacturer(String manuCode, String manuName, String manuDetails) {		
		this.manuCode = manuCode;
		this.manuName = manuName;
		this.manuDetails = manuDetails;
	}
	
	// ----------------- Getters And Setters -----------------------

	public String getManuCode() {
		return manuCode;
	}

	public void setManuCode(String manuCode) {
		this.manuCode = manuCode;
	}

	public String getManuName() {
		return manuName;
	}

	public void setManuName(String manuName) {
		this.manuName = manuName;
	}

	public String getManuDetails() {
		return manuDetails;
	}

	public void setManuDetails(String manuDetails) {
		this.manuDetails = manuDetails;
	}
	
}
