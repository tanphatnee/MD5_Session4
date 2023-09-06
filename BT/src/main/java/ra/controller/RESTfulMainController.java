package ra.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ra.model.entity.User;
import ra.model.service.IUserService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
public class RESTfulMainController {
    @Autowired
    private IUserService userService;
    @GetMapping
    public ResponseEntity<List<User>> getAll(){
        List<User> list = userService.findAll();
        if(list.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(list,HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable String id){
        User user = userService.findById(id);
        if(user==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user,HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<User> delete(@PathVariable String id){
        User user = userService.findById(id);
        if(user==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        userService.delete(Long.valueOf(id));
        return new ResponseEntity<>(user,HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<User> doAdd(@RequestBody User user){
        user.setId(null);
        User userAdd= userService.save(user);
        return new ResponseEntity<>(userAdd,HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<User> doUpdate(@RequestBody User user,@PathVariable String id){
        if(userService.findById(id)==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        user.setId(Long.valueOf(id));
        User userAdd= userService.save(user);
        return new ResponseEntity<>(userAdd,HttpStatus.CREATED);
    }

}
