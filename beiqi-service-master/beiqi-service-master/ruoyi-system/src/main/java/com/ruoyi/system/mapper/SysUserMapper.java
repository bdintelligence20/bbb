package com.ruoyi.system.mapper;

import java.util.List;

import com.ruoyi.system.domain.SysAuthUser;
import org.apache.ibatis.annotations.Param;
import com.ruoyi.common.core.domain.entity.SysUser;

/**
 * 用户表 数据层
 *
 * @author ruoyi
 */
public interface SysUserMapper
{
    /**
     * 根据条件分页查询用户列表
     *
     * @param sysUser 用户信息
     * @return 用户信息集合信息
     */
    public List<SysUser> selectUserList(SysUser sysUser);

    /**
     * 根据条件分页查询已配用户角色列表
     *
     * @param user 用户信息
     * @return 用户信息集合信息
     */
    public List<SysUser> selectAllocatedList(SysUser user);

    /**
     * 根据条件分页查询未分配用户角色列表
     *
     * @param user 用户信息
     * @return 用户信息集合信息
     */
    public List<SysUser> selectUnallocatedList(SysUser user);

    /**
     * 通过用户名查询用户
     *
     * @param userName 用户名
     * @return 用户对象信息
     */
    public SysUser selectUserByUserName(String userName);

    public SysUser selectUserByUserNameByEmail(@Param("userName") String userName, @Param("email") String email);
    /**
     * 通过用户ID查询用户
     *
     * @param userId 用户ID
     * @return 用户对象信息
     */
    public SysUser selectUserById(Long userId);

    /**
     * 新增用户信息
     *
     * @param user 用户信息
     * @return 结果
     */
    public int insertUser(SysUser user);

    /**
     * 修改用户信息
     *
     * @param user 用户信息
     * @return 结果
     */
    public int updateUser(SysUser user);

    /**
     * 修改用户头像
     *
     * @param userName 用户名
     * @param avatar 头像地址
     * @return 结果
     */
    public int updateUserAvatar(@Param("userName") String userName, @Param("avatar") String avatar);

    public int updateUserPwd(@Param("userName") String userName,@Param("email") String email, @Param("password") String password);

    /**
     * 重置用户密码
     *
     * @param userName 用户名
     * @param password 密码
     * @return 结果
     */
    public int resetUserPwd(@Param("email")String email,@Param("userName") String userName, @Param("password") String password);


    public int updateUserPassword(@Param("userName") String userName, @Param("password") String password);

    /**
     * 通过用户ID删除用户
     *
     * @param userId 用户ID
     * @return 结果
     */
    public int deleteUserById(Long userId);

    /**
     * 批量删除用户信息
     *
     * @param userIds 需要删除的用户ID
     * @return 结果
     */
    public int deleteUserByIds(Long[] userIds);

    /**
     * 校验用户名称是否唯一
     *
     * @param userName 用户名称
     * @return 结果
     */
    public int checkUserNameUnique(String userName);

    /**
     * 校验手机号码是否唯一
     *
     * @param phonenumber 手机号码
     * @return 结果
     */
    public SysUser checkPhoneUnique(String phonenumber);

    /**
     * 校验email是否唯一
     *
     * @param email 用户邮箱
     * @return 结果
     */
    public SysUser checkEmailUnique(String email);


    /**
     * 根据uuid查询用户信息
     *
     * @param uuid 唯一信息
     * @return 结果
     */
    public SysUser selectAuthUserByUuid(String uuid);

    /**
     * 新增第三方授权信息
     *
     * @param authUser 用户信息
     * @return 结果
     */
    public int insertAuthUser(SysAuthUser authUser);

    /**
     * 根据用户编号查询授权列表
     *
     * @param userId 登录账户
     * @return 授权列表
     */
    public List<SysAuthUser> selectAuthUserListByUserId(Long userId);

    /**
     * 校验source平台是否绑定
     *
     * @param userId 用户编号
     * @param source 绑定平台
     * @return 结果
     */
    public int checkAuthUser(@Param("userId") Long userId, @Param("source") String source);

    /**
     * 根据编号删除第三方授权信息
     *
     * @param authId 授权编号
     * @return 结果
     */
    public int deleteAuthUser(Long authId);

    /**
     * 查找没有账户表的用户数据
     * @return
     */
    List<SysUser> selectNotAccountList();

    public SysUser selectUserByEmail(String email);

    public List<SysUser> selectUserByDelFlag();

}
