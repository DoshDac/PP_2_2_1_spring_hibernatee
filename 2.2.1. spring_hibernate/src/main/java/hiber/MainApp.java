
package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context =
              new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      User user1 = new User("Алишка", "Мурадов", "user1@mail.ru");
      User user2 = new User("Турпал", "Ахметов", "user2@mail.ru");
      User user3 = new User("турпал", "Lastname3", "user3@mail.ru");
      User user4 = new User("User4", "Lastname4", "user4@mail.ru");

      Car car1 = new Car("королла", 2012);
      Car car2 = new Car("шкода", 2017);
      Car car3 = new Car("мазератти", 2023);
      Car car4 = new Car("тесла", 7);

      user1.setCar(car1);
      user2.setCar(car2);
      user3.setCar(car3);
      user4.setCar(car4);

      userService.add(user1);
      userService.add(user2);
      userService.add(user3);
      userService.add(user4);

      List<User> users = userService.listUsers();

      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println("Car = " + user.getCar().getSeries() + " " + user.getCar().getModel());
         System.out.println();
      }

      User user = userService.getUserByCar("королла", 2012);
      System.out.println(user);

      context.close();
   }
}
