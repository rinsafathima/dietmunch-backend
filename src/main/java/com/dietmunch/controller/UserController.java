package com.dietmunch.controller;

import com.dietmunch.dto.AuthUserDTO;
import com.dietmunch.entity.Users;
import com.dietmunch.repo.UserRpo;
import com.dietmunch.utill.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("api/v1/user")
public class UserController {

    @Autowired
    private UserRpo userRepo;

    @PostMapping("/register-user")
    public ResponseEntity<StandardResponse> getUserInformation(@RequestBody Users user){
        try {
            List<Users> prevUser = userRepo.findAllByEmail(user.getEmail());

            if(prevUser.isEmpty()) {
                user.setCreateDt(String.valueOf(new Date(System.currentTimeMillis())));
                userRepo.save(user);

                if(user.getId() > 0) {
                    //return user.getName();
                    return new ResponseEntity<>(
                            new StandardResponse(200, "Registration successful", user.getName()),
                            HttpStatus.OK);
                } else {
                    return null;
                }
            } else {
                //return "User is already registered";
                return new ResponseEntity<>(
                        new StandardResponse(404, "User is already registered", null),
                        HttpStatus.BAD_REQUEST);
            }
        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("/login")
    public ResponseEntity<StandardResponse> authorizedUser(@RequestParam(value = "email") String username,
                                                           @RequestParam(value = "pws") String password){
        try {
            if (username == null || password == null) {
                return new ResponseEntity<>(
                        new StandardResponse(401, "Login failed: username or password is empty", null),
                        HttpStatus.BAD_REQUEST);
            }

            List<Users> users = userRepo.findAllByEmail(username);

            if (users.isEmpty()) {
                return new ResponseEntity<>(
                        new StandardResponse(404, "Login failed: User not found", null),
                        HttpStatus.NOT_FOUND);
            }

            Users user = users.get(0);

            if (!user.getPwd().equals(password)) {
                return new ResponseEntity<>(
                        new StandardResponse(401, "Login failed: Incorrect password", null),
                        HttpStatus.UNAUTHORIZED);
            }

            // User authenticated successfully, construct and return response
            AuthUserDTO authUserDTO = new AuthUserDTO();
            authUserDTO.setId(user.getId());
            authUserDTO.setAddress(user.getAddress());
            authUserDTO.setName(user.getName());
            authUserDTO.setEmail(user.getEmail());
            authUserDTO.setRole(user.getRole());
            authUserDTO.setMobileNumber(user.getMobileNumber());
            authUserDTO.setAge(user.getAge());


            return new ResponseEntity<>(
                    new StandardResponse(200, "Login successful", authUserDTO),
                    HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();

            return new ResponseEntity<>(
                    new StandardResponse(500, "Internal Server Error", null),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
        }
        @GetMapping("/get-all-users")
        public ResponseEntity<StandardResponse> getAllUsers(){

        List<Users> userList  = userRepo.findAll();
        List <AuthUserDTO> authUserList = new ArrayList<>();


        for(int i=0; i <userList.size(); i++){
            AuthUserDTO authUser = new AuthUserDTO();
            authUser.setName(userList.get(i).getName());
            authUser.setAddress(userList.get(i).getAddress());
            authUser.setRole(userList.get(i).getRole());
            authUser.setId(userList.get(i).getId());
            authUser.setEmail(userList.get(i).getEmail());
            authUser.setMobileNumber(userList.get(i).getMobileNumber());
            authUser.setAge(userList.get(i).getAge());

            authUserList.add(authUser);

        }

            return new ResponseEntity<>(
                    new StandardResponse(200, "All users", authUserList),
                    HttpStatus.OK);
//            return authUserList;
        }
}
