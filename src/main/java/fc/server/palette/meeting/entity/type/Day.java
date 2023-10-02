package fc.server.palette.meeting.entity.type;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@RequiredArgsConstructor
public enum Day {
    MONDAY("월"), TUESDAY("화"), WEDNESDAY("수"),
    THURSDAY("목"), FRIDAY("금"), SATURDAY("토"), SUNDAY("일");

    private final String description;

    @JsonCreator
    public static List<Day> fromValue(List<String> values) {
        List<Day> days = new ArrayList<>();
        for (String value : values){
            for (Day day : Day.values()) {
                if (day.getDescription().equals(value)) {
                    days.add(day);
                }
            }
        }
        if (days.isEmpty()){
            throw new IllegalArgumentException("Invalid age: " + values);
        }
        return days;
    }
}