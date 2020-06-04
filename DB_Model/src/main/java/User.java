import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.stream.Collectors;

public class User {
    private String id;
    private String tag;
    private String nick;
    private String email;
    private String password;

    private int rp;
    private int totalRp;

    private ArrayList<String> categories;
    private ArrayList<String> subscriptions;
    private ArrayList<String> undone;
    private ArrayList<String> done;
    private ArrayList<String> saved;
    private ArrayList<String> trophies;

    private HashMap<String, String> links;

    private File photo;



    public User(String tag, String nick, String email, String password) {
        Validation.validateNickTagPassword(nick);
        Validation.validateNickTagPassword(tag);
        Validation.validateNickTagPassword(password);
        Validation.validateEmail(email);
        this.tag = tag;
        this.nick = nick;
        this.email = email;
        this.password = password;

        undone = new ArrayList<>();
        done = new ArrayList<>();
        categories = new ArrayList<>();
        subscriptions = new ArrayList<>();
        saved = new ArrayList<>();
        trophies = new ArrayList<>();
        id = null;
        photo = null;
        rp = 0;
        totalRp = 0;

        links = new HashMap<>();
        links.put("facebook","");
        links.put("instagram","");
        links.put("youtube","");
    }


    public void setTrophies(ArrayList<String> trophies) {
        this.trophies = trophies;
    }

    public User(String tag, String nick, String email, String password, ArrayList<String> categories) {
        this(tag, nick, email, password);
        this.categories = categories;
    }

    public static String addNewUser(User user){
        OkHttpClient client = new OkHttpClient();
        try {
            JSONObject jsonObject = new JSONObject()
                    .put("tag", user.tag)
                    .put("email", user.email)
                    .put("nick", user.nick)
                    .put("password", user.password)
                    .put("categories", user.categories)
                    .put("subscriptions", user.subscriptions)
                    .put("undone", user.undone)
                    .put("done", user.done)
                    .put("links", user.links)
                    .put("saved", user.saved)
                    .put("trophies", user.trophies)
                    .put("rp", user.rp)
                    .put("totalRp", user.totalRp);


            RequestBody requestBody;
            if (user.photo!=null){
                requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM)
                        .addFormDataPart("data", jsonObject.toString())
                        .addFormDataPart("file", user.photo.getName(), RequestBody.create(MediaType.parse("image/png"), user.photo))
                        .build();
            }   else {
                requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM)
                        .addFormDataPart("data", jsonObject.toString())
                        .build();
            }

            Request request = new Request.Builder()
                    .url("https://us-central1-challengeup-49057.cloudfunctions.net/add_user")
                    .post(requestBody)
                    .build();

            Response response = client.newCall(request).execute();
            String resStr = response.body().string();
            JSONObject object = new JSONObject(resStr);
            user.setId(object.getString("id"));
            return object.getString("id");
        } catch (JSONException  | IOException e) {
            e.printStackTrace();
        }
        return "";
    }
    public static String addNewUser(String tag, String nick, String email, String password, ArrayList<String> categories){
        Validation.validateNickTagPassword(nick);
        Validation.validateNickTagPassword(tag);
        Validation.validateNickTagPassword(password);
        Validation.validateEmail(email);
        OkHttpClient client = new OkHttpClient();
        try {
            JSONObject jsonObject = new JSONObject()
                    .put("tag", tag)
                    .put("email", email)
                    .put("nick", nick)
                    .put("password", password)
                    .put("categories", categories)
                    .put("subscriptions", new ArrayList())
                    .put("undone", new ArrayList())
                    .put("done", new ArrayList())
                    .put("links", new HashMap())
                    .put("saved", new ArrayList())
                    .put("trophies", new ArrayList())
                    .put("rp", 0)
                    .put("totalRp", 0);

            RequestBody requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM)
                        .addFormDataPart("data", jsonObject.toString())
                        .build();



            Request request = new Request.Builder()
                    .url("https://us-central1-challengeup-49057.cloudfunctions.net/add_user")
                    .post(requestBody)
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


    public void addChallengeToDone(Challenge challenge){
        done.add(challenge.getId());
    }
    public void addChallengeToUndone(Challenge challenge){
        undone.add(challenge.getId());
    }
    public void addChallengeToSaved(Challenge challenge){
        saved.add(challenge.getId());
    }
    public void addAchievement(Trophy trophy){trophies.add(trophy.getId());};



    public ArrayList<Challenge> getDoneChallenges(){
        ArrayList<Challenge> challenges = new ArrayList<>();
        for (String s:done) {
            challenges.add(Challenge.getChallengeById(s));
        }
        return challenges;
    }
    public ArrayList<Challenge> getUnDoneChallenges(){
        ArrayList<Challenge> challenges = new ArrayList<>();
        for (String s:undone) {
            challenges.add(Challenge.getChallengeById(s));
        }
        return challenges;
    }
    public ArrayList<Challenge> getSavedChallenges(){
        ArrayList<Challenge> challenges = new ArrayList<>();
        for (String s:saved) {
            challenges.add(Challenge.getChallengeById(s));
        }
        return challenges;
    }
    public ArrayList<Challenge> getAllCreatedChallenges(){
        ArrayList<Challenge> challenges = Challenge.getAllChallenges();
        ArrayList<Challenge> a = (ArrayList<Challenge>) challenges.stream().filter(x->x.getCreator_id().equals(id)).collect(Collectors.toList());
        return a;
    }

    public ArrayList<Trophy> getAchievementsAsTrophies(){
        ArrayList<Trophy> trophies = new ArrayList<>();
        for (String s: this.trophies) {
            trophies.add(Trophy.getTrophyById(s));
        }
        return trophies;
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

                HashMap<String,String> links = new HashMap<>();

                ArrayList<String> saved = new ArrayList<>();
                ArrayList<String> achievements = new ArrayList<>();
                try{
                    JSONArray s = object.getJSONObject(key).getJSONArray("trophies");
                    for (int i = 0; i< s.length(); ++i)achievements.add((String) s.get(i));
                } catch (JSONException ignored){}



                try{
                    JSONArray s = object.getJSONObject(key).getJSONArray("saved");
                    for (int i = 0; i< s.length(); ++i)saved.add((String) s.get(i));
                } catch (JSONException ignored){}


                try{
                    JSONObject l = object.getJSONObject(key).getJSONObject("links");
                    links.put("facebook", l.getString("facebook"));
                    links.put("instagram", l.getString("instagram"));
                    links.put("youtube", l.getString("youtube"));
                } catch (JSONException ignored){}

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
                user.setLinks(links);
                user.setSaved(saved);
                user.setTrophies(achievements);
                user.setRp(Integer.parseInt(object.getJSONObject(key).getString("rp")));
                user.setTotalRp(Integer.parseInt(object.getJSONObject(key).getString("totalRp")));
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

            HashMap<String,String> links = new HashMap<>();

            ArrayList<String> saved = new ArrayList<>();
            ArrayList<String> achievements = new ArrayList<>();
            try{
                JSONArray s = object.getJSONObject(id).getJSONArray("trophies");
                for (int i = 0; i< s.length(); ++i)achievements.add((String) s.get(i));
            } catch (JSONException ignored){}
            try{
                JSONArray s = object.getJSONObject(id).getJSONArray("saved");
                for (int i = 0; i< s.length(); ++i)saved.add((String) s.get(i));
            } catch (JSONException ignored){}

            try{
                JSONObject l = object.getJSONObject(id).getJSONObject("links");
                links.put("facebook", l.getString("facebook"));
                links.put("instagram", l.getString("instagram"));
                links.put("youtube", l.getString("youtube"));
            } catch (JSONException ignored){}

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
                user.setLinks(links);
                user.setSaved(saved);
                user.setTrophies(achievements);
                user.setRp(Integer.parseInt(object.getJSONObject(id).getString("rp")));
                user.setTotalRp(Integer.parseInt(object.getJSONObject(id).getString("totalRp")));

            return user;
        } catch (IOException | JSONException e) {
            return null;
        }
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
                    .put("subscriptions", subscriptions)
                    .put("links", links)
                    .put("saved", saved)
                    .put("trophies", trophies)
                    .put("rp", rp)
                    .put("totalRp", totalRp);

            //RequestBody body = RequestBody.create(jsonObject.toString(), JSON);
            RequestBody requestBody;
            if (photo!=null){
                requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM)
                        .addFormDataPart("data", jsonObject.toString())
                        .addFormDataPart("file", photo.getName(), RequestBody.create(MediaType.parse("image/png"), photo))
                        .build();
            }   else {
                requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM)
                        .addFormDataPart("data", jsonObject.toString())
                        .build();
            }



            Request request = new Request.Builder()
                    .url("https://us-central1-challengeup-49057.cloudfunctions.net/update_user")
                    .post(requestBody)
                    .build();

            client.newCall(request).execute();

        } catch (JSONException  | IOException e) {
            e.printStackTrace();
        }
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
    public ArrayList<String> getUndone() {
        return undone;
    }
    public ArrayList<String> getDone() {
        return done;
    }
    public ArrayList<String> getCategories() {
        return categories;
    }
    public ArrayList<String> getSubscriptions() {
        return subscriptions;
    }
    public String getFacebookLink(){
        return links.get("facebook");
    }
    public String getInstagramLink(){
        return links.get("instagram");
    }
    public String getYoutubeLink(){
        return links.get("youtube");
    }
    public File getPhoto() {
        return photo;
    }
    public ArrayList<String> getSaved() {
        return saved;
    }
    public ArrayList<String> getTrophies() {
        return trophies;
    }




    private void setLinks(HashMap<String, String> links) {
        this.links = links;
    }
    private void setId(String id){
        this.id = id;
    }
    public void setTag(String tag) {
        Validation.validateNickTagPassword(tag);
        this.tag = tag;
    }
    public void setNick(String nick) {
        Validation.validateNickTagPassword(nick);
        this.nick = nick;
    }
    public void setEmail(String email) {
        Validation.validateEmail(email);
        this.email = email;
    }
    public void setPassword(String password) {
        Validation.validateNickTagPassword(password);
        this.password = password;
    }
    public void setUndone(ArrayList<String> undone) {
        this.undone = undone;
    }
    public void setDone(ArrayList<String> done) {
        this.done = done;
    }
    public void setCategories(ArrayList<String> categories) {
        this.categories = categories;
    }
    public void setSubscriptions(ArrayList<String> subscriptions) {
        this.subscriptions = subscriptions;
    }
    public void setFacebookLink(String link){
        links.put("facebook", link);
    }
    public void setInstagramLink(String link){
        links.put("instagram", link);
    }
    public void setYuotubeLink(String link){
        links.put("youtube", link);
    }
    public void setPhoto(File photo) {
        this.photo = photo;
    }
    public void setSaved(ArrayList<String> saved) {
        this.saved = saved;
    }

    public int getRp() {
        return rp;
    }
    public void setRp(int rp) {
        this.rp = rp;
    }
    public int getTotalRp() {
        return totalRp;
    }
    public void setTotalRp(int totalRp) {
        this.totalRp = totalRp;
    }

    public static void main(String[] args) {
        Trophy trophy = Trophy.getTrophyById("-M8vdqsRpN7HvuZ8zbZw");
        System.out.println(trophy.getId());
        trophy.setName("ttt");
        trophy.update();

    }
}
