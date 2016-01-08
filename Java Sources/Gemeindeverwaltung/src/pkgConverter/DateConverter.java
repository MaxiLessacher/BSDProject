package pkgConverter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javafx.util.StringConverter;

public class DateConverter extends StringConverter<Date> {

	@Override
	public Date fromString(String string) {
		DateFormat formatter = new SimpleDateFormat("yyyy-dd-MM");
		Date returnDate = null;
		try {
			returnDate = formatter.parse(string);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return returnDate;
	}

	@Override
	public String toString(Date object) {
		return object.toString();
	}

}
