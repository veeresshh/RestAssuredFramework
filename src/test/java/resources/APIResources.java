package resources;

//enum is special class in java which has collection of constants or methods
public enum APIResources {
	
	AddPlaceAPI("/maps/api/place/add/json"),
	GetPlaceAPI("/maps/api/place/get/json"),
	DeletePlaceAPI("/maps/api/place/delete/json");
	
	private String Resource;
	
	// Constructor
	APIResources(String Resource)  
	
	{
		this.Resource=Resource;
	}
	
	public String getResource()
	{
		return Resource;
	}

}
