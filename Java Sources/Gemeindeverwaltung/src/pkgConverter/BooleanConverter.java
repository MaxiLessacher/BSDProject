package pkgConverter;

import javafx.util.StringConverter;

public class BooleanConverter extends StringConverter<Boolean> {

	@Override
	public Boolean fromString(String string) {
		boolean retValue= true;
		if (string.equals("false") || string.equals("0")) {
			retValue = false;
		}
		return retValue;
	}

	@Override
	public String toString(Boolean object) {
		return object.toString();
	}

}
