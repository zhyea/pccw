package org.chobit.cm.dao;

import org.apache.ibatis.annotations.*;
import org.chobit.cm.common.entity.User;

import java.util.List;

/**
 * @author robin
 */
@Mapper
public interface UserMapper {


    /**
     * 新增用户记录
     *
     * @param user 用户实例
     * @return 用户ID
     */
    @Insert({
            "insert into user (username, password, email, name)",
            "values (#{username}, #{password}, #{email}, #{name})"
    })
    @Options(useGeneratedKeys = true, keyProperty = "id")
    boolean insert(User user);


    /**
     * 批量新增
     * （估计是想使用BATCH模式）
     *
     * @param user 用户实例集合
     * @return 新增数量
     */
    @Insert({
            "<script>",
            "insert into user(username, password, email, name)",
            "values",
            "<foreach collection='users' item='item' separator=','>",
            "(#{item.username}, #{item.password}, #{item.email}, #{item.name})",
            "</foreach>",
            "</script>"
    })
    int batchInsert(@Param("users") List<User> user);

    /**
     * 是否更新成功
     *
     * @param user 用户实例
     * @return 是否更新成功
     */
    @Update({
            "update user set",
            "username=#{username}, password=#{password}, email=#{email}, name=#{name}",
            "where id=#{id}"
    })
    boolean update(User user);


    /**
     * 查询获取用户记录
     *
     * @param id 记录ID
     * @return 用户记录
     */
    @Select({"select * from user where id=#{id}"})
    User get(@Param("id") Long id);


    /**
     * 根据用户名查询获取用户记录
     *
     * @param username 用户名
     * @return 用户记录
     */
    User getByUsername(@Param("username") String username);


    /**
     * 删除用户记录
     *
     * @param id 记录ID
     * @return 是否删除成功
     */
    @Delete({"delete from user where id=#{id}"})
    boolean delete(@Param("id") Long id);
}
