import java.lang.reflect.Array;
import java.util.ArrayList;

public class Validation {
    public static boolean validateName(String name){
        if (name == null) throw new IllegalArgumentException("name can`t be null");
        if (name.equals("")) throw new IllegalArgumentException("name can`t be empty");
        if (name.length()>=64) throw new IllegalArgumentException("name can`t be more than 63 symbols");
        return true;
    }
    public static boolean validateDescription(String description){
        if (description == null) throw new IllegalArgumentException("description can`t be null");;
        if (description.equals("")) throw new IllegalArgumentException("description can`t be empty");
        if (description.length()>=128) throw new IllegalArgumentException("description can`t be more than 127 symbols");
        return true;
    }

    public static boolean validateTask(String task){
        if (task == null) throw new IllegalArgumentException("task can`t be null");;
        if (task.equals("")) throw new IllegalArgumentException("task can`t be empty");
        return true;
    }

    public static boolean validateTags(ArrayList<String> tags){
        if (tags == null) throw new IllegalArgumentException("can`t be null");
        if (tags.size()>5) throw new IllegalArgumentException("size must be less than 6");
        return true;
    }

    public static boolean validateNickTagPassword(String nick){
        if (nick == null) throw new IllegalArgumentException("can`t be null");
        if (!nick.matches("[A-Za-z0-9_\\-]{5}[A-Za-z0-9_\\-]*")) throw new IllegalArgumentException("wrong input");
        return true;
    }
    public static boolean validateEmail(String email){
        if (email == null) throw new IllegalArgumentException("can`t be null");
        if (!email.matches("[A-Za-z0-9_\\-]+@[A-Za-z0-9]+[A-Za-z0-9.]*[A-Za-z0-9].[A-Za-z0-9]+")) throw new IllegalArgumentException("wrong input");
        return true;
    }

    public static void main(String[] args) {
       validateEmail("sa@");
    }
}
