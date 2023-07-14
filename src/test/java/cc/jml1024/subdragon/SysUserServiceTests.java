package cc.jml1024.subdragon;

import cc.jml1024.subdragon.entity.SysUser;
import cc.jml1024.subdragon.qry.SysUserQry;
import cc.jml1024.subdragon.service.SysUserSerivce;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SysUserServiceTests {

    @Autowired
    private SysUserSerivce sysUserSerivce;

    private Long newUserId;

    @Test
    public void test_save() {
        SysUser user = new SysUser();
        user.setUsername("root");
        user.setPassword(new BCryptPasswordEncoder().encode("123456"));
        user.setEnabled(true);
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);
        user.setCreateDate(new Date());
        sysUserSerivce.save(user);
        newUserId = user.getId();
        System.out.println(newUserId);
    }

    @Test
    public void test_getByUsername() {
        SysUser user = sysUserSerivce.getByUsername("testuser");
        try {
            System.out.println(new ObjectMapper().writeValueAsString(user));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_selectByPrimarykey() {
        SysUser user = sysUserSerivce.getByUsername("testuser");
        SysUser user1 = sysUserSerivce.selectByPrimaryKey(user.getId());
        try {
            System.out.println(new ObjectMapper().writeValueAsString(user1));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_update() {
        SysUser sysUser = sysUserSerivce.getByUsername("root");
        SysUser user = sysUserSerivce.selectByPrimaryKey(sysUser.getId());
//        user.setUsername("testuser1");
        user.setPassword(new BCryptPasswordEncoder().encode("111111"));
        user.setEnabled(false);
        user.setAccountNonExpired(false);
        user.setAccountNonLocked(false);
        user.setCredentialsNonExpired(false);
        int update = sysUserSerivce.update(user);
        System.out.println(update);
    }

    @Test
    public void test_getCount() {
        SysUserQry qry = new SysUserQry();
        qry.setUsername("testuser1");
        int count = sysUserSerivce.getCount(qry);
        System.out.println(count);
    }

    @Test
    public void test_getListBy() {
        SysUserQry qry = new SysUserQry();
        qry.setUsername("testuser1");
        qry.setPageNum(0);
        qry.setPageSize(10);
        List<SysUser> list = sysUserSerivce.getListBy(qry);
        try {
            System.out.println(new ObjectMapper().writeValueAsString(list));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_deleteByPrimaryKey() {
        SysUser user = new SysUser();
        user.setUsername("testuser1");
        user.setPassword(new BCryptPasswordEncoder().encode("123456"));
        user.setEnabled(true);
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);
        user.setCreateDate(new Date());
        sysUserSerivce.save(user);
        newUserId = user.getId();
        System.out.println(newUserId);
        user = sysUserSerivce.getByUsername("testuser1");
        int result = sysUserSerivce.deleteByPrimaryKey(user.getId());
        System.out.println(result);
    }

}
