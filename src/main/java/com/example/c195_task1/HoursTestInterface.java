package com.example.c195_task1;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

@FunctionalInterface
public interface HoursTestInterface {

    /**
     * This lambda expression takes a start, end, customerID, and appointmentID, and it determines if there is an overlap in the start and end times between all the appointments.
     * This lambda is used by both the add and update appointments methods.
     * This lambda allows us to reuse the same code and decrease the overall clutter of the add and update methods.
     * This lambda returns a boolean.
     * @param start
     * @param end
     * @param cID
     * @param id
     * @return boolean
     */
    boolean checkHours(ZonedDateTime start, ZonedDateTime end, int cID, int id);
}
