package com.example.c195_task1;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

/**
 * This Interface is used to implement the checkhours function which is used by the LambdaHours Expression
 */
@FunctionalInterface
public interface HoursTestInterface {

    /**
     * This method is implemented by the LambdaHours Expression in the Appointment Screen Controller. It takes in a ZonedDateTime for start and end as well as a customerID and an Appointment ID.
     * It then returns a boolean that lets the system know if it should commit the start and end times to the database.
     * @param start
     * @param end
     * @param cID
     * @param id
     * @return boolean true if the start and end times are valid or false if they are not.
     */
    boolean checkHours(ZonedDateTime start, ZonedDateTime end, int cID, int id);
}
