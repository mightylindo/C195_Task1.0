package com.example.c195_task1;

import Model.Appointments;
import javafx.collections.ObservableList;
@FunctionalInterface
public interface ScheduleInterface {

    ObservableList<Appointments> addAppointments(int days);

}
