
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        User user = User.getAllUsers().get(0);

        Challenge challenge1 = new Challenge("a", "f", user.getId());
        Challenge challenge2 = new Challenge("ab", "fa", user.getId());
        Challenge challenge3 = new Challenge("az", "ft", user.getId());

        Challenge.addNewChallenge(challenge1);
        Challenge.addNewChallenge(challenge2);
        Challenge.addNewChallenge(challenge3);


        ArrayList<Challenge> challenges = user.getAllCreatedChallenges();

        System.out.println(challenges);

    }
}
