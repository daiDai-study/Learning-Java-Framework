package org.daistudy.json.jackson.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigDecimal;

@RestController
@RequestMapping("/test")
public class TestController {
    @Resource
    private ObjectMapper objectMapper;

    @GetMapping("/1")
    public JsonNode test1() throws JsonProcessingException {
        ObjectNode root = objectMapper.createObjectNode();
        root.put("str", "hello");

        root.put("float", 0.0f);
        root.put("double", 0.0);

        root.put("byte", (byte)2);
        root.put("short", (short)2);
        root.put("int", 2);
        root.put("long", 2L);

        root.put("boolean", true);

        root.put("char", 's');

        root.put("decimal1", new BigDecimal("0.0"));
        root.put("decimal2", new BigDecimal("1.0"));
        root.put("decimal3", new BigDecimal("1.5"));

        String jsonStr = "{\"str\":\"hello\",\"float\":0.0,\"double\":0.0,\"byte\":2,\"short\":2,\"int\":2,\"long\":2,\"boolean\":true,\"char\":115}";
        JsonNode jsonNode = objectMapper.readTree(jsonStr);

        return root;
    }

    @GetMapping("/2")
    public JsonNode test2() throws JsonProcessingException {
        ObjectNode root = objectMapper.createObjectNode();
        root.put("str", "hello");
        root.put("int", 2);

        ObjectNode nestedObj = objectMapper.createObjectNode();
        ArrayNode nestedArr = objectMapper.createArrayNode();

        nestedObj.put("type", "type");
        nestedObj.put("val", "val");
        root.putPOJO("putObj", nestedObj);
        root.set("setObj", nestedObj);

        nestedArr.add("world");
        nestedArr.add("3");
        nestedArr.add(nestedObj);
        root.putPOJO("putArr", nestedArr);
        root.set("setArr", nestedArr);

        String jsonStr = "{\"str\":\"hello\",\"int\":2,\"putObj\":{\"type\":\"type\",\"val\":\"val\"},\"setObj\":{\"type\":\"type\",\"val\":\"val\"},\"putArr\":[\"world\",\"3\",{\"type\":\"type\",\"val\":\"val\"}],\"setArr\":[\"world\",\"3\",{\"type\":\"type\",\"val\":\"val\"}]}";
        JsonNode jsonNode = objectMapper.readTree(jsonStr);

        return root;
    }
}
