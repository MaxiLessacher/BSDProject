package pkgClasses;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Verwaltungspersonal implements Comparable<Verwaltungspersonal> {
	private int personal_id;
	private String name;
	private String abteilung;

	public Verwaltungspersonal() {

	}

	public Verwaltungspersonal(int personal_id, String name, String abteilung) {
		this.personal_id = personal_id;
		this.name = name;
		this.abteilung = abteilung;
	}

	public int getPersonal_id() {
		return personal_id;
	}

	public void setPersonal_id(int personal_id) {
		this.personal_id = personal_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAbteilung() {
		return abteilung;
	}

	public void setAbteilung(String abteilung) {
		this.abteilung = abteilung;
	}

	@Override
	public int compareTo(Verwaltungspersonal o) {
		int retValue = 1;
		if (personal_id == o.getPersonal_id()) {
			retValue = 0;
		}
		return retValue;
	}

	@Override
	public String toString() {
		return "Verwaltungspersonal [personal_id=" + personal_id + ", name="
				+ name + ", abteilung=" + abteilung + "]";
	}
}
