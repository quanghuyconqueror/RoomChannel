import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class MySqlFunctions {
	private final String urlHost = "http://enddev.site50.net/RoomChannelPHPServer/";
	//ss commitchoi
	private String sendRequest(Map<String, String> params) {
		BufferedReader in;
		String result = "";
		
		try {
			StringBuilder postData = new StringBuilder();
			URL url = new URL(urlHost);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			for (Map.Entry<String,String> param : params.entrySet()) {
	            if (postData.length() != 0) postData.append('&');
	            postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
	            postData.append('=');
	            postData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
	        }
	        byte[] postDataBytes = postData.toString().getBytes("UTF-8");

	        conn.setRequestMethod("POST");
	        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
	        conn.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
	        conn.setDoOutput(true);
	        conn.getOutputStream().write(postDataBytes);
	        in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
	        String line = null;
	        
	        while ((line = in.readLine()) != null) {
	        	result += (line + "\n");
	        }
		}
        catch (Exception e) {
        	e.printStackTrace();
        }
   
        return result;
	}
	
	public User register(String username, String password, String email, String name, String city, String type) {
		User registerUser = null;
		Map<String,String> params = new LinkedHashMap<>();
        params.put("tag", "register");
        params.put("username", username);
        params.put("password", password);
        params.put("email", email);
        params.put("name", name);
        params.put("city", city);
        params.put("type", type);
        String jsonResponse = sendRequest(params);
		
        try {
			JSONArray userJSONArray = new JSONArray(jsonResponse);
			JSONObject userJSON = userJSONArray.getJSONObject(0);
			registerUser = new User();
			registerUser.setUsername(userJSON.getString("Username"));
			registerUser.setPassword(userJSON.getString("Password"));
			registerUser.setEmail(userJSON.getString("Email"));
			registerUser.setName(userJSON.getString("Name"));
			registerUser.setCity(userJSON.getString("City"));
			registerUser.setType(userJSON.getString("Type"));
		} catch (JSONException e) {
			try {
				JSONObject errorJSON = new JSONObject(jsonResponse);
				String error = errorJSON.getString("error_msg");
				System.out.println("Error: " + error);
			} catch (JSONException e1) {
				e1.printStackTrace();
			}
			
		}
        
        
		return registerUser;
		
	}
	
	public User login(String username, String password) {
	
		User user = null;
		Map<String,String> params = new LinkedHashMap<>();
        params.put("tag", "login");
        params.put("username", username);
        params.put("password", password);
        String jsonResponse = sendRequest(params);
        
        try {
			JSONArray userJSONArray = new JSONArray(jsonResponse);
			JSONObject userJSON = userJSONArray.getJSONObject(0);
			user = new User();
			user.setUsername(userJSON.getString("Username"));
			user.setPassword(userJSON.getString("Password"));
			user.setEmail(userJSON.getString("Email"));
			user.setName(userJSON.getString("Name"));
			user.setCity(userJSON.getString("City"));
			user.setType(userJSON.getString("Type"));
		} catch (JSONException e) {
			try {
				JSONObject errorJSON = new JSONObject(jsonResponse);
				String error = errorJSON.getString("error_msg");
				System.out.println("Error: " + error);
			} catch (JSONException e1) {
				e1.printStackTrace();
			}
			
		}
        
		return user;
		
	}
	
	public ArrayList<Room> loadAllRoom() {
		ArrayList<Room> rooms = null;
		Map<String,String> params = new LinkedHashMap<>();
        params.put("tag", "load_all_room");
        String jsonResponse = sendRequest(params);
        try {
			JSONArray roomJSONArray = new JSONArray(jsonResponse);
			rooms = new ArrayList<Room>();
			for (int i = 0; i < roomJSONArray.length(); i++) {
				JSONObject roomJSON = roomJSONArray.getJSONObject(i);
				Room room = new Room();
				room.setRoomID(roomJSON.getString("RoomID"));
				room.setAddress(roomJSON.getString("Address"));
				room.setDescription(roomJSON.getString("Description"));
				room.setTimePosted(roomJSON.getString("TimePosted"));
				room.setLatitude(roomJSON.getString("Latitude"));
				room.setLongitude(roomJSON.getString("Longitude"));
				room.setCity(roomJSON.getString("City"));
				room.setImages(roomJSON.getString("Images"));
				room.setCost(roomJSON.getString("Cost"));
				room.setBooked(roomJSON.getString("Booked"));
				room.setUserPostID(roomJSON.getString("UserPostID"));
				rooms.add(room);
			}
			
			
		} catch (JSONException e) {
			try {
				JSONObject errorJSON = new JSONObject(jsonResponse);
				String error = errorJSON.getString("error_msg");
				System.out.println("Error: " + error);
			} catch (JSONException e1) {
				e1.printStackTrace();
			}
			
		}

		return rooms;
		
	}
	
	public Room postRoom(String address, String description, String latitude, String longitude, String city, String images, String cost, String userPostID) {
		Room room = null;
		Map<String,String> params = new LinkedHashMap<>();
        params.put("tag", "post_room");
        params.put("address", address);
        params.put("description", description);
        params.put("latitude", latitude);
        params.put("longitude", longitude);
        params.put("city", city);
        params.put("images", images);
        params.put("cost", cost);
        params.put("userPostID", userPostID);

        String jsonResponse = sendRequest(params);
		
        try {
			JSONArray roomJSONArray = new JSONArray(jsonResponse);
			
			JSONObject roomJSON = roomJSONArray.getJSONObject(0);
			room = new Room();
			room.setRoomID(roomJSON.getString("RoomID"));
			room.setAddress(roomJSON.getString("Address"));
			room.setDescription(roomJSON.getString("Description"));
			room.setTimePosted(roomJSON.getString("TimePosted"));
			room.setLatitude(roomJSON.getString("Latitude"));
			room.setLongitude(roomJSON.getString("Longitude"));
			room.setCity(roomJSON.getString("City"));
			room.setImages(roomJSON.getString("Images"));
			room.setCost(roomJSON.getString("Cost"));
			room.setBooked(roomJSON.getString("Booked"));
			room.setUserPostID(roomJSON.getString("UserPostID"));
		}
			
			
		catch (JSONException e) {
			try {
				JSONObject errorJSON = new JSONObject(jsonResponse);
				String error = errorJSON.getString("error_msg");
				System.out.println("Error: " + error);
			} catch (JSONException e1) {
				e1.printStackTrace();
			}
			
		}
		return room;
		
		
	}
	

	
}
