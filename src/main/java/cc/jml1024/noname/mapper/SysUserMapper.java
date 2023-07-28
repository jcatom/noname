package cc.jml1024.noname.mapper;

import cc.jml1024.noname.entity.SysUser;
import cc.jml1024.noname.qry.SysUserQry;
import org.springframework.stereotype.Repository;

/**
 * @author Evil
 */
@Repository
public interface SysUserMapper extends BaseMapper<SysUser, SysUserQry>{

    /**
     * 根据用户名查询用信息
     *
     * @param username
     * @return SysUser
     */
    SysUser getByUsername(String username);

}
