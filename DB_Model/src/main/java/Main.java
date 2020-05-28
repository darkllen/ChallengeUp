import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<String> a =new ArrayList<>();
        a.add("s");
        a.add("x");

        ArrayList<String> b =new ArrayList<>();
       // b.add("q");
        //b.add("t");
     //  System.out.println(Challenge.addNewChallenge("aa","sx", "-adsdas", a, b));
        ArrayList<User> users = User.getAllUsers();
        System.out.println(users.size());
    }
}
