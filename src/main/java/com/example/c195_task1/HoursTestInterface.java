package com.example.c195_task1;

import java.time.LocalDateTime;
@FunctionalInterface
public interface HoursTestInterface {

    boolean checkHours(LocalDateTime start, LocalDateTime end, int id);
}
