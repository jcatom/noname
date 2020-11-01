package cc.jml1024.subdragon.mapper;

import java.util.List;

public interface BaseMapper<T, BaseQry> {
    T selectByPrimaryKey(Long id);

    int save(T entity);

    int saveSelective(T entity);

    int update(T entity);

    int updateSelective(T entity);

    int deleteByPrimaryKey(Long id);

    List<T> getListBy(BaseQry qry);

    int getCount(BaseQry qry);

}
