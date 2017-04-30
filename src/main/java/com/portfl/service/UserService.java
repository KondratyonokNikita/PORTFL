package com.portfl.service;

import com.portfl.model.*;
import com.portfl.repository.TokenRepository;
import com.portfl.repository.UserRepository;
import com.portfl.utils.DateUtils;
import org.apache.commons.collections.IteratorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TokenRepository tokenRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public User findOne(Long userId) {
        return this.userRepository.findOne(userId);
    }

    public Iterable<User> findAll() {
        return this.userRepository.findAll();
    }

    public List<User> findAllInList() {
        return this.userRepository.findAll();
    }

    @Transactional
    public void create(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(UserRole.ROLE_USER);
        user.setPhotos(new ArrayList<>());
        this.userRepository.save(user);
    }

    @Transactional
    public void update(User user) {
        User entity = this.userRepository.findOne(user.getId());

        if (Objects.nonNull(entity)) {
            entity.setUsername(user.getUsername());
            entity.setEmail(user.getEmail());
            entity.setRole(user.getRole());

            if (Objects.nonNull(user.getPassword())) {
                entity.setPassword(user.getPassword());
            }

            this.userRepository.save(entity);
        }
    }

    @Transactional
    public void delete(Long id) {
        this.userRepository.delete(id);
    }

    public boolean isAccountEnabled(String username) {
        return findByUsername(username).isEnabled();
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Transactional
    public void createVerificationToken(String token, User user) {
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setUserId(user.getId());
        verificationToken.setToken(token);
        tokenRepository.save(verificationToken);
    }

    @Transactional
    public boolean enableAccount(String token) {
        try {
            VerificationToken verificationToken = tokenRepository.findByToken(token);
            User user = userRepository.findOne(verificationToken.getUserId());
            user.setEnabled(true);
            userRepository.save(user);
            return true;
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean isExistUsername(String username) {
        if (userRepository.findByUsername(username) != null)
            return true;
        return false;
    }

    public boolean isExistEmail(String email) {
        if (userRepository.findByEmail(email) != null)
            return true;
        return false;
    }

    public User getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (Objects.nonNull(authentication)) {
            Object principal = authentication.getPrincipal();

            if (principal instanceof UserDetails) {
                return findByUsername(((UserDetails) principal).getUsername());
            }
            return null;
        }
        return null;
    }

    public void makeAdmin(Long profileId) {
        User user = userRepository.findOne(profileId);
        user.setRole(UserRole.ROLE_ADMIN);
        userRepository.save(user);
    }

    public void makeUser(Long profileId) {
        User user = userRepository.findOne(profileId);
        user.setRole(UserRole.ROLE_USER);
        userRepository.save(user);
    }

    private void findByParamFirstName(String firstName, List<User> userList) {
        Iterator<User> userIterator = userList.iterator();
        while (userIterator.hasNext()) {
            if (!userIterator.next().getFirstName().equals(firstName) && firstName != "") {
                userIterator.remove();
            }
        }
    }

    private void findByParamLastName(String lastName, List<User> userList) {
        Iterator<User> userIterator = userList.iterator();
        while (userIterator.hasNext()) {
            if (!userIterator.next().getLastName().equals(lastName) && lastName != "") {
                userIterator.remove();
            }
        }
    }

    private void findByParamWeight(Integer weightMin, Integer weightMax, List<User> userList) {
        Iterator<User> userIterator = userList.iterator();
        while (userIterator.hasNext()) {
            User user = userIterator.next();
            if (weightMin != null && user.getWeight() < weightMin) {
                userIterator.remove();
            }
            else {
                if (weightMax != null && user.getWeight() > weightMax) {
                    userIterator.remove();
                }
            }
        }
    }

    private void findByParamHeight(Integer heightMin, Integer heightMax, List<User> userList) {
        Iterator<User> userIterator = userList.iterator();
        while (userIterator.hasNext()) {
            User user = userIterator.next();
            if (heightMin != null && user.getHeight() < heightMin) {
                userIterator.remove();
            }
            else {
                if (heightMax != null && user.getHeight() > heightMax) {
                    userIterator.remove();
                }
            }
        }
    }

    private void findByParamBirthyear(Integer birthyearMin, Integer birthyearMax, List<User> userList) {
        Iterator<User> userIterator = userList.iterator();
        while (userIterator.hasNext()) {
            User user = userIterator.next();
            if (birthyearMin != null && user.getBirthday() < birthyearMin) {
                userIterator.remove();
            }
            else {
                if (birthyearMax != null && user.getBirthday() > birthyearMax) {
                    userIterator.remove();
                }
            }
        }
    }

    private void findByParamGender(Gender gender, List<User> userList) {
        Iterator<User> userIterator = userList.iterator();
        while (userIterator.hasNext()) {
            if (!userIterator.next().getGender().equals(gender) && gender != null) {
                userIterator.remove();
            }
        }
    }

    public List<User> getUsersByParam(SearchForm searchForm) {
        List<User> userList = findAllInList();
        findByParamFirstName(searchForm.getFirstName(), userList);
        findByParamLastName(searchForm.getLastName(), userList);
        findByParamWeight(searchForm.getWeightMin(), searchForm.getWeightMax(), userList);
        findByParamHeight(searchForm.getHeightMin(), searchForm.getHeightMax(), userList);
        findByParamBirthyear(searchForm.getBirthyearMin(), searchForm.getBirthyearMax(), userList);
        findByParamGender(searchForm.getGender(), userList);
        return userList;
    }

//    public List<User> getUsersByParam(SearchForm searchForm){
//        List<User> userList = findAllInList();
//        Iterator<User> userIterator=userList.iterator();
//        while (userIterator.hasNext()){
//            User user = userIterator.next();
//            if(searchForm.getFirstName()!=""){
//                if(!user.getFirstName().equals(searchForm.getFirstName())){
//                    userIterator.remove();
//                }
//            }
//
//            if(searchForm.getLastName()!=""){
//                if(!user.getLastName().equals(searchForm.getLastName())){
//                    userIterator.remove();
//                }
//            }
//
//            if(searchForm.getWeightMin()!=null){
//                if(user.getWeight()<searchForm.getWeightMin()){
//                    userIterator.remove();
//                }
//            }
//
//            if(searchForm.getWeightMax()!=null){
//                if(user.getWeight()>searchForm.getWeightMax()){
//                    userIterator.remove();
//                }
//            }
//
//            if(searchForm.getHeightMin()!=null){
//                if(user.getHeight()<searchForm.getHeightMin()){
//                    userIterator.remove();
//                }
//            }
//
//            if(searchForm.getHeightMax()!=null){
//                if(user.getHeight()>searchForm.getHeightMax()){
//                    userIterator.remove();
//                }
//            }
//
//            if(searchForm.getBirthyearMin()!=null){
//                if(user.getBirthday()<searchForm.getBirthyearMin()){
//                    userIterator.remove();
//                }
//            }
//
//            if(searchForm.getBirthyearMax()!=null){
//                if(user.getBirthday()>searchForm.getBirthyearMax()){
//                    userIterator.remove();
//                }
//            }
//        }
//        return userList;
//    }
}
