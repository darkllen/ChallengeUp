import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Utilities {

    public static ArrayList<String> getCategories() {
        try {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url("https://us-central1-challengeup-49057.cloudfunctions.net/get_categories")
                    .get()
                    .build();
            Response response = client.newCall(request).execute();
            String resStr = response.body().string();

            JSONObject object = new JSONObject(resStr);

            JSONArray array = (JSONArray) object.get("categories");
            ArrayList<String> arrayList = new ArrayList<>();

            for (int i = 0 ; i < array.length(); ++i)arrayList.add((String) array.get(i));
            return arrayList;
        } catch (JSONException | IOException ignored) {
        }
        return null;
    }
}

