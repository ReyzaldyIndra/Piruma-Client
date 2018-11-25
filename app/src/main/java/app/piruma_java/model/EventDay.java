package app.piruma_java.model;
import app.piruma_java.DateUtils;

import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;

import java.util.Calendar;

public class EventDay {
    private Calendar mDay;
    private Object mDrawable;
    private boolean mIsDisabled;

    public EventDay (Calendar day){
        mDay = day;
    }

    public EventDay(Calendar day, @DrawableRes int drawable){
        app.piruma_java.DateUtils.setMidnight(day);
        mDay = day;
        mDrawable = drawable;
    }

    public EventDay(Calendar day, Drawable drawable){
        DateUtils.setMidnight(day);
    }
}
