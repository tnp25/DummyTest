package demo.data;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

public class jsonDataReader {

    public void getJsonData() throws IOException, ParseException {

        // json to string

        String json = FileUtils.readFileToString(new File(System.getProperty("user.dir")+"src/test/java/demo/data/purchaseData.json"), StandardCharsets.UTF_8);


        //string to map using JacksonBind (maven dependency)

        ObjectMapper mapper = new ObjectMapper();
        List<Map<String, String>> map = mapper.readValue(json, new TypeReference<List<Map<String,String>>>() {});
    }
}

