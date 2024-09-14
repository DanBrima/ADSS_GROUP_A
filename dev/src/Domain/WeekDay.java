package Domain;

import Presentation.IO;

public enum WeekDay {
    SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY;

    public static WeekDay getWeekDayFromIO(IO io) {
        return valueOf(io.readString("Enter the week day (SUNDAY/MONDAY/TUESDAY/WEDNESDAY/THURSDAY/FRIDAY/SATURDAY):"));
    }
}
