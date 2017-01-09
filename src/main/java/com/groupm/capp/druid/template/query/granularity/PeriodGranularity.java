package com.groupm.capp.druid.template.query.granularity;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.TimeZone;

/**
 * Created by Maksym_Bondarenko on 4/20/2016.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PeriodGranularity {

    protected enum Period {

        Y, M, W, D, H, S
    }

    private enum Type {

        period, duration
    }

    private Type type;
    private String period;
    private String timeZone;
    private Period internal_period;
    private String origin;

    public PeriodGranularity(Period period) {
        type = Type.period;
        internal_period = period;
        this.period = "P" + 1 + period;
    }

    public Type getType() {
        return type;
    }

    public String getPeriod() {
        return period;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public void setCount(int count) {
        if (count > 0) {
            period = "P" + count + internal_period;
        }
    }

    public void month(int count) {
        period = "P" + count + Period.M;
    }

    public void month() {
        month(1);
    }

    public void week() {
        week(1);
    }

    public void week(int count) {
        period = "P" + count + Period.W;
    }

    public void day() {
        day(1);
    }

    public void day(int count) {
        period = "P" + count + Period.D;
    }

    public void setTimeZone(TimeZone timeZone) {
        this.timeZone = timeZone.getDisplayName();
    }

    @Override
    public String toString() {
        return "" + period + (timeZone == null ? "," : "\"timeZone\": \"" + timeZone + "\" ") + (origin == null ? "," : "\"origin\": \"" + origin + "\" ") + " }";
    }
}
