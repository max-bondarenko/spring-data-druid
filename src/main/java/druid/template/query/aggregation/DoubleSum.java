package druid.template.query.aggregation;

/**
 * Created by Maksym_Bondarenko on 4/20/2016.
 */
public class DoubleSum extends SimpleAggregation {

    public DoubleSum(String name) {
        super("doubleSum", name);
    }

    /**
     * <code>{ "type" : "doubleSum", "name" : &lt;output_name&gt;, "fieldName" : &lt;metric_name&gt; }
     * </code>
     *
     * @param name
     * @param fieldName
     */
    public DoubleSum(String name,String fieldName) {
        super("doubleSum", name, fieldName);
    }
}
