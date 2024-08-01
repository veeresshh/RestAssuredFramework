package resources;

import java.util.ArrayList;
import java.util.List;

import POJO.AddPlace;
import POJO.Location;

public class TestDataBuild {

	public AddPlace addPlacePayLoad(String name, String language, String address) {

		AddPlace p = new AddPlace();

		p.setAccuracy(50);
		p.setAddress(address);
		p.setLanguage(language);
		p.setPhone_number("(+91) 983 893 3937");
		p.setWebsite("https://rahulshettyacademy.com");
		p.setName(name);

		List<String> Type = new ArrayList<String>();
		Type.add("shoe park");
		Type.add("shop");

		p.setTypes(Type);

		Location Loc = new Location();
		Loc.setLat(-38.383494);
		Loc.setLng(33.427362);

		p.setLocation(Loc);

		return p;

	}

	public String deletePlacePayload(String placeId) {
		return "{\r\n    \"place_id\":\"" + placeId + "\"\r\n}";
	}

}
