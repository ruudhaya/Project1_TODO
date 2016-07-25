package com.pattash.udhaya.mytodo.extras;

import com.pattash.udhaya.mytodo.model.Task;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;

/**
 * Created by Udhay on 7/25/2016.
 */
public class DateComparator implements Comparator<Task> {
    DateFormat f = new SimpleDateFormat("dd/MM/yyyy");

    @Override
    public int compare(Task lhs, Task rhs) {
        String leftDate = lhs.getTargetDate();
        String rightDate = rhs.getTargetDate();

        try {
            return f.parse(leftDate).compareTo(f.parse(rightDate));
        } catch (ParseException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
