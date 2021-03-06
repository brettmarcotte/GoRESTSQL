package com.careerdevs.gorestsqlv3.Controllers;

import com.careerdevs.gorestsqlv3.Repos.UserRepository;
import com.careerdevs.gorestsqlv3.Models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/user")
public class UserController {

    /*
     * Make a GET route that queries one user by ID and saved their data to your local database, while also returning the user data in the response
     * Make a POST route that create a user on JUST your local database
     * Make a Put route that updates a user on JUST your local database
     *
     */

    @Autowired
    private UserRepository userRepository;

    @GetMapping ("/upload/{id}")
    public ResponseEntity<String> uploadUserById (
            @PathVariable ("id") String userId,
            RestTemplate restTemplate
    ) {
        try {
            int uID = Integer.parseInt(userId);
            //check the range

            String url = "https://gorest.co.in/public/v2/users/" + uID;

            User foundUser = restTemplate.getForObject(url, User.class);

            System.out.println(foundUser);

            return new ResponseEntity<>("temp", HttpStatus.OK);

        } catch (NumberFormatException e) {

            return new ResponseEntity<>("ID must be a number", HttpStatus.NOT_FOUND);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(e.getClass());

            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    //https:localhost://8080/user/all
    @GetMapping("/all")
    public ResponseEntity<?> getAllUser () {
        try {
            Iterable<User> allUsers = userRepository.findAll();
            return new ResponseEntity<>(allUsers, HttpStatus.OK);

        } catch ( Exception e) {
            System.out.println(e.getMessage());
            System.out.println(e.getClass());

            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}

