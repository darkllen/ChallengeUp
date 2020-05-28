
import javax.jws.soap.SOAPBinding;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

       String id3 = "-M8RycNDfRhYBCjcOF9a";
       User user = User.getUserById(id3);

       ArrayList<User> users = user.getSubscribersAsUsers();

       System.out.println("s");
    }
}
