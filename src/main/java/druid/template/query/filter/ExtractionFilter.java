package druid.template.query.filter;

import java.util.Map;

/**
 *
 * @author Administrator
 */
public class ExtractionFilter extends Filter {

    private String type = "extraction";
    private String dimension;
    private String value;
    private ExtractionFunction extractionFn;

    public ExtractionFilter(String dimension, String outputName, Map<String, String> dbKeyToDruidKey) {
        this.dimension = dimension;
        this.value = outputName;
        this.extractionFn = new ExtractionFunction(dbKeyToDruidKey);
    }

    public String getDimension() {
        return dimension;
    }

    public String getValue() {
        return value;
    }

    public ExtractionFunction getExtractionFn() {
        return extractionFn;
    }

    @Override
    public String toString() {
        return "{\"type\": \"" + type + "\","
                + "\"dimension\": \"" + dimension + '\"'
                + ",\"outputName\": \"" + value + '\"'
                + ",\"extractionFn\": " + extractionFn + '}';
    }

    public static class ExtractionFunction {

        private String type = "lookup";
        private Lookup lookup;

        public ExtractionFunction(Map<String, String> dbKeyToDruidKey) {
            this.lookup = new Lookup(dbKeyToDruidKey);
        }

        public String getType() {
            return type;
        }

        public Lookup getLookup() {
            return lookup;
        }

        @Override
        public String toString() {
            return "{\"type\": \"" + type + "\","
                    + "\"lookup\": " + lookup + '}';
        }
    }

    private static class Lookup {

        private String type = "map";
        private Map<String, String> map;

        public Lookup(Map<String, String> dbKeyToDruidKey) {
            this.map = dbKeyToDruidKey;
        }

        public String getType() {
            return type;
        }

        public Map getMap() {
            return map;
        }

        @Override
        public String toString() {
            return "{\"type\": \"" + type + "\","
                    + "\"map\": " + getMapped() + '}';
        }

        private String getMapped() {
            StringBuilder mapped = new StringBuilder();
            mapped.append("{");
            for (String key : this.map.keySet()) {
                mapped.append("\"").append(key).append("\":\"").append(this.map.get(key)).append("\"").append(",");
            }
            mapped.deleteCharAt(mapped.lastIndexOf(",")).append("}");
            return mapped.toString();
        }
    }

}
