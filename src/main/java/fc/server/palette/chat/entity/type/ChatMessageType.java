package fc.server.palette.chat.entity.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ChatMessageType {
    CHAT,
    JOIN,
    LEAVE,
    IMAGE,
    FILE,
    SHARE,
    SYSTEM
}
