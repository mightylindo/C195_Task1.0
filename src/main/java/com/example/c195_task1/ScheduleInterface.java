package com.example.c195_task1;

import Model.Appointments;
import javafx.collections.ObservableList;
@FunctionalInterface
public interface ScheduleInterface {

    /**
     *
     *  This lambda expression is used to select all the appointments based on the number of days input.
     *  This lambda is currently used by the radio buttons to select all appointments within the week or month.
     *  By using a lambda we can add other buttons easily such as quarterly or yearly, and we just use the lambda with different number of days.
     *  This lambda returns a list of appointments.
     *
     * @param days the number of days to sort by
     * @return ObservableList<Appointments></Appointments>
     */
    ObservableList<Appointments> addAppointments(int days);

}
