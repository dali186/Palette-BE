package fc.server.palette.meeting.entity.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Status {
    WAITING("승인 대기"), APPROVAL("승인"), REFUSE("거절");

    private final String description;
}
