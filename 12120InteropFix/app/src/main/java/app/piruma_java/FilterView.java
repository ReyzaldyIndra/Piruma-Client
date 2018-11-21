package app.piruma_java;

import app.piruma_java.base.BaseView;

public interface FilterView extends BaseView {
void showDay(int day, int month, int year, int dayOfWeek);

void invalidDate();

void onDateValid(String date);
}
