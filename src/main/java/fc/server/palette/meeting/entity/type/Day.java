package fc.server.palette.meeting.entity.type;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@RequiredArgsConstructor
public enum Day {
    MONDAY("월요일"), TUESDAY("화요일"), WEDNESDAY("수요일"),
    THRUSDAY("목요일"), FRIDAY("금요일"), SATURDAY("토요일"), SUNDAY("일요일");

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