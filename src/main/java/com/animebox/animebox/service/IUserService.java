package com.animebox.animebox.service;

import com.animebox.animebox.bean.user.User;
import com.animebox.animebox.bean.user.UserInfo;


public interface IUserService {
    public User userValid(String strUserTelephoneOrEmail, String strUserPassword);

    public UserInfo getUserInfo(int intUserId);

    public Boolean updateUserInfo(UserInfo userInfo,UserInfo oldUserInf);

    public Boolean newUser(String strUserAlias,String strUserTelephoneOrEmail,String strUserPassword );

    public Boolean telephoneOrEmailValid(String strUserTelephoneOrEmail);
}
