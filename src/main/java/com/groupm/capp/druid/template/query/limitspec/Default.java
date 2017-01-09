package com.groupm.capp.druid.template.query.limitspec;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.*;

/**
 * Created by Maksym_Bondarenko on 4/20/2016.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Default  extends LimitSpec {

    String type = "default";
    int limit;
    List<OrderByColumn> columns ;

    public Default(int limit) {
        this.limit = limit;
    }

    public Default(int limit, OrderByColumn ... columns) {
        this.limit = limit;
        this.columns = new LinkedList<>();
        this.columns.addAll(Arrays.asList(columns));
    }

    public String getType() {
        return type;
    }

    public int getLimit() {
        return limit;
    }

    public List<OrderByColumn> getColumns() {
        return columns;
    }
}
