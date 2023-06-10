
public class User {               // class for user's information
	private String id;
	private String password;
	private String phoneNum;
	private Reservation reservation;
	
	public User(String id, String password, String phoneNum, Reservation reservation) { // constructor of user class
		setId(id);
		setPassword(password);
		setPhoneNum(phoneNum);
		setReservation(reservation);
	}

	// getter and setter for user class
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public Reservation getReservation() {
		return reservation;
	}

	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
	}
}
