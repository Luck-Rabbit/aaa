package com.animebox.animebox.service.imp;

import com.animebox.animebox.bean.user.User;
import com.animebox.animebox.bean.user.UserInfo;
import com.animebox.animebox.dao.IUserInfoDao;
import com.animebox.animebox.dao.IUserLoginDao;
import com.animebox.animebox.service.IUserService;
import com.animebox.animebox.utils.SendMessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import static com.animebox.animebox.utils.SendMessageUtil.getRandomCode;


@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    IUserLoginDao iUserLoginDao;

    @Autowired
    IUserInfoDao iUserInfoDao;

    /**
     * @param strUserTelephoneOrEmail
     * @param strUserPassword
     * @return com.animebox.animebox.bean.user.User
     * @Author LuckyRabbit
     * @Description //TODO 判断用户输入的是字符串是电话还是邮箱
     * @Date 15:32 2019/10/17
     **/
    @Override
    public User userValid(String strUserTelephoneOrEmail, String strUserPassword) {
        List<User> users;
        if (Pattern.compile("^[-\\+]?[\\d]*$").matcher(strUserTelephoneOrEmail).matches()) {
            if (strUserTelephoneOrEmail.length() != 11) {
                return null;
            }
            users = iUserLoginDao.selectUserLoginTelephoneValid(strUserTelephoneOrEmail, strUserPassword);
        } else {
            if (strUserTelephoneOrEmail.contains("@") && strUserTelephoneOrEmail.endsWith(".com")) {
                users = iUserLoginDao.selectUserLoginEmailValid(strUserTelephoneOrEmail, strUserPassword);
            }else {
                return null;
            }
        }
        if (users.size() != 1) {
            return null;
        }
        return users.get(0);
    }

    /**
     * @param intUserId
     * @return com.animebox.animebox.bean.user.UserInfo
     * @Author LuckyRabbit
     * @Description //TODO
     * @Date 19:50 2019/10/17
     **/
    @Override
//    @Cacheable(cacheNames = "userInfo")
    public UserInfo getUserInfo(int intUserId) {
        UserInfo userInfo = iUserInfoDao.selectUserInfoById(intUserId);
        return userInfo;
    }

    /**
     * @param newUserInfo 新数据
     * @param oldUserInfo 旧数据
     * @return boolean
     * @Author LuckyRabbit
     * @Description //TODO 对比新旧信息，ID不同直接不进行更新，再对比基础项目和拓展项目
     * @Date 16:11 2019/10/17
     **/
    @Override
    public Boolean updateUserInfo(UserInfo newUserInfo, UserInfo oldUserInfo) {
        Boolean blnUpdateFlag = false;
        if (newUserInfo.getIntUserId().equals(oldUserInfo.getIntUserId())) {
            if (newUserInfo.getStrUserAlias().equals(oldUserInfo.getStrUserAlias()) &&
                    newUserInfo.getStrUserHeadPortrait().equals(oldUserInfo.getStrUserHeadPortrait()) &&
                    newUserInfo.getIntUserSex() == oldUserInfo.getIntUserSex() &&
                    newUserInfo.getUserBirth().equals(oldUserInfo.getUserBirth()) &&
                    newUserInfo.getStrUserTag().equals(oldUserInfo.getStrUserTag())) {
                blnUpdateFlag = true;
            } else {
                iUserInfoDao.updateUserBasicInfo(newUserInfo);
            }

            if (newUserInfo.getStrUserIntro().equals(oldUserInfo.getStrUserIntro())) {
                blnUpdateFlag = true;
            } else {
                iUserInfoDao.updateUserExInfo(newUserInfo);
            }
        }
        return blnUpdateFlag;
    }

    /**
     * @param strUserAlias
     * @param strUserTelephoneOrEmail
     * @param strUserPassword
     * @return boolean
     * @Author LuckyRabbit
     * @Description //TODO 需改进 如果在插入user数据成功后报错 没插入userinfo 需aop进行事物增强
     * @Date 20:34 2019/10/24
     **/
    @Override
    public Boolean newUser(String strUserAlias, String strUserTelephoneOrEmail, String strUserPassword) {
        strUserPassword = DigestUtils.md5DigestAsHex(strUserPassword.getBytes());
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String gmt_create = dateFormat.format(new Date());
        String gmt_modified = dateFormat.format(new Date());
        String strPhonePattern = "^\\d{11}$";
        boolean isMatchPhone = Pattern.matches(strPhonePattern, strUserTelephoneOrEmail);
        int intNewUserId = 0;
        User user = new User();
        user.setStrUserPassword(strUserPassword);
        if (isMatchPhone) {
            user.setStrUserTelephone(strUserTelephoneOrEmail);
            iUserLoginDao.insertNewUserByPhone(user, gmt_create, gmt_modified);
        }
        if (user.getIntUserId() == null) {
            String strEmailPattern = "^[A-Za-z0-9\\u4e00-\\u9fa5]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$";
            boolean isMatchEmail = Pattern.matches(strEmailPattern, strUserTelephoneOrEmail);
            if (isMatchEmail) {
                user.setStrUserEmail(strUserTelephoneOrEmail);
                iUserLoginDao.insertNewUserByEmail(user, gmt_create, gmt_modified);
            }
        }
        if (user.getIntUserId() != null) {
            Integer intNewUserInfo = iUserInfoDao.insertNewUserInfoOnlyUserAlias(user.getIntUserId(), strUserAlias, gmt_create, gmt_modified);
            System.out.println(intNewUserId);
            if (intNewUserInfo == 1) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Boolean telephoneOrEmailValid(String strUserTelephoneOrEmail) {
        String strPhonePattern = "^\\d{11}$";
        boolean isMatchPhone = Pattern.matches(strPhonePattern, strUserTelephoneOrEmail);
        if (isMatchPhone) {
            List<User> users =iUserLoginDao.selectTelephoneValid(strUserTelephoneOrEmail);
            if(users.size()>0){
                return false;
            }
        }
        String strEmailPattern = "^[A-Za-z0-9\\u4e00-\\u9fa5]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$";
        boolean isMatchEmail = Pattern.matches(strEmailPattern, strUserTelephoneOrEmail);
        if (isMatchEmail) {
            List<User> users = iUserLoginDao.selectEmailValid(strUserTelephoneOrEmail);
            if(users.size()>0){
                return false;
            }
        }
        return true;
    }
}
