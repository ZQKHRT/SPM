package com.vrms.app.dao;

import com.vrms.app.entity.Administrators;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 管理员数据访问层接口
 */
public interface AdministratorsDao {
    List<Administrators> listPage(@Param("page") int page,
                                  @Param("size") int size,
                                  @Param("username") String username);
    int count(String username);

    int save(Administrators administrators);

    int update(Administrators administrators);

    int remove(Integer aid);

    int changeStats(Administrators administrators);

    Administrators login(String username);
}
