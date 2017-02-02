package druid.template.query.aggregation;

/**
 * Created by Maksym_Bondarenko on 4/20/2016.
 */
public abstract class Aggregation {
    protected String type;
    protected String name;

    public String getType() {
        return type;
    }

    /**
     *  Output name in responce event
     * @return
     */
    public String getName() {
        return name;
    }

}
