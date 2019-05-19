package com.example.demo.controller;

import com.example.demo.model.Friendship;
import com.example.demo.model.Meet;
import com.example.demo.model.User;
import com.example.demo.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
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

    @GetMapping(path = {"/"})
    public Meet getBook() {
        log.info("HELLO WORDL");
        return null;
    }

    @PostMapping("/edit")
    public ResponseEntity<User> editUser(@RequestBody User user) {
        log.info("EDIT");
        return new ResponseEntity<User>(userServiceImpl.createUser(user), HttpStatus.OK);
    }

    @PostMapping("/delete")
    public ResponseEntity<User> deleteUser(@RequestParam("email") String  email) {
        log.info("DELETE");
        return new ResponseEntity<User>(userServiceImpl.deleteUser(email), HttpStatus.OK);
    }

    @PostMapping("/addFriend")
    public ResponseEntity<Friendship> getUser(@RequestParam("owner") Long ownerId, @RequestParam("friend") Long friendId) {
        return new ResponseEntity<Friendship>(userServiceImpl.createLinkWithFriends(ownerId, friendId), HttpStatus.OK);
    }

    @GetMapping("/owner/{id}/friends")
    public ResponseEntity<Set<User>> getFriendsOf(@PathVariable("id") Long ownerId) {
        return new ResponseEntity<Set<User>>(userServiceImpl.getFriendsOf(ownerId), HttpStatus.OK);
    }

    @GetMapping("/getUsers")
    public ResponseEntity<List<User>> getUsers() {
        log.info("getUser");
        return new ResponseEntity<>(userServiceImpl.getUsers(), HttpStatus.OK);
    }
}
