package com.example.demo.controller;

import com.example.demo.model.Friendship;
import com.example.demo.model.User;
import com.example.demo.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping({"/user"})
public class UserController {

    @Autowired
    UserServiceImpl userServiceImpl;
    static Logger log = Logger.getLogger(UserController.class.getName());

    @PostMapping("/create")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        log.info("CREATE");
        return new ResponseEntity<User>(userServiceImpl.createUser(user), HttpStatus.OK);
    }


    @PostMapping("/addFriend")
    public ResponseEntity<User> getUser(@RequestParam("owner") Long ownerId, @RequestParam("friend") Long friendId) {
        return new ResponseEntity<User>(userServiceImpl.createLinkWithFriends(ownerId, friendId), HttpStatus.OK);
    }

    @GetMapping("/owner/{id}/friends")
    public ResponseEntity<List<User>> getFriendsOf(@PathVariable("id") Long ownerId) {
        return new ResponseEntity<List<User>>(userServiceImpl.getFriendsOf(ownerId), HttpStatus.OK);
    }

    @GetMapping("/getUsers")
    public ResponseEntity<List<User>> getUsers() {
        log.info("getUser");
        return new ResponseEntity<>(userServiceImpl.getUsers(), HttpStatus.OK);
    }

    @GetMapping("/showFriends/{id}")
    public ResponseEntity<List<User>> getUsers(@PathVariable("id")  int id) {
        log.info("showFriends"+id);
        return new ResponseEntity<>(userServiceImpl.getUsers(), HttpStatus.OK);
    }

}
