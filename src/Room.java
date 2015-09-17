
public class Room implements Comparable<Room>{
	private String roomID;
	private String address;
	private String description;
	private String timePosted;
	private String latitude;
	private String longitude;
	private String city;
	private String images;
	private String cost;
	private String booked;
	private String userPostID;
	public String getRoomID() {
		return roomID;
	}
	public void setRoomID(String roomID) {
		this.roomID = roomID;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getTimePosted() {
		return timePosted;
	}
	public void setTimePosted(String timePosted) {
		this.timePosted = timePosted;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getImages() {
		return images;
	}
	public void setImages(String images) {
		this.images = images;
	}
	public String getCost() {
		return cost;
	}
	public void setCost(String cost) {
		this.cost = cost;
	}
	public String getBooked() {
		return booked;
	}
	public void setBooked(String booked) {
		this.booked = booked;
	}
	public String getUserPostID() {
		return userPostID;
	}
	public void setUserPostID(String userPostID) {
		this.userPostID = userPostID;
	}
	
	@Override
	public String toString() {
		return "Room [roomID=" + roomID + ", address=" + address
				+ ", description=" + description + ", timePosted=" + timePosted
				+ ", latitude=" + latitude + ", longitude=" + longitude
				+ ", city=" + city + ", images=" + images + ", cost=" + cost
				+ ", booked=" + booked + ", userPostID=" + userPostID + "]";
	}
	@Override
	public int compareTo(Room o) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
	
	
}
