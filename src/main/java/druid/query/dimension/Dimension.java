package druid.query.dimension;

import com.fasterxml.jackson.annotation.*;
import java.util.Map;

/**
 * Created by Maksym_Bondarenko on 4/21/2016.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Dimension {

    String value;
    DefaultDimension internal;
    ExtractionDimension extractionDimension;

    public Dimension(String value) {
        this.value = value;
    }

    public Dimension(String dimension, String outputName) {
        internal = new DefaultDimension(dimension, outputName);
    }

    public Dimension(String dimension, String outputName, Map<String, String> dbKeyToDruidKey) {
        this.extractionDimension = new ExtractionDimension(dimension, outputName, dbKeyToDruidKey);
    }

    @JsonValue
    Object getValue() {
        if (internal != null) {
            return internal;
        }
        if (this.extractionDimension != null) {
            return extractionDimension;
        }
        return value;
    }

}
