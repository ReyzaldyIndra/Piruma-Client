package app.piruma_java;

import android.view.View;

import java.util.Calendar;

public class SelectedDay {
    private View mView;
    private Calendar mCalendar;

    public SelectedDay(Calendar calendar){
        mCalendar = calendar;
    }
    public SelectedDay(View view, Calendar calendar){
        mView =  view;
        mCalendar = calendar;
    }

    public View getView() {
        return mView;
    }

    public void setView(View view) {
        mView = view;
    }

    public Calendar getCalendar() {
        return mCalendar;
    }
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof com.applandeo.materialcalendarview.utils.SelectedDay) {
            return getCalendar().equals(((com.applandeo.materialcalendarview.utils.SelectedDay) obj).getCalendar());
        }

        if(obj instanceof Calendar){
            return getCalendar().equals(obj);
        }

        return super.equals(obj);
    }
}
