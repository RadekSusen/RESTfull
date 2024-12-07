package utb.fai.RESTAPIServer;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "", produces = {"application/json"}, consumes = {"application/json"})

public class UserController {

    @Autowired
    private UserRepository userRepository;

    // Get all users
    @GetMapping("/users")
    public List<MyUser> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/getUser")
    public ResponseEntity<MyUser> getUserById(@RequestParam Long id) {
        Optional<MyUser> user = userRepository.findById(id);
        if (user.isPresent()) {
            return new ResponseEntity<>(user.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Create a new user
    @PostMapping("/createUser")
    public ResponseEntity<MyUser> createUser(@RequestBody MyUser newUser) {
        if(userRepository.existsById(newUser.getId())  || !newUser.isUserDataValid()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        MyUser savedUser = userRepository.save(newUser);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    // Update an existing user
    @PutMapping("/editUser")
    public ResponseEntity<MyUser> updateUser(@RequestParam Long id, @RequestBody MyUser updatedUser) {
        try {
            Optional<MyUser> user = userRepository.findById(id);
            if (!user.isPresent()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            MyUser existingUser = user.get();
            updatedUser.setId(existingUser.getId());
            if (!updatedUser.isUserDataValid()) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            existingUser.setName(updatedUser.getName());
            existingUser.setEmail(updatedUser.getEmail());
            existingUser.setPhoneNumber(updatedUser.getPhoneNumber());
            MyUser savedUser = userRepository.save(existingUser);
            return new ResponseEntity<>(savedUser, HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // Delete a user by ID
    @DeleteMapping(value = "/deleteUser")
    public ResponseEntity<Void> deleteUser(@RequestParam Long id) {
        try {
            Optional<MyUser> user = userRepository.findById(id);
            userRepository.delete(user.get());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // Delete all users
    @DeleteMapping(value = "/deleteAll")
    public ResponseEntity<Void> deleteAllUsers() {
        try {
            userRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
