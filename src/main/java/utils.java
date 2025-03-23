import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.Map;

public class utils {
    public void readJson(){
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            // Read JSON file into a Map
            Map<String, Object> jsonMap = objectMapper.readValue(new File("accountsDB.json"), Map.class);
            System.out.println(jsonMap);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
