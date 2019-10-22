import Shapes.Shape;
import com.google.gson.*;

import java.lang.reflect.Type;

public class ShapeClassAdapter implements JsonSerializer<Shape>, JsonDeserializer<Shape> {
    @Override
    public JsonElement serialize(Shape src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject result = new JsonObject();
        result.add("type", new JsonPrimitive(src.getClass().getSimpleName()));
        result.add("properties", context.serialize(src, src.getClass()));
        return result;
    }


    @Override
    public Shape deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        String type = jsonObject.get("type").getAsString();
        JsonElement element = jsonObject.get("properties");

        try {
            String thepackage = "Shapes.";
            return context.deserialize(element, Class.forName(thepackage + type));
        } catch (ClassNotFoundException cnfe) {
            throw new JsonParseException("Unknown element type: " + type, cnfe);
        }
    }
}
