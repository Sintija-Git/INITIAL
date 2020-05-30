package jtm.extra04;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import javax.security.auth.x500.X500Principal;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONWriter;

import com.gargoylesoftware.htmlunit.javascript.host.Iterator;

public class JsonCars {

	/*- 
	 * Implement method, which returns list of cars from generated JSON String
	 */
	public List<Car> getCars(String jsonString) {
		/*- HINTS:
		 * You will need to use:
		 * - https://stleary.github.io/JSON-java/org/json/JSONObject.html
		 * - https://stleary.github.io/JSON-java/org/json/JSONArray.html
		 * You will need to initialize JSON array from "cars" key in JSON string
		 */
		
		
		
		List<Car> cars = new ArrayList<>();
		JSONObject jObjects = new JSONObject(jsonString);
		JSONArray array = jObjects.getJSONArray("cars");
		for (int i = 0; i < array.length(); i++) {
			jObjects = array.getJSONObject(i);
			Car car1 = new Car(jObjects.getString("model"), jObjects.getInt("year"), jObjects.getString("color"),
					jObjects.getFloat("price"));
			cars.add(car1);
		}
		return cars;

	}

//		List<Car> result = new ArrayList<Car>();
//
//		JSONObject jo = new JSONObject(jsonString);
//		
//		JSONArray ja =  jo.getJSONArray("cars");
//		
//		
//		for (int i = 0; i < ja.length(); i++) {
//			jo = ja.getJSONObject(i);
//			
//			Car car = new Car (jo.getString("model"), jo.getInt("year"), jo.getString("color"), jo.getFloat("price"));
//			
//			result.add(car);
//		}
//		
//
//		return result;
//}

	/*- 
	 * Implement method, which returns JSON String generated from list of cars
	 */
	public String getJson(List<Car> cars) {
		/*- HINTS:
		 * You will need to use:
		 * - https://docs.oracle.com/javase/8/docs/api/index.html?java/io/StringWriter.html
		 * - http://static.javadoc.io/org.json/json/20180130/index.html?org/json/JSONWriter.html
		 * Remember to add "car" key as a single container for array of car objects in it.
		 */
		StringWriter writer = new StringWriter();
		JSONWriter jsonWriter = new JSONWriter(writer);
		jsonWriter.object().key("cars").array();
		for (Car car : cars) {
			jsonWriter.object();
			jsonWriter.key("model").value(car.getModel());
			jsonWriter.key("year").value(car.getYear());
			jsonWriter.key("color").value(car.getColor());
			jsonWriter.key("price").value(car.getPrice());
			jsonWriter.endObject();
		}
		jsonWriter.endArray();
		jsonWriter.endObject();
		System.out.println("CARS TO STRING" + cars.toString());
		return writer.toString();
	}
}