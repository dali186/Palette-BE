package fc.server.palette.meeting.entity.type;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@RequiredArgsConstructor
public enum Age {
    TWENTIES("20대"), THIRTIES("30대"), FORTIES("40대"), FIFTIES("50대"), SIXTIES("60대");

    private final String description;

    @JsonCreator
    public static List<Age> fromValue(List<String> values) {
        List<Age> ages = new ArrayList<>();
        for (String value : values){
            for (Age age : Age.values()) {
                if (age.getDescription().equals(value)) {
                    ages.add(age);
                }
            }
        }
        if (ages.isEmpty()){
            throw new IllegalArgumentException("Invalid age: " + values);
        }
        return ages;
    }
}
