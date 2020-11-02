package cc.jml1024.subdragon.security.serivce;

import cc.jml1024.subdragon.entity.SysUser;
import cc.jml1024.subdragon.service.SysUserSerivce;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Evil
 */
@Service
public class SysUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private SysUserSerivce sysUserSerivce;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser sysUser = sysUserSerivce.getByUsername(username);
        List<GrantedAuthority> authorities = new ArrayList<>();
        GrantedAuthority authority = new SimpleGrantedAuthority("ADMIN");
        authorities.add(authority);
        sysUser.setAuthorities(authorities);
        return sysUser;
    }
}
