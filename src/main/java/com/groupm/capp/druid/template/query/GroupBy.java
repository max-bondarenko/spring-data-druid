package com.groupm.capp.druid.template.query;

import com.groupm.capp.druid.template.query.limitspec.LimitSpec;

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
