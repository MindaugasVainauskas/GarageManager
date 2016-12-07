/* 
 * Mindaugas Vainauskas 
 * Data Centric RAD module
 * Year 3 semester 1 Software Development course
 * JSF with MySql database web application project
 */
package project.rad.gmit;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.mysql.jdbc.exceptions.jdbc4.CommunicationsException;

public class DAO {
	// ------------ variables and lists -----------------
	private ArrayList<Manufacturer> manufacturers;
	private ArrayList<Model> models;
	private ArrayList<Vehicle> vehicles;
	private ArrayList<VehicleDetails> vehDetails;
	private ArrayList<VehicleDetails> foundVehicles;
	private DataSource mysqlDS;
	 
	// ------------- Constructor -------------------------------
	public DAO() throws Exception {
	    Context context = new InitialContext();
	    String jndiName = "java:comp/env/jdbc/garage";
	    mysqlDS = (DataSource) context.lookup(jndiName);
	}
	 
// -------------------------- Display manufacturer details -------------------------------------
	protected ArrayList<Manufacturer>getManufacturers(){
		try {
			manufacturers = new ArrayList<>();
			Connection conn = mysqlDS.getConnection();
			
			Statement myStmt = conn.createStatement();
			Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			String query = "SELECT * FROM manufacturer";
			ResultSet rs = myStmt.executeQuery(query);
			
			while(rs.next()){
				String manuCode = rs.getString("manu_code");
				String manuName = rs.getString("manu_name");	
				String manuDetails = rs.getString("manu_details");
				
				//add product into product list
				manufacturers.add(new Manufacturer(manuCode, manuName, manuDetails));
			}
						
			//close all the open connections
			conn.close();
			myStmt.close();
			stmt.close();
			rs.close();
			
		}catch(CommunicationsException e){
			System.out.println(e.getMessage());			
			String msg = "Error: Can not connect to Database!";
			FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg);
			FacesContext.getCurrentInstance().addMessage(null, facesMessage);
		} catch (SQLException e) {		
			System.out.println(e.getMessage());
			String msg = "Error: Could not get the Manufacturer Details!!!";			
			FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg);
			FacesContext.getCurrentInstance().addMessage(null, facesMessage);
		}catch (Exception e){
			e.printStackTrace();
		}	
		return manufacturers;
	}
	
	// -------------------------- get the models -----------------------------
	protected ArrayList<Model>getModels(){
		try {
			models = new ArrayList<>();
			Connection conn = mysqlDS.getConnection();
			
			Statement myStmt = conn.createStatement();
			Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			String query = "SELECT * FROM model";
			ResultSet rs = myStmt.executeQuery(query);
			
			while(rs.next()){
				String manuCode = rs.getString("manu_code");
				String modelCode = rs.getString("model_code");	
				String modelName = rs.getString("model_name");
				String modelDesc = rs.getString("model_desc");
				
				//add product into product list
				models.add(new Model(manuCode, modelCode, modelName, modelDesc));
			}
						
			//close all the open connections
			conn.close();
			myStmt.close();
			stmt.close();
			rs.close();
			
		} catch(CommunicationsException e){
			System.out.println(e.getMessage());			
			String msg = "Error: Can not connect to Database!";
			FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg);
			FacesContext.getCurrentInstance().addMessage(null, facesMessage);
		}catch (SQLException e) {		
			System.out.println(e.getMessage());
			String msg = "Error: Could not get the Model Details!!!";			
			FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg);
			FacesContext.getCurrentInstance().addMessage(null, facesMessage);
		}catch (Exception e){
			e.printStackTrace();
		}	
		return models;
	}
	
	// ----------------------------- get the Vehicles -----------------------------
	protected ArrayList<Vehicle>getVehicles(){
		try {
			vehicles = new ArrayList<>();
			Connection conn = mysqlDS.getConnection();
			
			Statement myStmt = conn.createStatement();
			Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			String query = "SELECT * FROM vehicle";
			ResultSet rs = myStmt.executeQuery(query);
			
			while(rs.next()){
				String reg = rs.getString("reg");
				String veManCode = rs.getString("manu_code");	
				String veModCode = rs.getString("model_code");
				String mil = rs.getString("mileage");
				String pri = rs.getString("price");
				String col = rs.getString("colour");
				String fue = rs.getString("fuel");
				
				//add product into product list
				vehicles.add(new Vehicle(reg, veManCode, veModCode, mil, pri, col, fue));
			}
						
			//close all the open connections
			conn.close();
			myStmt.close();
			stmt.close();
			rs.close();
			
		} catch(CommunicationsException e){
			System.out.println(e.getMessage());			
			String msg = "Error: Can not connect to Database!";
			FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg);
			FacesContext.getCurrentInstance().addMessage(null, facesMessage);
		}catch (SQLException e) {		
			System.out.println(e.getMessage());
			String msg = "Error: Could not get the Vehicle Details!!!";			
			FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg);
			FacesContext.getCurrentInstance().addMessage(null, facesMessage);
		}catch (Exception e){
			e.printStackTrace();
		}	
		
		return vehicles;
		
	}
	
	// -------------------- Get full Vehicle Details ---------------------------
	protected ArrayList<VehicleDetails>getVehicleDetails(){
		try {
			vehDetails = new ArrayList<>();
			Connection conn = mysqlDS.getConnection();
			
			Statement myStmt = conn.createStatement();
			Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			String query = "SELECT v.reg, ma.manu_code, ma.manu_name, ma.manu_details"
					+ ", mo.model_code, mo.model_name, mo.model_desc, v.mileage, v.price, "
					+ "v.colour, v.fuel FROM vehicle v INNER JOIN manufacturer ma on(v.manu_code=ma.manu_code)"
					+ "INNER JOIN model mo on(v.model_code = mo.model_code)";
			ResultSet rs = myStmt.executeQuery(query);
			
			while(rs.next()){
				String reg = rs.getString("v.reg");
				String manCode = rs.getString("ma.manu_code");	
				String manName = rs.getString("ma.manu_name");
				String manDesc = rs.getString("ma.manu_details");
				String modCode = rs.getString("mo.model_code");
				String modName = rs.getString("mo.model_name");
				String modDesc = rs.getString("mo.model_desc");
				String mil = rs.getString("v.mileage");
				String pri = rs.getString("v.price");
				String col = rs.getString("v.colour");
				String fuel = rs.getString("v.fuel");
				
				//add product into product list
				vehDetails.add(new VehicleDetails(reg, manCode, manName, manDesc, modCode, modName, modDesc, mil, pri, col, fuel));
			}
						
			//close all the open connections
			conn.close();
			myStmt.close();
			stmt.close();
			rs.close();
			
		}catch(CommunicationsException e){
			System.out.println(e.getMessage());			
			String msg = "Error: Can not connect to Database!";
			FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg);
			FacesContext.getCurrentInstance().addMessage(null, facesMessage);
		} catch (SQLException e) {		
			System.out.println(e.getMessage());
			String msg = "Error: Could not get the Vehicle Details!!!";			
			FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg);
			FacesContext.getCurrentInstance().addMessage(null, facesMessage);
		}catch (Exception e){
			e.printStackTrace();
		}	
		
		return vehDetails;
		
	}

	
	// ----------------------------- search for vehicles -------------------------------
	protected ArrayList<VehicleDetails> VehicleSearch(String vPriceCond, String vPrice, String vCol, String vFuel){		
		try {
			foundVehicles = new ArrayList<>();
			Connection conn = mysqlDS.getConnection();
			String addColour;
			String addPrice;
			String setPCond = "";
			
			//following sets string to add to query depending if colour has been specified or not
			if(!vCol.equalsIgnoreCase("")){
				addColour = " and v.colour like '"+vCol+"'";// colour gets added to colour query substring 
			}
			else{
				addColour = "";//colour query substring stays null 
			}
			
			//following sets string to add price constraint to query depending on whether price has been entered or not
			if(!vPrice.equalsIgnoreCase("")){
				if(vPriceCond.equalsIgnoreCase("less")){
					setPCond = "<";
				}else if(vPriceCond.equalsIgnoreCase("more")){
					setPCond = ">";
				}else if(vPriceCond.equalsIgnoreCase("equal")){
					setPCond = "=";
				}
				
				//add the price condition and price amount to query substring to be added
				addPrice = " and v.price "+setPCond+" '"+vPrice+"'";				
			}
			else{				
				addPrice = "";//query substring stays null
			}
				
			
			//set up query string with added substrings in case colour and/or price gets specified
			String query = "SELECT v.reg, ma.manu_code, ma.manu_name, mo.model_code, mo.model_name, v.mileage, v.price, "
						+ "v.colour, v.fuel FROM vehicle v INNER JOIN manufacturer ma on(v.manu_code=ma.manu_code)"
						+ "INNER JOIN model mo on(v.model_code = mo.model_code) where v.fuel like ?"+addColour+addPrice;
			
			//prepare statement with string query
			PreparedStatement ps = conn.prepareStatement(query);
						
			//next set the values to be inserted into table with insertion order shown			
			ps.setString(1, vFuel);
			
			//execute prepared statement
			ResultSet rs = ps.executeQuery();				
			
			while(rs.next()){
				String reg = rs.getString("v.reg");
				String manCode = rs.getString("ma.manu_code");	
				String manName = rs.getString("ma.manu_name");			
				String modCode = rs.getString("mo.model_code");
				String modName = rs.getString("mo.model_name");				
				String mil = rs.getString("v.mileage");
				String pri = rs.getString("v.price");
				String col = rs.getString("v.colour");
				String fuel = rs.getString("v.fuel");
				
				//add product into product list
				foundVehicles.add(new VehicleDetails(reg, manCode, manName, modCode, modName, mil, pri, col, fuel));			
		}
					
		//close all the open connections
		conn.close();		
		rs.close();
		
		}catch(CommunicationsException e){
			System.out.println(e.getMessage());			
			String msg = "Error: Can not connect to Database!";
			FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg);
			FacesContext.getCurrentInstance().addMessage(null, facesMessage);
		} catch (SQLException e) {		
			System.out.println(e.getMessage());
			String msg = "Error: Could not get the Vehicle Details!!!";			
			FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg);
			FacesContext.getCurrentInstance().addMessage(null, facesMessage);
		}catch (Exception e){
			e.printStackTrace();
		}		
		
		return foundVehicles;
	}
	//load found vehicle arraylist
	protected ArrayList<VehicleDetails> loadFoundVehicles(){
		return foundVehicles;
	}
	
	// -------------------------- Add new Manufacturer ----------------------------------
	protected void AddManufacturer(String manCode, String manName, String manDetails){		
		try {			
			
			Connection conn = mysqlDS.getConnection();			
			
			//set up query string
			String query = "INSERT INTO manufacturer(manu_code, manu_name, manu_details) VALUES(?, ?, ?)";
			
			//Capitalise first letter of manufacturer name prior to addition to the DB
			manName = manName.substring(0, 1).toUpperCase()+manName.substring(1);
			//prepare statement with string query
			PreparedStatement ps = conn.prepareStatement(query);
						
			//next set the values to be inserted into table with insertion order shown			
			ps.setString(1, manCode.toUpperCase());
			ps.setString(2, manName);
			ps.setString(3, manDetails);	
			//execute prepared statement
			ps.execute();				
						
			//close all the open connections
			conn.close();			
			ps.close();
			
		} catch(CommunicationsException e){
			System.out.println(e.getMessage());			
			String msg = "Error: Can not connect to Database!";
			FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg);
			FacesContext.getCurrentInstance().addMessage(null, facesMessage);
		}catch (SQLException e) {	
			e.printStackTrace();
			String msg = e.getMessage();
			System.out.println(msg);
			FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg);
			FacesContext.getCurrentInstance().addMessage(null, facesMessage);
		}catch (Exception e){
			e.printStackTrace();
		}		
		
	}
	
	//------------------------- Add new model ------------------------
	
	protected void AddModel(String manCode, String modCode, String modName, String modDesc){		
		try {			
			
			Connection conn = mysqlDS.getConnection();			
			
			//set up query string
			String query = "INSERT INTO model(manu_code, model_code, model_name, model_desc) VALUES(?, ?, ?, ?)";			
			
			//prepare statement with string query
			PreparedStatement ps = conn.prepareStatement(query);
						
			//Capitalise first letter of model name prior to addition to the DB
			modName = modName.substring(0, 1).toUpperCase()+modName.substring(1);
			//next set the values to be inserted into table with insertion order shown			
			ps.setString(1, manCode.toUpperCase());  //convert manufacturer code into upper case
			ps.setString(2, modCode.toUpperCase());  //convert model code to upper case
			ps.setString(3, modName);
			ps.setString(4, modDesc);
			//execute prepared statement
			ps.execute();				
						
			//close all the open connections
			conn.close();			
			ps.close();
			
		}catch(CommunicationsException e){
			System.out.println(e.getMessage());			
			String msg = "Error: Can not connect to Database!";
			FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg);
			FacesContext.getCurrentInstance().addMessage(null, facesMessage);
		}catch (SQLException e) {				
			String msg = e.getMessage();
			System.out.println(msg);
			FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg);
			FacesContext.getCurrentInstance().addMessage(null, facesMessage);
		}catch (Exception e){
			e.printStackTrace();
		}			
		
	}
	
	//------------------------- Add new Vehicle ------------------------
	
	protected void AddVehicle(String reg, String maCode, String moCode, String mil, String price, String col, String fuel){		
		try {			
			
			Connection conn = mysqlDS.getConnection();			
			
			//set up query string
			String query = "INSERT INTO vehicle(reg, manu_code, model_code, mileage, price, "
					+ "colour, fuel) VALUES(?, ?, ?, ?, ?, ?, ?)";
			
			//prepare statement with string query
			PreparedStatement ps = conn.prepareStatement(query);
						
			//Capitalise first letter of Colour prior to addition to the DB
			col = col.substring(0, 1).toUpperCase()+col.substring(1);
			//next set the values to be inserted into table with insertion order shown			
			ps.setString(1, reg.toUpperCase()); 
			ps.setString(2, maCode.toUpperCase());  //convert manufacturer code into upper case
			ps.setString(3, moCode.toUpperCase());  //convert model code to upper case
			ps.setString(4, mil);
			ps.setString(5, price);
			ps.setString(6, col);
			ps.setString(7, fuel);
			//execute prepared statement
			ps.execute();				
						
			//close all the open connections
			conn.close();			
			ps.close();
			
		} catch(CommunicationsException e){
			System.out.println(e.getMessage());			
			String msg = "Error: Can not connect to Database!";
			FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg);
			FacesContext.getCurrentInstance().addMessage(null, facesMessage);
		}catch (SQLException e) {				
			String msg = e.getMessage();
			System.out.println(msg);
			FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg);
			FacesContext.getCurrentInstance().addMessage(null, facesMessage);
		}catch (Exception e){
			e.printStackTrace();
		}			
		
	}

	// -------------------- Update manufacturer details ---------------------------
	protected void UpdateManufacturer(String manCode, String manName, String manDetails){		
		try {			
			
			Connection conn = mysqlDS.getConnection();			
			
			//set up query string
			String query = "UPDATE manufacturer SET manu_name = ?, manu_details = ? where manu_code = ?";
			//System.out.println(manName+"; Man Code: "+manCode);//sysout line used to make sure data reaches dao properly
			//prepare statement with string query
			PreparedStatement ps = conn.prepareStatement(query);
						
			//next set the values to be updated. In this case its Manufacturers name and details. Code can't be updated(Primary Key)			
			ps.setString(1, manName);  //convert model code to upper case
			ps.setString(2, manDetails);
			ps.setString(3, manCode.toUpperCase());
			//execute prepared statement
			ps.execute();				
						
			//close all the open connections
			conn.close();			
			ps.close();
			
		} catch(CommunicationsException e){
			System.out.println(e.getMessage());			
			String msg = "Error: Can not connect to Database!";
			FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg);
			FacesContext.getCurrentInstance().addMessage(null, facesMessage);
		}catch (SQLException e) {				
			String msg = e.getMessage();
			System.out.println(msg);
			FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg);
			FacesContext.getCurrentInstance().addMessage(null, facesMessage);
		}catch (Exception e){
			e.printStackTrace();
		}			
		
	}
	
	// ----------------------- Deleting manufacturer from database ------------------------------------
	protected void DeleteManufacturer(String manCode){		
		try {			
			
			Connection conn = mysqlDS.getConnection();			
			
			//set up query string
			String query = "DELETE from manufacturer where manu_code = ?";			
			//prepare statement with string query
			PreparedStatement ps = conn.prepareStatement(query);
						
			//next set the manufacturer code to be deleted	
			ps.setString(1, manCode.toUpperCase());
			//execute prepared statement
			ps.execute();				
						
			//close all the open connections
			conn.close();			
			ps.close();
			
		}catch(CommunicationsException e){
			System.out.println(e.getMessage());			
			String msg = "Error: Can not connect to Database!";
			FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg);
			FacesContext.getCurrentInstance().addMessage(null, facesMessage);
		} catch (SQLException e) {				
			String msg = e.getMessage();
			System.out.println(msg);
			FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg);
			FacesContext.getCurrentInstance().addMessage(null, facesMessage);
		}catch (Exception e){
			e.printStackTrace();
		}			
		
	}
	
	// ------------------ Following 2 methods have been implemented on top of normal requirements for the project -------------------------------------
	// ------------------ Reason for this addition was a more complete and realistic feeling of the application --------------------------------
	//-------------------- Deleting model from database --------------------------------
	protected void DeleteModel(String modCode){		
		try {			
			
			Connection conn = mysqlDS.getConnection();			
			
			//set up query string
			String query = "DELETE from model where model_code = ?";			
			//prepare statement with string query
			PreparedStatement ps = conn.prepareStatement(query);
						
			//next set the manufacturer code to be deleted	
			ps.setString(1, modCode.toUpperCase());
			//execute prepared statement
			ps.execute();				
						
			//close all the open connections
			conn.close();			
			ps.close();
			
		}catch(CommunicationsException e){
			System.out.println(e.getMessage());			
			String msg = "Error: Can not connect to Database!";
			FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg);
			FacesContext.getCurrentInstance().addMessage(null, facesMessage);
		} catch (SQLException e) {				
			String msg = e.getMessage();
			System.out.println(msg);
			FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg);
			FacesContext.getCurrentInstance().addMessage(null, facesMessage);
		}catch (Exception e){
			e.printStackTrace();
		}			
		
	}
	
	//----------------------- Deleting vehicle from Database ------------------------------------
	protected void DeleteVehicle(String vReg){		
		try {			
			
			Connection conn = mysqlDS.getConnection();			
			
			//set up query string
			String query = "DELETE from vehicle where reg = ?";			
			//prepare statement with string query
			PreparedStatement ps = conn.prepareStatement(query);
						
			//next set the manufacturer code to be deleted	
			ps.setString(1, vReg.toUpperCase());
			//execute prepared statement
			ps.execute();				
						
			//close all the open connections
			conn.close();			
			ps.close();
			
		}catch(CommunicationsException e){
			System.out.println(e.getMessage());			
			String msg = "Error: Can not connect to Database!";
			FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg);
			FacesContext.getCurrentInstance().addMessage(null, facesMessage);
		} catch (SQLException e) {				
			String msg = e.getMessage();
			System.out.println(msg);
			FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg);
			FacesContext.getCurrentInstance().addMessage(null, facesMessage);
		}catch (Exception e){
			e.printStackTrace();
		}			
		
	}
}
