package project.rad.gmit;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class VehicleDetails {
	private String reg;
	private String manCode;
	private String manName;
	private String manDetails;
	private String modCode;
	private String modName;
	private String modDesc;
	private String mileage;
	private String price;
	private String colour;
	private String fuel;

	public VehicleDetails(){
		
	}

	public VehicleDetails(String reg, String manCode, String manName, String manDetails, String modCode, String modName,
			String modDesc, String mileage, String price, String colour, String fuel) {
		super();
		this.reg = reg;
		this.manCode = manCode;
		this.manName = manName;
		this.manDetails = manDetails;
		this.modCode = modCode;
		this.modName = modName;
		this.modDesc = modDesc;
		this.mileage = mileage;
		this.price = price;
		this.colour = colour;
		this.fuel = fuel;
	}
	
	//--------------------- Extra constructor for use with vehicle search ----------------------------
	public VehicleDetails(String reg, String manCode, String manName, String modCode, String modName, String mileage, String price, String colour, String fuel) {
		super();
		this.reg = reg;
		this.manCode = manCode;
		this.manName = manName;		
		this.modCode = modCode;
		this.modName = modName;		
		this.mileage = mileage;
		this.price = price;
		this.colour = colour;
		this.fuel = fuel;
	}

	//----------------- getters and setters --------------------------------
	public String getReg() {
		return reg;
	}

	public void setReg(String reg) {
		this.reg = reg;
	}

	public String getManCode() {
		return manCode;
	}

	public void setManCode(String manCode) {
		this.manCode = manCode;
	}

	public String getManName() {
		return manName;
	}

	public void setManName(String manName) {
		this.manName = manName;
	}

	public String getManDetails() {
		return manDetails;
	}

	public void setManDetails(String manDetails) {
		this.manDetails = manDetails;
	}

	public String getModCode() {
		return modCode;
	}

	public void setModCode(String modCode) {
		this.modCode = modCode;
	}

	public String getModName() {
		return modName;
	}

	public void setModName(String modName) {
		this.modName = modName;
	}

	public String getModDesc() {
		return modDesc;
	}

	public void setModDesc(String modDesc) {
		this.modDesc = modDesc;
	}

	public String getMileage() {
		return mileage;
	}

	public void setMileage(String mileage) {
		this.mileage = mileage;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getColour() {
		return colour;
	}

	public void setColour(String colour) {
		this.colour = colour;
	}

	public String getFuel() {
		return fuel;
	}

	public void setFuel(String fuel) {
		this.fuel = fuel;
	}
	
	
}
