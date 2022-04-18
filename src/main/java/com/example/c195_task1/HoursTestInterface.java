package com.example.c195_task1;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

@FunctionalInterface
public interface HoursTestInterface {

    boolean checkHours(ZonedDateTime start, ZonedDateTime end, int cID, int id);
}
