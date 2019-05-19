package com.example.demo.controller;

import com.example.demo.model.Meet;
import com.example.demo.model.User;
import com.example.demo.service.MeetServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping({"/meet"})
public class MeetController {
    @Autowired
    private MeetServiceImpl meetService;

    @PostMapping("/createMeet")
    public ResponseEntity<Meet> createMeet(@RequestParam("user") Long idUser,@RequestBody Meet meet ) {
        return new ResponseEntity<Meet>(meetService.createMeet(idUser, meet), HttpStatus.OK);
    }

    @PostMapping("/joinMeet")
    public ResponseEntity<Meet> joinUserToMeet(@RequestParam("user") Long idUser, @RequestParam("meet") Long idMeet, @RequestParam("userOwner") Long idOwner) { //´primera forma de unirse al meet
        return new ResponseEntity<Meet>(meetService.addUserToMeet(idUser,idMeet, idOwner), HttpStatus.OK);
    }

    @GetMapping("/getMeets")
    public ResponseEntity<List<Meet>> getMeets() {
        return new ResponseEntity<>(meetService.getMeets(), HttpStatus.OK);
    }


}
