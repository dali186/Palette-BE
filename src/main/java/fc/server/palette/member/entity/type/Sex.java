package fc.server.palette.member.entity.type;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Sex {

    FEMALE("여성"), MALE("남성"), IRRELEVANT("무관");

    private final String value;

    @JsonCreator
    public static Sex fromValue(String value) {
        if ("여성".equals(value) || "여성만".equals(value)) {
            return FEMALE;
        } else if ("남성".equals(value)) {
            return MALE;
        } else {
            throw new IllegalArgumentException("Invalid sex: " + value);
        }
    }
}
