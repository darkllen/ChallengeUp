import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.stream.Collectors;

public class Challenge {
    private String id;
    private String name;
    private String task;
    private String creator_id;

    private int likes;
    private int timesViewed;
    private int rewardRp;

    private ArrayList<String> rewardTrophies;
    private ArrayList<String> tags;
    private ArrayList<String> categories;

    public Challenge(String name, String task, String creator_id, ArrayList<String> tags, ArrayList<String> categories) throws IllegalArgumentException {
        this(name, task, creator_id);
        Validation.validateTags(tags);
        Validation.validateTags(categories);
        this.tags = tags;
        this.categories = categories; }
    public Challenge(String name, String task, String creator_id) throws IllegalArgumentException{
        Validation.validateName(name);
        Validation.validateTask(task);
        this.name = name;
        this.task = task;
        this.creator_id = creator_id;
        tags = new ArrayList<>();
        categories = new ArrayList<>();
        likes = 0;
        timesViewed = 0;
        rewardRp = 0;
        rewardTrophies = new ArrayList<>();
        id = null;
    }

    public static String addNewChallenge(Challenge challenge){
        OkHttpClient client = new OkHttpClient();
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        try {
            JSONObject jsonObject = new JSONObject()
                    .put("name", challenge.name)
                    .put("task", challenge.task)
                    .put("creator_id",challenge.creator_id)
                    .put("likes", challenge.likes)
                    .put("times_viewed", challenge.timesViewed)
                    .put("tags", challenge.tags)
                    .put("categories", challenge.categories)
                    .put("rewardRp", challenge.rewardRp)
                    .put("rewardTrophies", challenge.rewardTrophies);


            RequestBody body = RequestBody.create(jsonObject.toString(), JSON);

            Request request = new Request.Builder()
                    .url("https://us-central1-challengeup-49057.cloudfunctions.net/add_challenge")
                    .post(body)
                    .build();

            Response response = client.newCall(request).execute();
            String resStr = response.body().string();
            JSONObject object = new JSONObject(resStr);

            challenge.setId(object.getString("id"));
            return object.getString("id");
        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }
        return "";
    }
    public static String addNewChallenge(String name, String task, String creator_id, ArrayList<String> tags, ArrayList<String> categories) throws IllegalArgumentException{
        Validation.validateTags(tags);
        Validation.validateTags(categories);
        OkHttpClient client = new OkHttpClient();
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        try {
            JSONObject jsonObject = new JSONObject()
                    .put("name", name)
                    .put("task", task)
                    .put("creator_id",creator_id)
                    .put("likes", 0)
                    .put("times_viewed", 0)
                    .put("tags", tags)
                    .put("categories", categories)
                    .put("rewardRp", 0)
                    .put("rewardTrophies", new ArrayList());


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

    public static ArrayList<Challenge> getAllChallenges(){
        try {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url("https://us-central1-challengeup-49057.cloudfunctions.net/get_all_challenges")
                    .get()
                    .build();
            Response response = client.newCall(request).execute();
            String resStr = response.body().string();

            JSONObject object = new JSONObject(resStr);
            object = new JSONObject(object.getString("challenges"));

            ArrayList<Challenge> challenges = new ArrayList<>();
            for (Iterator<String> it = object.keys(); it.hasNext(); ) {
                String key = it.next();

                ArrayList<String> tagsArray = new ArrayList<>();
                ArrayList<String> categoriesArray = new ArrayList<>();

                ArrayList<String> trophiesArray = new ArrayList<>();

                try{
                    JSONArray rewardTrophies = object.getJSONObject(key).getJSONArray("rewardTrophies");
                    for (int i = 0; i< rewardTrophies.length(); ++i)trophiesArray.add((String) rewardTrophies.get(i));
                } catch (JSONException ignored){}

                try{
                    JSONArray tags = object.getJSONObject(key).getJSONArray("tags");
                    for (int i = 0; i< tags.length(); ++i)tagsArray.add((String) tags.get(i));
                } catch (JSONException ignored){}


                try {
                    JSONArray categories = object.getJSONObject(key).getJSONArray("categories");
                    for (int i = 0; i< categories.length(); ++i)categoriesArray.add((String) categories.get(i));
                }catch (JSONException ignored){}


                Challenge challenge = new Challenge(object.getJSONObject(key).getString("name"),
                        object.getJSONObject(key).getString("task"),
                        object.getJSONObject(key).getString("creator_id"),
                        tagsArray,
                        categoriesArray);
                challenge.setId(key);
                challenge.setLikes(Integer.parseInt(object.getJSONObject(key).getString("likes")));
                challenge.setTimesViewed(Integer.parseInt(object.getJSONObject(key).getString("times_viewed")));
                challenge.setRewardRp(Integer.parseInt(object.getJSONObject(key).getString("rewardRp")));
                challenge.setRewardTrophies(trophiesArray);
                challenges.add(challenge);
            }
            return challenges;
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static Challenge getChallengeById(String id){
        try {
            OkHttpClient client = new OkHttpClient();

            Request request = new Request.Builder()
                    .url("https://us-central1-challengeup-49057.cloudfunctions.net/get_challenge_by_id?challenge_id="+id)
                    .get()
                    .build();
            Response response = client.newCall(request).execute();
            String resStr = response.body().string();
            JSONObject object = new JSONObject(resStr);
            object = new JSONObject(object.getString("challenge"));

            ArrayList<String> tagsArray = new ArrayList<>();
            ArrayList<String> categoriesArray = new ArrayList<>();

            ArrayList<String> trophiesArray = new ArrayList<>();

            try{
                JSONArray rewardTrophies = object.getJSONObject(id).getJSONArray("rewardTrophies");
                for (int i = 0; i< rewardTrophies.length(); ++i)trophiesArray.add((String) rewardTrophies.get(i));
            } catch (JSONException ignored){}

            try{
                JSONArray tags = object.getJSONObject(id).getJSONArray("tags");
                for (int i = 0; i< tags.length(); ++i)tagsArray.add((String) tags.get(i));
            } catch (JSONException ignored){}


            try {
                JSONArray categories = object.getJSONObject(id).getJSONArray("categories");
                for (int i = 0; i< categories.length(); ++i)categoriesArray.add((String) categories.get(i));
            }catch (JSONException ignored){}

            Challenge challenge = new Challenge(object.getJSONObject(id).getString("name"),
                    object.getJSONObject(id).getString("task"),
                    object.getJSONObject(id).getString("creator_id"),
                    tagsArray,
                    categoriesArray);
            challenge.setId(id);
            challenge.setLikes(Integer.parseInt(object.getJSONObject(id).getString("likes")));
            challenge.setTimesViewed(Integer.parseInt(object.getJSONObject(id).getString("times_viewed")));
            challenge.setRewardRp(Integer.parseInt(object.getJSONObject(id).getString("rewardRp")));
            challenge.setRewardTrophies(trophiesArray);

            return challenge;
        } catch (IOException | JSONException e) {
            return null;
        }
    }

    public static ArrayList<Challenge> getAllWithCategory(String category){
        ArrayList<Challenge> challenges = Challenge.getAllChallenges();
        ArrayList<Challenge> a = (ArrayList<Challenge>) challenges.stream().filter(x->x.getCategories().contains(category)).collect(Collectors.toList());
        return a;
    }
    public static ArrayList<Challenge> getAllWithTag(String tag){
        ArrayList<Challenge> challenges = Challenge.getAllChallenges();
        ArrayList<Challenge> a = (ArrayList<Challenge>) challenges.stream().filter(x->x.getTags().contains(tag)).collect(Collectors.toList());
        return a;
    }
    public static ArrayList<Challenge> getAllWithCategories(ArrayList<String> categories){
        ArrayList<Challenge> challenges = Challenge.getAllChallenges();
        ArrayList<Challenge> a = (ArrayList<Challenge>) challenges.stream().filter(x->x.getCategories().containsAll(categories)).collect(Collectors.toList());
        return a;
    }
    public static ArrayList<Challenge> getAllWithTags(ArrayList<String> tags){
        ArrayList<Challenge> challenges = Challenge.getAllChallenges();
        ArrayList<Challenge> a = (ArrayList<Challenge>) challenges.stream().filter(x->x.getTags().containsAll(tags)).collect(Collectors.toList());
        return a;
    }

    public long numberOfPeopleWhoAccepted(){
        ArrayList<User> users = User.getAllUsers();
        return users.stream().filter(x->x.getUndone().contains(id)).count();
    }
    public long numberOfPeopleWhoComplete(){
        ArrayList<User> users = User.getAllUsers();
        return users.stream().filter(x->x.getDone().contains(id)).count();
    }
    public long numberOfPeopleWhoCompleteAndAccepted(){
        ArrayList<User> users = User.getAllUsers();
        return users.stream().filter(x->x.getDone().contains(id) || x.getUndone().contains(id)).count();
    }

    public ArrayList<Comment> getAllComments(){
        ArrayList<Comment> comments = Comment.getAllComments();
        ArrayList<Comment> a = (ArrayList<Comment>) comments.stream().filter(x->x.getChallenge_id().equals(id)).collect(Collectors.toList());
        return a;
    }

    public void update(){
        OkHttpClient client = new OkHttpClient();
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        try {
            JSONObject jsonObject = new JSONObject()
                    .put("challenge_id", id)
                    .put("name", name)
                    .put("task", task)
                    .put("creator_id", creator_id)
                    .put("likes", likes)
                    .put("categories", categories)
                    .put("tags", tags)
                    .put("times_viewed", timesViewed)
                    .put("rewardRp", rewardRp)
                    .put("rewardTrophhies", rewardTrophies);

            RequestBody body = RequestBody.create(jsonObject.toString(), JSON);

            Request request = new Request.Builder()
                    .url("https://us-central1-challengeup-49057.cloudfunctions.net/update_challenge")
                    .post(body)
                    .build();

            client.newCall(request).execute();
        } catch (JSONException  | IOException e) {
            e.printStackTrace();
        }
    }

    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getTask() {
        return task;
    }
    public int getLikes() {
        return likes;
    }
    public int getTimesViewed() {
        return timesViewed;
    }
    public String getCreator_id() {
        return creator_id;
    }
    public ArrayList<String> getTags() {
        return tags;
    }
    public ArrayList<String> getCategories() {
        return categories;
    }
    public int getRewardRp() {
        return rewardRp;
    }
    public ArrayList<String> getRewardTrophies() {
        return rewardTrophies;
    }

    public void setName(String name) throws IllegalArgumentException {
        Validation.validateName(name);

        this.name = name;
    }
    public void setTask(String task) throws IllegalArgumentException{
        Validation.validateTask(task);
        this.task = task;
    }
    public void setLikes(int likes) {
        this.likes = likes;
    }
    public void setTimesViewed(int timesViewed) {
        this.timesViewed = timesViewed;
    }
    public void setCreator_id(String creator_id) {
        this.creator_id = creator_id;
    }
    public void setTags(ArrayList<String> tags) throws IllegalArgumentException {
        Validation.validateTags(tags);
        this.tags = tags;
    }
    public void setCategories(ArrayList<String> categories) throws IllegalArgumentException {
        Validation.validateTags(categories);
        this.categories = categories;
    }
    public void setRewardRp(int rewardRp) {
        this.rewardRp = rewardRp;
    }
    public void setRewardTrophies(ArrayList<String> rewardTrophies) {
        this.rewardTrophies = rewardTrophies;
    }

    private void setId(String id){
        this.id = id;
    }

}
