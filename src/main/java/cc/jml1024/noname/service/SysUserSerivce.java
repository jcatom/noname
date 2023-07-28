package cc.jml1024.noname.service;

import cc.jml1024.noname.entity.SysUser;
import cc.jml1024.noname.mapper.SysUserMapper;
import cc.jml1024.noname.qry.SysUserQry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Evil
 */
@Service
public class SysUserSerivce {

    @Autowired
    private SysUserMapper sysUserMapper;

    public SysUser selectByPrimaryKey(Long id) {
        return sysUserMapper.selectByPrimaryKey(id);
    }

    public int save(SysUser sysUser) {
        return sysUserMapper.save(sysUser);
    }

    public int saveSelective(SysUser sysUser) {
        return sysUserMapper.saveSelective(sysUser);
    }

    public int update(SysUser sysUser) {
        return sysUserMapper.update(sysUser);
    }

    public int deleteByPrimaryKey(Long id) {
        return sysUserMapper.deleteByPrimaryKey(id);
    }

    public SysUser getByUsername(String username) {
        return sysUserMapper.getByUsername(username);
    }

    public int getCount(SysUserQry qry) {
        return sysUserMapper.getCount(qry);
    }

    public List<SysUser> getListBy(SysUserQry qry) {
        return sysUserMapper.getListBy(qry);
    }

}
