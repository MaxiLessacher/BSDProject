package pkgClasses;

public class Wasserzaehler implements Comparable<Wasserzaehler>{
	private int zaehler_nr;
	private int HH_ID;
	private int zaehlerstand;
	private boolean isHauptzaehler;
	private int standort_x;
	private int standort_y;

	public Wasserzaehler() {

	}

	public Wasserzaehler(int zaehler_nr, int HH_ID, int zaehlerstand, boolean isHauptzaehler, int standort_x, int standort_y) {
		this.zaehler_nr = zaehler_nr;
		this.HH_ID = HH_ID;
		this.zaehlerstand = zaehlerstand;
		this.isHauptzaehler = isHauptzaehler;
		this.standort_x = standort_x;
		this.standort_y = standort_y;
	}

	public int getZaehler_nr() {
		return zaehler_nr;
	}

	public void setZaehler_nr(int zaehler_nr) {
		this.zaehler_nr = zaehler_nr;
	}

	public int getHH_ID() {
		return HH_ID;
	}

	public void setHH_ID(int hH_ID) {
		HH_ID = hH_ID;
	}

	public int getZaehlerstand() {
		return zaehlerstand;
	}

	public void setZaehlerstand(int zaehlerstand) {
		this.zaehlerstand = zaehlerstand;
	}

	public boolean isHauptzaehler() {
		return isHauptzaehler;
	}

	public void setHauptzaehler(boolean isHauptzaehler) {
		this.isHauptzaehler = isHauptzaehler;
	}

	public int getStandort_x() {
		return standort_x;
	}

	public void setStandort_x(int standort_x) {
		this.standort_x = standort_x;
	}

	public int getStandort_y() {
		return standort_y;
	}

	public void setStandort_y(int standort_y) {
		this.standort_y = standort_y;
	}

	@Override
	public int compareTo(Wasserzaehler o) {
		int retValue = 1;
		if (zaehler_nr == o.getZaehler_nr()) {
			retValue = 0;
		}
		return retValue;
	}

	@Override
	public String toString() {
		return "Wasserzaehler [zaehler_nr=" + zaehler_nr + ", HH_ID=" + HH_ID
				+ ", zaehlerstand=" + zaehlerstand + ", isHauptzaehler="
				+ isHauptzaehler + ", standort_x=" + standort_x
				+ ", standort_y=" + standort_y + "]";
	}
}
