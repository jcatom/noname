package cc.jml1024.subdragon.mapper;

import cc.jml1024.subdragon.entity.SysUser;
import cc.jml1024.subdragon.qry.SysUserQry;
import org.springframework.stereotype.Repository;

@Repository
public interface SysUserMapper extends BaseMapper<SysUser, SysUserQry>{

    SysUser getByUsername(String username);

}
