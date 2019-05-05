package com.example.demo.service;

import com.example.demo.Repositories.FriendsRepository;
import com.example.demo.model.Friendship;
import com.example.demo.model.User;
import com.example.demo.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl {
    @Autowired
    UserRepository userRepository;
    @Autowired
    public FriendsRepository friendsRepository;

    public User createUser(User user) {

        if (userRepository.findByEmail(user.getEmail()) == null) {
            return userRepository.save(user);
        }
        return null;
    }

    public User getUser(int index) {
        return null;
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Transactional
    public User createLinkWithFriends(Long ownerId, Long friendId) {
        User owner = userRepository.findById(ownerId);
        User friend = userRepository.findById(friendId);

        if(owner == null) throw new RuntimeException("Owner not found");
        if(friend == null) throw new RuntimeException("Friend not found");

        Friendship newFriendship = new Friendship();
        newFriendship.setOwner(owner);
        newFriendship.setFriend(friend);

        newFriendship = friendsRepository.save(newFriendship);
        owner = userRepository.findById(owner.getId());
        return owner;
    }


    public List<User> getFriendsOf(Long ownerId) {
        List<User> rtn = new ArrayList<>();
        User user = userRepository.findById(ownerId);
        if(user!=null) {
            for(Friendship fsh : user.getFriends()) {
                rtn.add(fsh.getFriend());
            }
            return rtn;
        }
        return null;
    }
}