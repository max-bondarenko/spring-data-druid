package druid.template.query.aggregation;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Maksym_Bondarenko on 4/20/2016.
 */

public class Cardinality extends Aggregation {
    private List<String> fieldNames = new LinkedList<>();
    private boolean byRow;

    public Cardinality(String name) {
        type = "cardinality";
        this.name = name;
    }

    /**
     * Cardinality by value
     * <p>
     * When setting byRow to false (the default) it computes the cardinality of the set composed of the union of all
     * dimension values for all the given dimensions.
     * <p>
     * For a single dimension, this is equivalent to
     * <p/>
     * <code>SELECT COUNT(DISTINCT(dimension)) FROM <datasource> </code>
     */
    public Cardinality(String name, String... args) {
        this(name);
        fieldNames.addAll(Arrays.asList(args));
    }

    public List<String> getFieldNames() {
        return fieldNames;
    }

    public void setByRow(boolean byRow) {
        this.byRow = byRow;
    }

    public boolean isByRow() {
        return byRow;
    }
}
