package fc.server.palette.meeting.entity.type;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AcceptType {
    FIRST_COME("선착순"), APPROVAL("승인제");

    private final String description;
    @JsonCreator
    public static AcceptType fromValue(String value) {
        for (AcceptType acceptType : AcceptType.values()) {
            if (acceptType.getDescription().equals(value)) {
                return acceptType;
            }
        }
        throw new IllegalArgumentException("Invalid acceptType: " + value);
    }
}
