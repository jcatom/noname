package cc.jml1024.subdragon.mapper;

import cc.jml1024.subdragon.entity.SysUser;
import cc.jml1024.subdragon.qry.SysUserQry;
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
