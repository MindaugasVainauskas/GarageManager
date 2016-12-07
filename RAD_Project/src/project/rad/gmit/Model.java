package project.rad.gmit;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class Model {
	//Variables	
	private String modelManuCode;
	private String modelCode;
	private String modelName;
	private String modelDesc;

	public Model(){
		
	}

	public Model(String manuCode, String modelCode, String modelName, String modelDesc) {
		super();
		this.modelManuCode = manuCode;
		this.modelCode = modelCode;
		this.modelName = modelName;
		this.modelDesc = modelDesc;
	}

	
	
	public String getModelManuCode() {
		return modelManuCode;
	}

	public void setModelManuCode(String modelManuCode) {
		this.modelManuCode = modelManuCode;
	}

	public String getModelCode() {
		return modelCode;
	}

	public void setModelCode(String modelCode) {
		this.modelCode = modelCode;
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
	
	
	
}
