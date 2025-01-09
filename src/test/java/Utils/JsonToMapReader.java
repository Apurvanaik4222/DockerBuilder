package Utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class JsonToMapReader {

    public static List<HashMap<String,Object>> reader(String jsonFile) throws IOException {
       String jsonContent = FileUtils.readFileToString(new File(jsonFile), StandardCharsets.UTF_8);
        ObjectMapper objectMapper = new ObjectMapper();
        List<HashMap<String,Object>> list;
        if(jsonContent.trim().startsWith("[")) {
            list= objectMapper.readValue(jsonContent,new TypeReference<List<HashMap<String,Object>>>(){
            });

        }else
        {
           HashMap<String,Object> map = objectMapper.readValue(jsonContent,new TypeReference<HashMap<String,Object>>(){
            });
           list =new ArrayList<>();
           list.add(map);
        }

       return list;
    }

    public static void main(String[] args) throws IOException {
        //C:\Users\91762\IdeaProjects\SDETVS25Jan\src\test\java\resources\DataFiles\FlightReservationData\Data\TestData.json
        List<HashMap<String,Object>> list =  JsonToMapReader.reader("/home/selenium-docker/DataFiles/TestData.json");

        HashMap<String,Object> map = list.get(0);
       System.out.println(map.get("firstName"));
         System.out.println(map.get("lastName"));
    }
}


