package project.rad.gmit;

import java.util.ArrayList;
import javax.faces.bean.ManagedBean;

@ManagedBean
public class GarageController {
	
	private String manuName;
	private String manuDetails;	
	
	//model vars	
	private String modelName;
	private String modelDesc;
	
	//vehicle vars
	private String reg;
	private String manCode;
	private String modCode;
	private String vMil;
	private String vPrice;
	private String vCol;
	private String vFuel;
	private String vPriceCond;//used for search
	
	//index for getting full vehicle details
	private int index= 0;
	
	ArrayList<Manufacturer> manufacturers;
	ArrayList<Model> models;
	ArrayList<Vehicle> vehicles;
	ArrayList<VehicleDetails> vehData;
	ArrayList<VehicleDetails> foundVehicles;
	DAO dao;
			
	public GarageController() throws Exception{
		dao = new DAO();
		loadManufacturers();
		loadModels();
		loadVehicles();
		loadVehData();	
		loadSearchResults();
	}	


	// ------------------Methods -------------------------
	public void loadVehicles() {		
		vehicles = dao.getVehicles();			
	}

	public void loadModels() {		
		models = dao.getModels();			
	}
	
	public void loadManufacturers(){		
		manufacturers = dao.getManufacturers();		
	}

	private void loadVehData() {	
		vehData = dao.getVehicleDetails();		
	}
	
	// --------------------- finding full vehicle details -------------------------------------------------
	//getting the vehicle details found
	public String GetVehDetails(String vReg){	
		ArrayList<VehicleDetails> vData = vehData;
		int ind = 0;
		for(VehicleDetails veh : vData){
			if(veh.getReg().equalsIgnoreCase(vReg)){
				break;
			}
			else{
				ind++;
			}
		}
		
		setIndex(ind);// set the index to current value		
		return "AllVehicleDetails.xhtml";
	}
	
	
	// --------------------- Adding new Manufacturer ------------------------------
	public String addManufacturer(String mCode, String mName, String mDetails){
		Manufacturer man = new Manufacturer(mCode, mName, mDetails);		
		dao.AddManufacturer(man.getManuCode(), man.getManuName(), man.getManuDetails());		
		return "Manufacturers";
	}
	// --------------------------- Updating manufacturer --------------------------
	// updates manufacturer data(manufacturer code update disabled but still read in for locating row purpose)
	public String UpdateManufacturer(String manCode, String manName, String manDetails){
		Manufacturer man = new Manufacturer(manCode, manName, manDetails);
		//send update info to the dao updating method.		
		dao.UpdateManufacturer(man.getManuCode(), man.getManuName(), man.getManuDetails());		
		return "Manufacturers";
	}
	
	// goes to update page while carrying current row manufacturer data.
	public String goToUpdates(String manCode, String manName, String manDetails){
		//set up new object of type Manufacturer
		Manufacturer man = new Manufacturer(manCode, manName, manDetails);	
		//set the default values in input text fields that user will see when page loads.
		this.setManCode(man.getManuCode());
		this.setManuName(man.getManuName());
		this.setManuDetails(man.getManuDetails());
		
		return "UpdateManufacturer";
	}	
	
	//---------------------------- Deleting manufacturer from the list ----------------------
	public String deleteManufacturer(String manCode){	
		dao.DeleteManufacturer(manCode);		
		return "Manufacturers";
	}
	
	// --------------------- Adding new Model -------------------------------------
	public String addModel(String mCode, String moCode, String moName, String mDesc){
		Model mod = new Model(mCode, moCode, moName, mDesc);		
		dao.AddModel(mod.getModelManuCode(), mod.getModelCode(), mod.getModelName(), mod.getModelDesc());		
		return "Models";
	}
	//---------------------------- Deleting model from list ------------------------------------
	public String deleteModel(String modCode){		
		dao.DeleteModel(modCode);		
		return "Models";
	}
	
	// ----------------------- Adding new Vehicle ----------------------------------
	public String addVehicle(String reg, String maCode, String moCode, String mil, String price, String col, String fuel){
		Vehicle veh = new Vehicle(reg, maCode, moCode, mil, price, col, fuel);		
		dao.AddVehicle(veh.getvReg(), veh.getvManCode(), veh.getvModCode(), veh.getvMileage(), veh.getvPrice(), veh.getvColour(), veh.getvFuel());		
		return "Vehicles";
	}
	
	// ---------------------- Deleting vehicle from list -----------------------------------------
	public String deleteVehicle(String vReg){		
		dao.DeleteVehicle(vReg);		
		return "Vehicles";
	}
	
	//---------------------- search for Vehicles ------------------------------
	public String vehicleSearch(String vPriceCond, String vPrice, String vCol, String vFuel){		
		dao.VehicleSearch(vPriceCond, vPrice, vCol, vFuel);		
		setFoundVehicles(dao.loadFoundVehicles());		
		loadSearchResults();
		
		return "DisplayVehicles";
	}
	public void loadSearchResults(){
		foundVehicles = getFoundVehicles();
	}
	
	// ----------------- index getter and setter ------------------------------
	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	
// ----------------- Manufacturer Getters and Setters -------------------------------
	
	public String getManuName() {
		return manuName;
	}

	public ArrayList<VehicleDetails> getFoundVehicles() {
		return foundVehicles;
	}


	public void setFoundVehicles(ArrayList<VehicleDetails> foundVehicles) {
		this.foundVehicles = foundVehicles;
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
	
	public ArrayList<VehicleDetails> getVehData() {
		return vehData;
	}

	public void setVehData(ArrayList<VehicleDetails> vehData) {
		this.vehData = vehData;
	}

	public String getvPriceCond() {
		return vPriceCond;
	}


	public void setvPriceCond(String vPriceCond) {
		this.vPriceCond = vPriceCond;
	}


	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public String getModelDesc() {
		return modelDesc;
	}

	public void setModelDesc(String modelDesc) {
		this.modelDesc = modelDesc;
	}
	
	//manufacturers getter and setter
	public ArrayList<Manufacturer> getManufacturers() {
		return manufacturers;
	}	

	public void setManufacturers(ArrayList<Manufacturer> manufacturers) {
		this.manufacturers = manufacturers;
	}
	// models getter and setter
	public ArrayList<Model> getModels() {
		return models;
	}

	public void setModels(ArrayList<Model> models) {
		this.models = models;
	}

	// ------- Vehicle Getters and Setters -------------------------------------

	public ArrayList<Vehicle> getVehicles() {
		return vehicles;
	}


	public void setVehicles(ArrayList<Vehicle> vehicles) {
		this.vehicles = vehicles;
	}


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


	public String getModCode() {
		return modCode;
	}


	public void setModCode(String modCode) {
		this.modCode = modCode;
	}


	public String getvMil() {
		return vMil;
	}


	public void setvMil(String vMil) {
		this.vMil = vMil;
	}


	public String getvPrice() {
		return vPrice;
	}


	public void setvPrice(String vPrice) {
		this.vPrice = vPrice;
	}


	public String getvCol() {
		return vCol;
	}


	public void setvCol(String vCol) {
		this.vCol = vCol;
	}


	public String getvFuel() {
		return vFuel;
	}


	public void setvFuel(String vFuel) {
		this.vFuel = vFuel;
	}	
	
}
