package cc.jml1024.noname.mapper;

import java.util.List;

/**
 * @author Evil
 */
public interface BaseMapper<T, BaseQry> {
    /**
     * 根据主键查询记录
     *
     * @param id
     * @return T
     */
    T selectByPrimaryKey(Long id);

    /**
     * 新增对应实体类信息
     *
     * @param entity
     * @return int
     */
    int save(T entity);

    /**
     * 新增对应实体类信息，只保存不为空的字段
     *
     * @param entity
     * @return int
     */
    int saveSelective(T entity);

    /**
     * 更新对应实体类信息
     *
     * @param entity
     * @return int
     */
    int update(T entity);

    /**
     * 更新对应实体类信息,只更新不为空的字段
     *
     * @param entity
     * @return int
     */
    int updateSelective(T entity);

    /**
     * 根据主键删除对应记录
     *
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 根据给定条件查询相关记录
     *
     * @param qry
     * @return List
     */
    List<T> getListBy(BaseQry qry);

    /**
     * 根据给定条件查询相关记录总数
     *
     * @param qry
     * @return int
     */
    int getCount(BaseQry qry);

}
