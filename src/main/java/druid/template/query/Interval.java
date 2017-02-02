package druid.template.query;

import com.fasterxml.jackson.annotation.JsonValue;
import org.joda.time.LocalDate;
import org.joda.time.format.*;

/**
 * Created by Maksym_Bondarenko on 4/13/2016.
 */
public class Interval {
    public static final DateTimeFormatter full =  DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss.SSS");
    public static final DateTimeFormatter simple =  DateTimeFormat.forPattern ("yyyy-MM-dd");

    private final LocalDate from;
    private final LocalDate to;
    private DateTimeFormatter df = simple;

    /**
     * @param from beginning of the interval.
     * @param to end of the interval.
     * @throws IllegalArgumentException if 'from' date is greater than 'to' date.
     */
    public Interval(LocalDate from, LocalDate to) {
        if (from.toDate().after(to.toDate())) {
            throw new IllegalArgumentException("Date 'from' must be less or equal to the 'to' date");
        }
        this.from = from;
        this.to = to;
    }

    public void fullDateFormat(){
        df = full;
    }

    public void setDataFormat(DateTimeFormatter df) {
        this.df = df;
    }

    @JsonValue
    @Override
    public String toString() {
        return  df.print(from) + '/' + df.print(to) ;
    }
}
