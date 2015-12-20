package application;

import javafx.util.StringConverter;

public class IntegerConverter extends StringConverter<Integer> {

	@Override
	public String toString(Integer integer) {
		return integer.toString();
	}

	@Override
	public Integer fromString(String string) {
		return Integer.parseInt(string);
	}

}
