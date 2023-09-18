package fc.server.palette.member.auth;


import fc.server.palette.member.auth.jwt.AuthoritiesProvider;
import fc.server.palette.member.entity.Member;
import fc.server.palette.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Member member = memberRepository.findByEmail(email).orElseThrow(
                () -> new UsernameNotFoundException("해당 이메일을 가진 회원이 존재하지 않습니다."));
        return createUser(member);
    }

    @Transactional
    public boolean isSamePwd(Long id, String rawPassword) {
        Member member = memberRepository.findById(id).orElseThrow(
                () -> new RuntimeException("존재하지 않는 유저입니다."));
        return passwordEncoder.matches(rawPassword, member.getPassword());
    }

    private org.springframework.security.core.userdetails.User createUser(Member member) {

        log.info("Member: {}", member);
        return new org.springframework.security.core.userdetails.User(member.getId().toString(), member.getPassword(), AuthoritiesProvider.getAuthorityCollection());
    }

}
