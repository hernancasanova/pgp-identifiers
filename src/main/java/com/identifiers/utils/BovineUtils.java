package com.identifiers.utils;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class BovineUtils {

	public static String getAge(LocalDateTime dateBirth) {

		if (dateBirth == null) return null;

		LocalDateTime now = LocalDateTime.now();

		long years = ChronoUnit.YEARS.between(dateBirth, now);
		dateBirth = dateBirth.plusYears(years);

		long months = ChronoUnit.MONTHS.between(dateBirth, now);
		dateBirth = dateBirth.plusMonths(months);

		long days = ChronoUnit.DAYS.between(dateBirth, now);

		String yearsStr = years > 1 ? years + " años, " :
						years == 1 ? "1 año, " : "";

		String monthsStr = months == 1 ? "1 mes, " :
						months == 0 ? "" :
						months + " meses, ";

		String daysStr = days == 1 ? "1 día" : days + " días";

		return yearsStr + monthsStr + daysStr;
	}
	
	
	public static String getDaysBetween(LocalDateTime dateStart, LocalDateTime dateEnd) {

		if (dateStart == null) return null;

		//LocalDateTime now = LocalDateTime.now();

		long years = ChronoUnit.YEARS.between(dateStart, dateEnd);
		dateStart = dateStart.plusYears(years);

		long months = ChronoUnit.MONTHS.between(dateStart, dateEnd);
		dateStart = dateStart.plusMonths(months);

		long days = ChronoUnit.DAYS.between(dateStart, dateEnd);

		String yearsStr = years > 1 ? years + " años, " :
						years == 1 ? "1 año, " : "";

		String monthsStr = months == 1 ? "1 mes, " :
						months == 0 ? "" :
						months + " meses, ";

		String daysStr = days == 1 ? "1 día" : days + " días";

		return yearsStr + monthsStr + daysStr;
	}
	
	
	public static String formatDiio(String diio) {
	    return diio.matches("\\d{9}")
	            ? diio.replaceAll("^(\\d{2})(\\d{3})(\\d{4})$", "$1.$2.$3")
	            : diio;
	}
}