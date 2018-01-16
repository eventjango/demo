package com.example.demo.controller;

import com.example.demo.domain.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@org.springframework.web.bind.annotation.RestController
public class StarpayRestController {


    @Autowired
    private UserService userService; //Service which will do all data retrieval/manipulation work


    //-------------------Retrieve All Users--------------------------------------------------------

    @GetMapping("/user")
    public ResponseEntity<List<User>> listAllUsers(){

        List<User> users = userService.findAllUsers();

        if(users.isEmpty()){
            return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
    }


    //-------------------Retrieve Single User--------------------------------------------------------

    @GetMapping(value = "/user/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<User> getUser(@PathVariable("id") long id){

        System.out.println("Fetching User with id " + id);

        User user = userService.findById(id);

        if(user == null){
            System.out.println("User with id " + id + " not found");
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<User>(user, HttpStatus.OK);
    }



    //-------------------Create a User--------------------------------------------------------

    @PostMapping("/user/")
    public ResponseEntity<Void> createUser(@RequestBody User user, UriComponentsBuilder uriComponentsBuilder){

        System.out.println("Creating User " + user.getName());

        if(userService.isUserExist(user)){

            System.out.println("A User with name " + user.getName() + " already exist");
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }

        userService.saveUser(user);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uriComponentsBuilder.path("/user/{id}").buildAndExpand(user.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }



    //------------------- Update a User --------------------------------------------------------

    @PutMapping("/user/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") long id, @RequestBody User user){

        System.out.println("Updating User " + id);

        User currentUser = userService.findById(id);

        if(currentUser == null){
            System.out.println("User with id "+ id + " not found");
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }

        currentUser.setName(user.getName());
        currentUser.setAge(user.getAge());
        currentUser.setSalary(user.getSalary());

        userService.updateUser(currentUser);
        return new ResponseEntity<User>(currentUser, HttpStatus.OK);
    }


    //------------------- Delete a User --------------------------------------------------------

    @DeleteMapping("/user/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable("id") long id){

        System.out.println("Fetching & Deleting User with id " + id);

        User user = userService.findById(id);

        if(user == null){
            System.out.println("Unable to delete. User with id " + id + " not found");
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }

        userService.deleteUserById(id);
        return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
    }

    //------------------- Delete All Users --------------------------------------------------------

    @DeleteMapping("/user/")
    public ResponseEntity<User> deleteAllUsers() {
        System.out.println("Deleting All Users");

        userService.deleteAllUsers();
        return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
    }

}
