package com.groupm.capp.druid.template.query.dimension;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.Map;

/**
 *
 * @author Administrator
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExtractionDimension {

    private String type = "extraction";
    private String dimension;
    private String outputName;
    private ExtractionFunction extractionFn;

    public ExtractionDimension(String dimension, String outputName, Map<String, String> druidKeyToDBKey) {
        this.dimension = dimension;
        this.outputName = outputName;
        this.extractionFn = new ExtractionFunction(druidKeyToDBKey);
    }

    public String getType() {
        return type;
    }

    public String getDimension() {
        return dimension;
    }

    public String getOutputName() {
        return outputName;
    }

    public ExtractionFunction getExtractionFn() {
        return extractionFn;
    }

    public static class ExtractionFunction {

        private String type = "lookup";
        private boolean optimize = true;
        private Lookup lookup;
        private boolean retainMissingValue = true;
        private boolean injective = false;

        public ExtractionFunction(Map<String, String> druidKeyToDBKey) {
            this.lookup = new Lookup(druidKeyToDBKey);
        }

        public String getType() {
            return type;
        }

        public boolean isOptimize() {
            return optimize;
        }

        public Lookup getLookup() {
            return lookup;
        }

        public boolean isRetainMissingValue() {
            return retainMissingValue;
        }

        public boolean isInjective() {
            return injective;
        }
    }

    public static class Lookup {

        private String type = "map";
        private Map map;

        public Lookup(Map<String, String> dbKeyToDruidKey) {
            this.map = dbKeyToDruidKey;
        }

        public String getType() {
            return type;
        }

        public Map getMap() {
            return map;
        }
    }
}
