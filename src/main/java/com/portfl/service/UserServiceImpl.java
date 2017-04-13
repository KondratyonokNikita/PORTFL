package com.portfl.service;

import com.portfl.dao.GenderDao;
import com.portfl.dao.RoleDao;
import com.portfl.dao.TokenDao;
import com.portfl.dao.UserDao;
import com.portfl.model.Gender;
import com.portfl.model.Role;
import com.portfl.model.User;
import com.portfl.model.VerificationToken;
import com.portfl.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private GenderDao genderDao;

    @Autowired
    private TokenDao tokenDao;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        Set<Role> roles = new HashSet<>();
        roles.add(roleDao.getOne(1L));
        Set<Gender> genders = new HashSet<>();
        genders.add(genderDao.findOne(1L));
        user.setRoles(roles);
        userDao.save(user);
    }

    @Override
    public boolean isAccountEnabled(String username) {
        return findByUsername(username).isEnabled();
    }

    @Override
    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    @Override
    public void createVerificationToken(String token, User user) {
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setUser(user);
        verificationToken.setToken(token);
        verificationToken.setDateExpired(DateUtils.getNextDayDate());
        tokenDao.save(verificationToken);
    }

    @Override
    public boolean enableAccount(String token) {
        try {
            VerificationToken verificationToken = tokenDao.findByToken(token);
            User user = verificationToken.getUser();
            user.setEnabled(true);
            userDao.save(user);
            return true;
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean isExistUsername(String username) {
        if (userDao.findByUsername(username) != null)
            return true;
        return false;
    }

    @Override
    public boolean isExistEmail(String email) {
        if (userDao.findByEmail(email) != null)
            return true;
        return false;
    }
}
