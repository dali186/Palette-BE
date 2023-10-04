package fc.server.palette.member.controller;

import fc.server.palette.member.auth.CustomUserDetails;
import fc.server.palette.member.dto.request.FollowRequestDto;
import fc.server.palette.member.dto.request.MemberProfileDto;
import fc.server.palette.member.dto.response.FollowInfoDto;
import fc.server.palette.member.dto.response.MemberMyPageDto;
import fc.server.palette.member.entity.Member;
import fc.server.palette.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.hibernate.cache.spi.support.CacheUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/mypage")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;


    @GetMapping("/{memberId}")
    public ResponseEntity<?> myPageInfo (
            @PathVariable Long memberId,
            @AuthenticationPrincipal CustomUserDetails customUserDetails
    ){

        Member currentMember = customUserDetails.getMember();
        MemberMyPageDto memberMyPageDto = memberService.myPageInfo(memberId, currentMember);
        return ResponseEntity.ok(memberMyPageDto);
    }

    @GetMapping("/{memberId}/activities")
    public ResponseEntity<?> myPageInfoActivities () {

        return null;
    }

    @GetMapping("/{memberId}/bookmarks")
    public ResponseEntity<?> myPageInfoBookmarks () {
        return null;
    }

    @PostMapping("/{memberId}")
    public ResponseEntity<?> updateMember (
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @PathVariable Long memberId,
            @RequestPart(name = "image", required = false) List<MultipartFile> image,
            @RequestPart(name ="dto", required = false) MemberProfileDto memberProfileDto
   ){
       memberService.updateMember(userDetails.getMember(), image, memberProfileDto );

        return ResponseEntity.ok("수정완료");
    }



    @GetMapping("/followed/{followedId}")
    public ResponseEntity<List<FollowInfoDto>> getFollowed(
            @PathVariable Long followedId,
            @AuthenticationPrincipal CustomUserDetails customUserDetails
    ){
        List<FollowInfoDto> followed = memberService.getFollowed(followedId);
        return ResponseEntity.ok(followed);
    }



    @GetMapping("/following/{followingId}")
    public ResponseEntity<List<FollowInfoDto>> getFollowings(
            @PathVariable Long followingId,
            @AuthenticationPrincipal CustomUserDetails customUserDetails
    ){
        List<FollowInfoDto> following = memberService.getFollowings(followingId);
        return ResponseEntity.ok(following);
    }



    @PostMapping("/follow/{followedId}")
    public ResponseEntity<?> follow(
            @PathVariable Long followedId,
            @RequestBody FollowRequestDto Dto,
            @AuthenticationPrincipal CustomUserDetails userDetails
    ){
        memberService.follow(followedId, Dto.getFollowingId());
        return ResponseEntity.ok("팔로우완료");
    }


    @DeleteMapping("/follow/{followingId}")
    public ResponseEntity<?> unfollow(
            @PathVariable Long followingId,
            @AuthenticationPrincipal CustomUserDetails customUserDetails
    ){

        memberService.unfollow(customUserDetails.getMember().getId(), followingId);
        return ResponseEntity.ok("언팔로우완료");
    }
}
