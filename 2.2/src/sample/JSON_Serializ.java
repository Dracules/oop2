package sample;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Scanner;

public class JSON_Serializ extends Serializ_Deserializ {

    public void Save(ArrayList<learner> arr) {
        try {
            FileWriter nFile = new FileWriter("JSON.json");

            String json;
            GsonBuilder builder = new GsonBuilder();
            builder.registerTypeAdapter(learner.class, new InterfaceAdapter());
            Gson gson = builder.create();
            json = gson.toJson(arr,new TypeToken<java.util.List<learner>>() {}.getType());
            nFile.write(json);

            nFile.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public ArrayList<learner> Load() {
        ArrayList<learner> arr = new ArrayList<learner>();
        try {
            FileReader nFile = new FileReader("JSON.json");
            Scanner scan = new Scanner(nFile);

            GsonBuilder builder = new GsonBuilder();
            builder.registerTypeAdapter(learner.class, new InterfaceAdapter());
            Gson gson = builder.create();
            arr.addAll(gson.fromJson(scan.nextLine(),new TypeToken<java.util.List<learner>>() {}.getType()));
            nFile.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return arr;
    }

    public class InterfaceAdapter implements JsonSerializer, JsonDeserializer {

        private static final String CLASSNAME = "CLASSNAME";
        private static final String DATA = "DATA";

        public Object deserialize(JsonElement jsonElement, Type type,
                                JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {

            JsonObject jsonObject = jsonElement.getAsJsonObject();
            JsonPrimitive prim = (JsonPrimitive) jsonObject.get(CLASSNAME);
            String className = prim.getAsString();
            Class klass = getObjectClass(className);
            return jsonDeserializationContext.deserialize(jsonObject.get(DATA), klass);
        }
        public JsonElement serialize(Object jsonElement, Type type, JsonSerializationContext jsonSerializationContext) {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty(CLASSNAME, jsonElement.getClass().getName());
            jsonObject.add(DATA, jsonSerializationContext.serialize(jsonElement));
            return jsonObject;
        }
        /****** Helper method to get the className of the object to be deserialized *****/
        public Class getObjectClass(String className) {
            try {
                return Class.forName(className);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                throw new JsonParseException(e.getMessage());
            }
        }
    }
}

