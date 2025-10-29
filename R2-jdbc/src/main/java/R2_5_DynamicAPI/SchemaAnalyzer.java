package R2_5_DynamicAPI;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.*;

public class SchemaAnalyzer {

    public static class FieldInfo {
        String name;
        String type; // "STRING", "NUMBER", "BOOLEAN", "OBJECT", "ARRAY"

        public FieldInfo(String name, String type) {
            this.name = name;
            this.type = type;
        }
    }

    public List<FieldInfo> analyzeSchema(String jsonData) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(jsonData);

        // Si es un array, analizar el primer elemento
        if (rootNode.isArray() && rootNode.size() > 0) {
            rootNode = rootNode.get(0);
        }

        List<FieldInfo> fields = new ArrayList<>();
        Iterator<Map.Entry<String, JsonNode>> fieldsIterator = rootNode.fields();

        while (fieldsIterator.hasNext()) {
            Map.Entry<String, JsonNode> field = fieldsIterator.next();
            String fieldName = field.getKey();
            JsonNode fieldValue = field.getValue();

            String fieldType = detectType(fieldValue);
            fields.add(new FieldInfo(fieldName, fieldType));
        }

        return fields;
    }

    private String detectType(JsonNode node) {
        if (node.isInt() || node.isLong() || node.isDouble() || node.isFloat()) {
            return "NUMBER";
        } else if (node.isBoolean()) {
            return "BOOLEAN";
        } else if (node.isObject()) {
            return "OBJECT";
        } else if (node.isArray()) {
            return "ARRAY";
        } else {
            return "STRING";
        }
    }

    public String convertToString(JsonNode node) {
        if (node == null || node.isNull()) {
            return "";
        } else if (node.isObject() || node.isArray()) {
            return node.toString();
        } else {
            return node.asText();
        }
    }
}