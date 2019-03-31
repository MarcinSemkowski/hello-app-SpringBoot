package pl.demosdakurs.helloapp;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController

public class Hello {

    private List<User> users = new ArrayList<>();


    public Hello() {
        this.users = new ArrayList<>();
        User user = new User();
        user.setId(1);
        user.setName("Jan");
        user.setSurname("Kowalski");
        user.setSex(Sex.MALE);
        user.setAge(22);
        users.add(user);
        User user2 = new User();
        user2.setId(1);
        user2.setName("Anna");
        user2.setSurname("Nowak");
        user2.setSex(Sex.FEMALE);
        user2.setAge(18);
        users.add(user2);
    }


     public String sayHello(){
         return "Hello!";
     }

    @PostMapping("/api")
     public String sayHelloPost(@RequestParam String name,@RequestParam int age,@RequestParam String sex,@RequestParam String surname){
          User user =  new User();
          user.setName(name);
          user.setAge(age);
          user.setSurname(surname);
          if(sex.equalsIgnoreCase("Male".toLowerCase())){
              user.setSex(Sex.MALE);
          }
          if(sex.equalsIgnoreCase("Female".toLowerCase())){
             user.setSex(Sex.FEMALE);
          }

          users.add(user);
         System.out.println(user.getName());
          return "New  User " + user.getName() + "user-surname =" + user.getSurname() + "sex = " + user.getSex() + "age = " + user.getAge() ;
     }

    public List<User> getUsers() {
        return users;
    }

    @GetMapping("/api")
     public ResponseEntity<List<User>> getListOfUsers(){

         getUsers().stream().forEach(user->System.out.println(user));
         return new  ResponseEntity<>(users, HttpStatus.OK);

     }
    @DeleteMapping("/api")
    public boolean removeUser(@RequestParam long id) {

        Optional<User> first = users.stream()

                .filter(x -> id == x.getId()).findFirst();

        if (first.isPresent()) {

            return users.remove(first.get());

        }

        return false;

    }





}
