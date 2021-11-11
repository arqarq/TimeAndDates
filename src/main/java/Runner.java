import org.threeten.bp.Instant;
import org.threeten.bp.LocalDate;
import org.threeten.bp.LocalDateTime;
import org.threeten.bp.LocalTime;
import org.threeten.bp.OffsetDateTime;
import org.threeten.bp.ZoneId;
import org.threeten.bp.ZoneOffset;
import org.threeten.bp.format.DateTimeFormatter;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import static org.threeten.bp.temporal.ChronoUnit.DAYS;

public class Runner {
    public static void main(String[] args) {
        SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
        GregorianCalendar gregorianCalendar = new GregorianCalendar(2021, Calendar.OCTOBER, 15, 0, 55, 55);

        Date now = gregorianCalendar.getTime();
        Calendar calendar = Calendar.getInstance();
        calendar.set(2021, Calendar.NOVEMBER, 15, 0, 55, 55);
        Date now2 = calendar.getTime();
        String strDate = sm.format(now);
        String strDate2 = sm.format(now2);
        LocalDate wymagalnoscLocalDate = LocalDate.parse(strDate);
        OffsetDateTime wymagalnoscOffsetDateTime = OffsetDateTime.parse(strDate + "T00:00:00+01:00");
        OffsetDateTime wymagalnoscOffsetDateTime2 = OffsetDateTime.parse(strDate2 + "T00:00:00+01:00");
        long epochMilli = now.getTime();
        long epochMilli2 = now2.getTime();
        OffsetDateTime offsetDateTime = OffsetDateTime.of(
                LocalDateTime.ofInstant(Instant.ofEpochMilli(epochMilli), ZoneId.systemDefault()).truncatedTo(DAYS),
                ZoneOffset.ofTotalSeconds(TimeZone.getDefault().getOffset(epochMilli) / 1000));
        OffsetDateTime offsetDateTime2 = OffsetDateTime.of(
                LocalDateTime.ofInstant(Instant.ofEpochMilli(epochMilli2), ZoneId.systemDefault()).toLocalDate(),
                LocalTime.ofSecondOfDay(0),
                // LocalTime.of(0, 0),
                ZoneOffset.ofHours(TimeZone.getDefault().getOffset(epochMilli2) / 1000 / 3600));
        System.out.println("compare : " + wymagalnoscOffsetDateTime + " / " + offsetDateTime);
        System.out.println("compare : " + wymagalnoscOffsetDateTime + " / " + offsetDateTime.format(DateTimeFormatter.ISO_LOCAL_DATE));
        System.out.println("compare2: " + wymagalnoscOffsetDateTime2 + " / " + offsetDateTime2);
        System.out.println("compare2: " + wymagalnoscOffsetDateTime2 + " / " + offsetDateTime2.format(DateTimeFormatter.ISO_LOCAL_DATE));
    }
}
