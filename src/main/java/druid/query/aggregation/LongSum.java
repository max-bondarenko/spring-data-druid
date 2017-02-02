package druid.query.aggregation;

/**
 * Created by Maksym_Bondarenko on 4/20/2016.
 */
public class LongSum extends SimpleAggregation {

    public LongSum(String name) {
        super("longSum", name);
    }

    public LongSum(String name,String fieldName) {
        super("longSum", name, fieldName);
    }
}
