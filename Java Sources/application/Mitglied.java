package application;

public class Mitglied {
	private int mitglieds_id;
	private String name;
	private boolean isHH_Vorstand;
	private int HH_ID;

	public Mitglied() {

	}

	public Mitglied (int mitglieds_id, String name, boolean isHH_Vorstand, int HH_ID) {
		this.mitglieds_id = mitglieds_id;
		this.name = name;
		this.HH_ID = HH_ID;
		this.isHH_Vorstand = isHH_Vorstand;
	}

	public int getMitglieds_id() {
		return mitglieds_id;
	}

	public void setMitglieds_id(int mitglieds_id) {
		this.mitglieds_id = mitglieds_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isHH_Vorstand() {
		return isHH_Vorstand;
	}

	public void setHH_Vorstand(boolean hH_Vorstand) {
		this.isHH_Vorstand = hH_Vorstand;
	}

	public int getHH_ID() {
		return HH_ID;
	}

	public void setHH_ID(int hH_ID) {
		HH_ID = hH_ID;
	}

}
