package com.example.c195_task1;

import Model.Appointments;
import javafx.collections.ObservableList;

/**
 * This Interface is used to implement the addAppointment function, which is called by the Lambda Expression LambdaAppointments.
 */
@FunctionalInterface
public interface ScheduleInterface {

    /**
     * This method is implemented by the LambdaAppointments expression in the Appointment Screen Controller. It takes in an int, and it returns an Observable List of appointments.
     * @param days the number of days to sort by
     * @return ObservableList<Appointments></Appointments> which the system uses to update the tableview display.
     */
    ObservableList<Appointments> addAppointments(int days);

}
