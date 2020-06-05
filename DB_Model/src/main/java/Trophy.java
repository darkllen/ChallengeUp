import okhttp3.*;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.stream.Collectors;

public class Trophy {
    private String id;
    private String name;
    private String description;

    public Trophy(String name, String description) throws IllegalArgumentException {
        Validation.validateName(name);
        Validation.validateDescription(description);
        this.name = name;
        this.description = description;
        id = null;
    }

    public static String addNewTrophy(Trophy trophy){
        OkHttpClient client = new OkHttpClient();
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        try {
            JSONObject jsonObject = new JSONObject()
                    .put("name", trophy.name)
                    .put("description", trophy.description);

            RequestBody body = RequestBody.create(jsonObject.toString(), JSON);

            Request request = new Request.Builder()
                    .url("https://us-central1-challengeup-49057.cloudfunctions.net/add_trophy")
                    .post(body)
                    .build();

            Response response = client.newCall(request).execute();
            String resStr = response.body().string();
            JSONObject object = new JSONObject(resStr);

            trophy.setId(object.getString("id"));
            return object.getString("id");
        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }
        return "";
    }
    public static String addNewTrophy(String name, String description) throws IllegalArgumentException{
        Validation.validateName(name);
        Validation.validateDescription(description);
        OkHttpClient client = new OkHttpClient();
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        try {
            JSONObject jsonObject = new JSONObject()
                    .put("name", name)
                    .put("description", description);

            RequestBody body = RequestBody.create(jsonObject.toString(), JSON);
            Request request = new Request.Builder()
                    .url("https://us-central1-challengeup-49057.cloudfunctions.net/add_trophy")
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

    public static ArrayList<Trophy> getAllTrophies(){
        try {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url("https://us-central1-challengeup-49057.cloudfunctions.net/get_all_trophies")
                    .get()
                    .build();
            Response response = client.newCall(request).execute();
            String resStr = response.body().string();

            JSONObject object = new JSONObject(resStr);
            object = new JSONObject(object.getString("trophies"));

            ArrayList<Trophy> trophies = new ArrayList<>();
            for (Iterator<String> it = object.keys(); it.hasNext(); ) {
                String key = it.next();

                Trophy trophy = new Trophy(object.getJSONObject(key).getString("name"),
                        object.getJSONObject(key).getString("description"));
                trophy.setId(key);
                trophies.add(trophy);
            }
            return trophies;
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static Trophy getTrophyById(String id){
        try {
            OkHttpClient client = new OkHttpClient();

            Request request = new Request.Builder()
                    .url("https://us-central1-challengeup-49057.cloudfunctions.net/get_trophy_by_id?trophy_id="+id)
                    .get()
                    .build();
            Response response = client.newCall(request).execute();
            String resStr = response.body().string();
            JSONObject object = new JSONObject(resStr);
            object = new JSONObject(object.getString("trophy"));

            Trophy trophy = new Trophy(object.getJSONObject(id).getString("name"),
                    object.getJSONObject(id).getString("description"));
            trophy.setId(id);
            return trophy;
        } catch (IOException | JSONException e) {
            return null;
        }
    }

    public void update(){
        OkHttpClient client = new OkHttpClient();
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        try {
            JSONObject jsonObject = new JSONObject()
                    .put("trophy_id", id)
                    .put("name", name)
                    .put("description", description);

            RequestBody body = RequestBody.create(jsonObject.toString(), JSON);

            Request request = new Request.Builder()
                    .url("https://us-central1-challengeup-49057.cloudfunctions.net/update_trophy")
                    .post(body)
                    .build();
            client.newCall(request).execute();

        } catch (JSONException  | IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<User> getUsersWithThisTrophy(){
        ArrayList<User> users = User.getAllUsers();
        ArrayList<User> a = (ArrayList<User>) users.stream().filter(x->x.getTrophies().contains(id)).collect(Collectors.toList());
        return a;
    }

    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    public String getId() {
        return id;
    }

    public void setName(String name) throws IllegalArgumentException {
        Validation.validateName(name);
        this.name = name;
    }
    public void setDescription(String description) throws IllegalArgumentException {
        Validation.validateDescription(description);
        this.description = description;
    }

    private void setId(String id) {
        this.id = id;
    }
}
