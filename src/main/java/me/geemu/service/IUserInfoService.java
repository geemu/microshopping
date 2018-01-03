package me.geemu.service;

import me.geemu.persistence.model.primary.UserInfo;

/**
 * @author Geemu
 * Email: cfmmail@sina.com
 * Date: 2017/12/5 12:29
 * Description:
 */
public interface IUserInfoService {

    /**
     * 根据账号密码查找用户 返回token
     *
     * @param account
     * @param password
     * @return
     */
    String findByAccountAndPassword(String account, String password);
    
}
