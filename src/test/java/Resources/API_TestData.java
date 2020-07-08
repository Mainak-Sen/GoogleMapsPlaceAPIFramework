package Resources;

import java.util.ArrayList;
import java.util.List;

import utilities.Add_Place_POJO;
import utilities.Update_Place_POJO;

public class API_TestData {

	public Add_Place_POJO add_place_payload(String name, String phone_number, String address, String language) {
	Add_Place_POJO app=new Add_Place_POJO();
	app.setAccuracy(50);
	app.setName(name);
	app.setPhone_number(phone_number);
	app.setAddress(address);
	app.setWebsite("http://google.com");
	app.setLanguage(language);
	utilities.location l=new utilities.location();
	l.setLat(-38.383494);
	l.setLng(33.427362);
	app.setLocation(l);
	List<String> myList=new ArrayList<String>();
	myList.add("shoe park");
	myList.add("shop");
	app.setTypes(myList);
	
	return app;
}
	
	public Update_Place_POJO update_place_payload(String address,String place_id) {
		Update_Place_POJO upp=new Update_Place_POJO();
		upp.setPlace_id(place_id);
		upp.setAddress(address);
		upp.setKey("qaclick123");
		
		return upp;	
	}
	
	public String delete_place_payload(String place_id)
	{
		return "{\r\n    \"place_id\":\""+place_id+"\"\r\n}";
	}
	
}	
