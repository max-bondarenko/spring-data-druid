package druid.query.aggregation;

/**
 * Created by Maksym_Bondarenko on 4/20/2016.
 */
public class LongMin extends SimpleAggregation {

    protected LongMin(String name) {
        super("longMin", name);
    }
}
