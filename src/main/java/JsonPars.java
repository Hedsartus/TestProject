import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class JsonPars {
    public static String listToJson(List<?> list) {
        if(list != null && list.size()>0) {
            Type listType = new TypeToken<List<?>>() {}.getType();
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            return gson.toJson(list, listType);
        } else {
            return null;
        }
    }

    public static String readString(String nameFile) {
        File file = new File(nameFile);
        if(file.exists()) {
            try {
                JSONParser parser = new JSONParser();
                JSONArray jsonObject = (JSONArray) parser.parse(new FileReader(file));
                return jsonObject.toJSONString();
            } catch (IOException | ParseException e) {
                e.printStackTrace();
                return null;
            }
        } else {
            return null;
        }
    }

    public static <T> List<T> jsonToList(String jsonText, Class<T> elementType) {
        if(jsonText != null && !jsonText.equals("")) {
            try {
                List<T> list = new ArrayList<>();
                GsonBuilder builder = new GsonBuilder();
                Gson gson = builder.create();

                JSONParser parser = new JSONParser();
                JSONArray jsonArray = (JSONArray) parser.parse(jsonText);

                for (Object obj : jsonArray) {
                    JSONObject jsonObject = (JSONObject) obj;
                    T object = gson.fromJson(String.valueOf(jsonObject), elementType);
                    list.add(object);
                }
                return list;
            } catch (ParseException e) {
                e.printStackTrace();
                return null;
            }
        }
        return null;
    }
}
