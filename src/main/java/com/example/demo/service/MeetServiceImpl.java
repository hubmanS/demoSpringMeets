package com.example.demo.service;

import com.example.demo.Repositories.MeetRepository;
import com.example.demo.Repositories.UserRepository;
import com.example.demo.model.Meet;
import com.example.demo.model.Rol;
import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

@Service
public class MeetServiceImpl {
    @Autowired
    MeetRepository meetRepository;

    @Autowired
    UserRepository userRepository;
    static Logger log = Logger.getLogger(MeetServiceImpl.class.getName());

    public Meet createMeet(Long idUser, Meet meet) {
        Meet currentMeet = meetRepository.findByName(meet.getName());
        User user = userRepository.findById(idUser);
        user.setRol(Rol.OWNER);

        if (currentMeet == null) {
            Set<User> users =new HashSet<>();
            log.info("MASMSS"+ user.getRol());
            users.add(user);
            meet.setUsers(users);
            return meetRepository.save(meet);
        } else {
            Set<User> users = meet.getUsers();
            List<User> list = new ArrayList<>(users);
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i) == user) {
                    list.set(i, user);
                }
            }
            users = new HashSet<>(list);
            currentMeet.setName(meet.getName());
            currentMeet.setUsers(users);
            currentMeet.setState(meet.getState());
            return meetRepository.save(currentMeet);
        }
    }

    public Meet addUserToMeet(Long idUser, Long idMeet, Long idOwner) {


        Set<User> users = meetRepository.findById(idMeet).getUsers();
        boolean let = false;

        List<User> list = new ArrayList<>(users);
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getRol() == Rol.OWNER && idOwner == list.get(i).getId()) {
                let = true;
            }
        }
        log.info("ENLA"+ let);
        Meet meetCurrent = meetRepository.findById(idMeet);
        if (let) {
            users.add(userRepository.findById(idUser));
            meetCurrent.setUsers(users);
        }
        return meetCurrent;
    }


    public List<Meet> getMeets() {
        return meetRepository.findAll();
    }

}
