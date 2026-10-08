import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class JacksonDemo {
    public static void main(String[] args) {
        // ObjectMapper is Jackson's central engine
        ObjectMapper mapper = new ObjectMapper();

        // Enable pretty-printing so the JSON output is formatted nicely
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        try {
            // ==========================================
            // 1. SERIALIZATION: Java Object -> JSON String
            // ==========================================
            System.out.println("--- 1. Serializing Java Object to JSON ---");
            Fan originalFan = new Fan(1, "Michael", "Jordan", "Bulls");

            String jsonOutput = mapper.writeValueAsString(originalFan);
            System.out.println("Generated JSON:\n" + jsonOutput);

            // ==========================================
            // 2. DESERIALIZATION: JSON String -> Java Object
            // ==========================================
            System.out.println("\n--- 2. Deserializing JSON to Java Object ---");
            String incomingJson = "{\n" +
                    "  \"id\" : 2,\n" +
                    "  \"first_name\" : \"LeBron\",\n" +
                    "  \"last_name\" : \"James\",\n" +
                    "  \"favoriteTeam\" : \"Lakers\"\n" +
                    "}";

            Fan parsedFan = mapper.readValue(incomingJson, Fan.class);
            System.out.println("Constructed Object: " + parsedFan);
            System.out.println("Extracted Team: " + parsedFan.getFavoriteTeam());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}