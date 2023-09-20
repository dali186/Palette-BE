package fc.server.palette.meeting.entity.type;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Type {
    STUDY("스터디"), PROJECT("프로젝트"), AMITY("친목");

    private final String description;

    @JsonCreator
    public static Type fromValue(String value) {
        for (Type type : Type.values()) {
            if (type.getDescription().equals(value)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid type: " + value);
    }
}
