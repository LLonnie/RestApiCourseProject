package resources;

import mapsPojos.AddPlace;
import mapsPojos.AddPlace_Location;

import java.util.ArrayList;
import java.util.List;

public class TestDataBuild {

	public AddPlace addPlacePayload(String name, String language, String address) {
		AddPlace_Location location = new AddPlace_Location();
		location.setLat(-38.383494);
		location.setLng(33.427362);

		AddPlace place = new AddPlace();
		place.setLocation(location);
		place.setAccuracy(50);
		place.setName(name);
		place.setPhone_number("(+91) 983 893 3937");
		place.setAddress(address);
		place.setWebsite("http://google.com");
		place.setLanguage(language);

		List<String> types = new ArrayList<>();
		types.add("shoe park");
		types.add("shop");
		place.setTypes(types);

		return place;
	}

	public String deletePlacePayload(String placeId) {
		return "{\n" +
				"	\"place_id\": \"" + placeId + "\"\n" +
				"}";
	}
}
