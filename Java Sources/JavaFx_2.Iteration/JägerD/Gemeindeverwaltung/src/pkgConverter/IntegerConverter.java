package pkgConverter;

import javafx.util.StringConverter;

public class IntegerConverter extends StringConverter<Integer> {

	@Override
	public String toString(Integer integer) {
		String retValue = "";
		if (integer != null) {
			retValue = integer.toString();
		}
		return retValue;
	}

	@Override
	public Integer fromString(String string) {
		int retValue = 0;
		if (!string.isEmpty()) {
			retValue = Integer.parseInt(string);
		}
		return retValue;
	}

}
