package com.example.c195_task1;

import Model.Reports;
import javafx.collections.ObservableList;

/**
 *
 */
@FunctionalInterface
public interface MonthCounterInterface {

    /**
     *  This lambda expression is used to count all appointments by type.
     *  It counts up each month as it gets a hit.
     *  It was necessary to use a lambda expression, so I could reset the month counters for each type.
     * @param type
     * @return report object
     */
    Reports getCounted(String type);
}
