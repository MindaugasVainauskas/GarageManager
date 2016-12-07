package project.rad.gmit;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class Vehicle {
	private String vReg;
	private String vManCode;
	private String vModCode;
	private String vMileage;
	private String vPrice;
	private String vColour;
	private String vFuel;
	
	public Vehicle(){
		
	}	

	public Vehicle(String vReg, String vManCode, String vModCode, String vMileage, String vPrice, String vColour,
			String vFuel) {
		super();
		this.vReg = vReg;
		this.vManCode = vManCode;
		this.vModCode = vModCode;
		this.vMileage = vMileage;
		this.vPrice = vPrice;
		this.vColour = vColour;
		this.vFuel = vFuel;
	}

	public String getvReg() {
		return vReg;
	}

	public void setvReg(String vReg) {
		this.vReg = vReg;
	}

	public String getvManCode() {
		return vManCode;
	}

	public void setvManCode(String vManCode) {
		this.vManCode = vManCode;
	}

	public String getvModCode() {
		return vModCode;
	}

	public void setvModCode(String vModCode) {
		this.vModCode = vModCode;
	}

	public String getvMileage() {
		return vMileage;
	}

	public void setvMileage(String vMileage) {
		this.vMileage = vMileage;
	}

	public String getvPrice() {
		return vPrice;
	}

	public void setvPrice(String vPrice) {
		this.vPrice = vPrice;
	}

	public String getvColour() {
		return vColour;
	}

	public void setvColour(String vColour) {
		this.vColour = vColour;
	}

	public String getvFuel() {
		return vFuel;
	}

	public void setvFuel(String vFuel) {
		this.vFuel = vFuel;
	}
	
	
}
