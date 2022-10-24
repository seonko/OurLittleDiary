package com.seonko.OurLittleDiary.config.auth;

import com.seonko.OurLittleDiary.domain.Member;
import com.seonko.OurLittleDiary.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PrincipalDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberRepository.findByEmail(username).orElse(null);

        if (member == null) {
            throw new UsernameNotFoundException(username);
        }

        return new PrincipalDetails(member);
    }
}
