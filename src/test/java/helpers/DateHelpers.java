package helpers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.Locale;

public class DateHelpers {
    Logger logger = LogManager.getLogger(DateHelpers.class);

    public Boolean isDataEqualAndAfterCurrent(LocalDate date) {
        logger.info("Card date: " + date);
        return date.isEqual(LocalDate.now()) || date.isAfter(LocalDate.now());
    }

    public Boolean isDateValidCurrentWeek(LocalDate date) {
        LocalDate now = LocalDate.now();
        logger.info("Current date: " + now);
        LocalDate firstDayOfWeek = now.with(WeekFields.of(Locale.FRANCE).dayOfWeek(), 1L);
        logger.info("Monday date: " + firstDayOfWeek);
        LocalDate lastDayOfWeek = now.with(WeekFields.of(Locale.FRANCE).dayOfWeek(), 7L);
        logger.info("Sunday date: " + lastDayOfWeek);
        return date.isAfter(firstDayOfWeek) && date.isBefore(lastDayOfWeek);
    }
}
