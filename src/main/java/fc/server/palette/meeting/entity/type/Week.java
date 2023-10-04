package fc.server.palette.meeting.entity.type;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Week {
    EVERY_WEEK("매주"), EVERY_OTHER_WEEK("격주"), MONTHLY("매달");

    private final String description;

    @JsonCreator
    public static Week fromValue(String value) {
        if (value == null){
            return null;
        }
        for (Week week : Week.values()) {
            if (week.getDescription().equals(value)) {
                return week;
            }
        }
        throw new IllegalArgumentException("Invalid week: " + value);
    }
}
