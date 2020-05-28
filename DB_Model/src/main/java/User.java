import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.stream.Collectors;

public class User {
    private String id;
    private String tag;
    private String nick;
    private String email;
    private String password;

    private ArrayList<String> categories;
    private ArrayList<String> subscriptions;
    private ArrayList<String> undone;
    private ArrayList<String> done;



    public User(String tag, String nick, String email, String password) {
        this.tag = tag;
        this.nick = nick;
        this.email = email;
        this.password = password;

        undone = new ArrayList<>();
        done = new ArrayList<>();
        categories = new ArrayList<>();
        id = null;
    }

    public User(String tag, String nick, String email, String password, ArrayList<String> categories) {
        this(tag, nick, email, password);
        this.categories = categories;
    }

    public void addChallengeToDone(Challenge challenge){
        done.add(challenge.getId());
    }
    public void addChallengeToUndone(Challenge challenge){
        undone.add(challenge.getId());
    }

    public ArrayList<Challenge> getDoneChallenges(){
        ArrayList<Challenge> challenges = new ArrayList<>();
        for (String s:done) {
            challenges.add(Challenge.getChallengeById(s));
        }
        return challenges;
    }

    public ArrayList<User> getSubscriptionsAsUsers(){
        ArrayList<User> users = new ArrayList<>();
        for (String s:subscriptions) {
            users.add(User.getUserById(s));
        }
        return users;
    }
    public ArrayList<User> getSubscribersAsUsers(){
       ArrayList<User> users = getAllUsers();
       ArrayList<User> a = (ArrayList<User>) users.stream().filter(x->x.getSubscriptions().contains(id)).collect(Collectors.toList());
       return a;
    }


    public ArrayList<Challenge> getUnDoneChallenges(){
        ArrayList<Challenge> challenges = new ArrayList<>();
        for (String s:undone) {
            challenges.add(Challenge.getChallengeById(s));
        }
        return challenges;
    }

    public static String addNewUser(User user){
        String id = addNewUser(user.tag, user.nick, user.email, user.password, user.categories);
        user.setId(id);
        return id;
    }
    public static String addNewUser(String tag, String nick, String email, String password, ArrayList<String> categories){
        OkHttpClient client = new OkHttpClient();
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        try {
            JSONObject jsonObject = new JSONObject()
                    .put("tag", tag)
                    .put("email", email)
                    .put("nick", nick)
                    .put("password", password)
                    .put("categories", categories);

            RequestBody body = RequestBody.create(jsonObject.toString(), JSON);

            Request request = new Request.Builder()
                    .url("https://us-central1-challengeup-49057.cloudfunctions.net/add_user")
                    .post(body)
                    .build();

            Response response = client.newCall(request).execute();
            String resStr = response.body().string();
            JSONObject object = new JSONObject(resStr);
            return object.getString("id");

        } catch (JSONException  | IOException e) {
            e.printStackTrace();
        }
        return "";
    }
    public static ArrayList<User> getAllUsers(){
        try {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url("https://us-central1-challengeup-49057.cloudfunctions.net/get_all_users")
                    .get()
                    .build();
            Response response = client.newCall(request).execute();
            String resStr = response.body().string();

            JSONObject object = new JSONObject(resStr);
            object = new JSONObject(object.getString("users"));

            ArrayList<User> users = new ArrayList<>();
            for (Iterator<String> it = object.keys(); it.hasNext(); ) {
                String key = it.next();

                ArrayList<String> undoneArray = new ArrayList<>();
                ArrayList<String> doneArray = new ArrayList<>();
                ArrayList<String> categoriesArray = new ArrayList<>();
                ArrayList<String> subscriptionsArray = new ArrayList<>();

                try{
                    JSONArray subscriptions = object.getJSONObject(key).getJSONArray("subscriptions");
                    for (int i = 0; i< subscriptions.length(); ++i)subscriptionsArray.add((String) subscriptions.get(i));
                } catch (JSONException ignored){}


                try{
                    JSONArray categories = object.getJSONObject(key).getJSONArray("categories");
                    for (int i = 0; i< categories.length(); ++i)categoriesArray.add((String) categories.get(i));
                } catch (JSONException ignored){}

                try{
                    JSONArray undone = object.getJSONObject(key).getJSONArray("undone");
                    for (int i = 0; i< undone.length(); ++i)undoneArray.add((String) undone.get(i));
                } catch (JSONException ignored){}

                try {
                    JSONArray done = object.getJSONObject(key).getJSONArray("done");
                    for (int i = 0; i< done.length(); ++i)doneArray.add((String) done.get(i));
                }catch (JSONException ignored){}

                User user = new User(object.getJSONObject(key).getString("tag"),
                        object.getJSONObject(key).getString("nick"),
                        object.getJSONObject(key).getString("email"),
                        object.getJSONObject(key).getString("password"),
                        categoriesArray);
                user.setId(key);
                user.setDone(doneArray);
                user.setUndone(undoneArray);
                user.setSubscriptions(subscriptionsArray);

                users.add(user);


            }
            return users;
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static User getUserById(String id){
        try {
            OkHttpClient client = new OkHttpClient();

            Request request = new Request.Builder()
                    .url("https://us-central1-challengeup-49057.cloudfunctions.net/get_user_by_id?user_id="+id)
                    .get()
                    .build();
            Response response = client.newCall(request).execute();
            String resStr = response.body().string();
            JSONObject object = new JSONObject(resStr);
            object = new JSONObject(object.getString("user"));

            ArrayList<String> undoneArray = new ArrayList<>();
            ArrayList<String> doneArray = new ArrayList<>();
            ArrayList<String> categoriesArray = new ArrayList<>();
            ArrayList<String> subscriptionsArray = new ArrayList<>();

            try{
                JSONArray subscriptions = object.getJSONObject(id).getJSONArray("subscriptions");
                for (int i = 0; i< subscriptions.length(); ++i)subscriptionsArray.add((String) subscriptions.get(i));
            } catch (JSONException ignored){}


            try{
                JSONArray categories = object.getJSONObject(id).getJSONArray("categories");
                for (int i = 0; i< categories.length(); ++i)categoriesArray.add((String) categories.get(i));
            } catch (JSONException ignored){}

            try{
                JSONArray undone = object.getJSONObject(id).getJSONArray("undone");
                for (int i = 0; i< undone.length(); ++i)undoneArray.add((String) undone.get(i));
            } catch (JSONException ignored){}

            try {
                JSONArray done = object.getJSONObject(id).getJSONArray("done");
                for (int i = 0; i< done.length(); ++i)doneArray.add((String) done.get(i));
            }catch (JSONException ignored){}

                User user = new User(object.getJSONObject(id).getString("tag"),
                        object.getJSONObject(id).getString("nick"),
                        object.getJSONObject(id).getString("email"),
                        object.getJSONObject(id).getString("password"),
                        categoriesArray);
                user.setId(id);
                user.setUndone(undoneArray);
                user.setDone(doneArray);
                user.setSubscriptions(subscriptionsArray);

            return user;
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
    public void update(){
        OkHttpClient client = new OkHttpClient();
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        try {
            JSONObject jsonObject = new JSONObject()
                    .put("user_id", id)
                    .put("tag", tag)
                    .put("email", email)
                    .put("nick", nick)
                    .put("password", password)
                    .put("done", done)
                    .put("undone", undone)
                    .put("categories", categories)
                    .put("subscriptions", subscriptions);

            RequestBody body = RequestBody.create(jsonObject.toString(), JSON);

            Request request = new Request.Builder()
                    .url("https://us-central1-challengeup-49057.cloudfunctions.net/update_user")
                    .post(body)
                    .build();

            client.newCall(request).execute();

        } catch (JSONException  | IOException e) {
            e.printStackTrace();
        }
    }



    private void setId(String id){
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getTag() {
        return tag;
    }

    public String getNick() {
        return nick;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ArrayList<String> getUndone() {
        return undone;
    }

    public void setUndone(ArrayList<String> undone) {
        this.undone = undone;
    }

    public ArrayList<String> getDone() {
        return done;
    }

    public void setDone(ArrayList<String> done) {
        this.done = done;
    }

    public ArrayList<String> getCategories() {
        return categories;
    }

    public void setCategories(ArrayList<String> categories) {
        this.categories = categories;
    }

    public ArrayList<String> getSubscriptions() {
        return subscriptions;
    }

    public void setSubscriptions(ArrayList<String> subscriptions) {
        this.subscriptions = subscriptions;
    }
}
