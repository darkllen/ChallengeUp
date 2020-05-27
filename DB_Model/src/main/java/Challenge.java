import okhttp3.*;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

public class Challenge {
    private String name;
    private String task;
    private String creator_id;
    private ArrayList<String> tags;
    private ArrayList<String> categories;

    public Challenge(String name, String task, String creator_id) {
        this.name = name;
        this.task = task;
        this.creator_id = creator_id;
    }

    public Challenge(String name, String task, String creator_id, ArrayList<String> tags, ArrayList<String> categories) {
        this.name = name;
        this.task = task;
        this.creator_id = creator_id;
        this.tags = tags;
        this.categories = categories;
    }

    public static String addNewChallenge(Challenge challenge){
        return addNewChallenge(challenge.name, challenge.task, challenge.creator_id, challenge.tags, challenge.categories);
    }
    public static String addNewChallenge(String name, String task, String creator_id, ArrayList<String> tags, ArrayList<String> categories){
        OkHttpClient client = new OkHttpClient();
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        try {
            JSONObject jsonObject = new JSONObject()
                    .put("name", name)
                    .put("task", task)
                    .put("creator_id",creator_id)
                    .put("tags", tags)
                    .put("categories", categories);


            RequestBody body = RequestBody.create(jsonObject.toString(), JSON);

            Request request = new Request.Builder()
                    .url("https://us-central1-challengeup-49057.cloudfunctions.net/add_challenge")
                    .post(body)
                    .build();

            Response response = client.newCall(request).execute();
            String resStr = response.body().string();
            JSONObject object = new JSONObject(resStr);

            return object.getString("id");
        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}
