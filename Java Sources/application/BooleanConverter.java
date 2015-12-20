package application;

import javafx.util.StringConverter;

public class BooleanConverter extends StringConverter<Integer> {

	@Override
	public Integer fromString(String string) {
		String retValue= "1";
		if (string.equals("false") || string.equals("0")) {
			retValue = "0";
		}
		return Integer.parseInt(retValue);
	}

	@Override
	public String toString(Integer object) {
		String retValue = "false";
		if (object!=null) {
			if (object.intValue() == 1) {
				retValue = "true";
			}
		}
		return retValue;
	}

}
