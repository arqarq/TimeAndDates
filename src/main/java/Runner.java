import org.threeten.bp.Instant;
import org.threeten.bp.LocalDate;
import org.threeten.bp.LocalDateTime;
import org.threeten.bp.OffsetDateTime;
import org.threeten.bp.ZoneId;
import org.threeten.bp.ZoneOffset;
import org.threeten.bp.format.DateTimeFormatter;
import pl.prokom.epwd.utilLib.toStr.ToStr;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class Runner {
    public static void main(String[] args) throws ParseException {
        Date now = new Date(2021, Calendar.OCTOBER, 10, 12, 55, 55);
        GregorianCalendar gregorianCalendar = new GregorianCalendar(2021, Calendar.OCTOBER, 10, 12, 55, 55);
        now = gregorianCalendar.getTime();
        Calendar calendar = Calendar.getInstance();
        calendar.set(2021, Calendar.OCTOBER, 15, 0, 0, 55);
        now = calendar.getTime();
        SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");

        String strDate = sm.format(now);
        LocalDate wymagalnoscLocalDate = LocalDate.parse(strDate);
        OffsetDateTime wymagalnoscOffsetDateTime = OffsetDateTime.parse(strDate + "T00:00:00+01:00");
        long epochMilli = now.getTime();
        OffsetDateTime offsetDateTime = OffsetDateTime.of(
                LocalDateTime.ofInstant(Instant.ofEpochMilli(epochMilli), ZoneId.systemDefault()),
                ZoneOffset.ofTotalSeconds(TimeZone.getDefault().getOffset(epochMilli) / 1000));
                // ZoneOffset.ofHours(1));
        System.out.println(ToStr.logAs("XXX compare", wymagalnoscOffsetDateTime.toString() + " / " + offsetDateTime));
        // System.out.println(ToStr.logAs("XXX compare", wymagalnoscOffsetDateTime + " / " + offsetDateTime.format(DateTimeFormatter.ISO_LOCAL_DATE.withZone(ZoneId.systemDefault()))));
        System.out.println(ToStr.logAs("XXX compare", wymagalnoscOffsetDateTime + " / " + offsetDateTime.format(DateTimeFormatter.ISO_LOCAL_DATE.withZone(ZoneId.of("America/El_Salvador")))));
        System.out.println(ZoneId.getAvailableZoneIds());
        System.out.println(TimeZone.getDefault().getOffset(epochMilli) / 1000);
    }
}
