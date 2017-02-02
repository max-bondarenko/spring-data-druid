package druid.query;

import druid.query.limitspec.LimitSpec;

/**
 * Created by Maksym_Bondarenko on 4/20/2016.
 */
public class GroupBy extends Query {

    LimitSpec limitSpec;

    public GroupBy() {
        queryType = Type.groupBy;
    }

    public LimitSpec getLimitSpec() {
        return limitSpec;
    }

    public void setLimitSpec(LimitSpec limitSpec) {
        this.limitSpec = limitSpec;
    }
}
