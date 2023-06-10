
public class Reservation {           // class for information of reservation
	private String event;
	private String date;
	private String match;
	private String etc;
	
	public Reservation(String event, String date, String match, String etc) { // constructor for reservation class
		setEvent(event);
		setDate(date);
		setMatch(match);
		
		if (etc != null) { // Because etc field is optional, so if it is null, do not set Etc field.
			setEtc(etc);
		}
	}

	// getter and setter for reservation class
	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getMatch() {
		return match;
	}

	public void setMatch(String match) {
		this.match = match;
	}

	public String getEtc() {
		return etc;
	}

	public void setEtc(String etc) {
		this.etc = etc;
	}
	
}
