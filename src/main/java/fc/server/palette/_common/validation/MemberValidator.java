package fc.server.palette._common.validation;

public class MemberValidator {
    public static void validateAuthority(Long memberId, Long authorId){
        if (!memberId.equals(authorId)) {
            throw new IllegalArgumentException("권한이 없습니다.");
        }
    }
}
