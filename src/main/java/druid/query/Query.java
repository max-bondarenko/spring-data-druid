package druid.query;

import com.fasterxml.jackson.annotation.JsonInclude;
import druid.query.aggregation.Aggregation;
import druid.query.granularity.Granularity;
import druid.query.postaggregations.PostAggregation;
import druid.query.dimension.Dimension;
import druid.query.filter.Filter;

import java.util.*;

/**
 * Created by Maksym_Bondarenko on 4/20/2016.
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
public abstract class Query {

    protected enum Type {
        groupBy, topN, timeseries
    }

    protected Type queryType;
    private String dataSource;
    private Granularity granularity;
    private Integer threshold;

    private List<Dimension> dimensions = new LinkedList<>();
    private List<Aggregation> aggregations;
    private List<PostAggregation> postAggregations;
    private List<Interval> intervals;
    private Filter filter;

    public Type getQueryType() {
        return queryType;
    }

    public void setDataSource(String dataSource) {
        this.dataSource = dataSource;
    }

    public void setGranularity(Granularity granularity) {
        this.granularity = granularity;
    }

    public void setDimensions(List<Dimension> dimensions) {
        this.dimensions = dimensions;
    }

    public void addDimension(Dimension dimension) {
        if (dimension != null) {
            if (this.dimensions == null) {
                this.dimensions = new LinkedList<>();
            }
            this.dimensions.add(dimension);
        }
    }

    public void setThreshold(Integer threshold) {
        this.threshold = threshold;
    }

    public String getDataSource() {
        return dataSource;
    }

    public Granularity getGranularity() {
        return granularity;
    }

    public List<Dimension> getDimensions() {
        return dimensions;
    }

    public Integer getThreshold() {
        return threshold;
    }

    public List<Aggregation> getAggregations() {
        return aggregations;
    }

    public Filter getFilter() {
        return  filter != null && filter.isValid()  ? filter : null ;
    }

    public void setFilter(Filter filter) {
        this.filter = filter;
    }

    public void setAggregations(List<Aggregation> aggregations) {
        this.aggregations = aggregations;
    }

    public void addAggregation(Aggregation aggregation) {
        if (aggregation != null) {
            if (this.aggregations == null) {
                aggregations = new LinkedList<>();
            }
            aggregations.add(aggregation);
        }
    }

    public List<Interval> getIntervals() {
        return intervals;
    }

    public void setIntervals(List<Interval> intervals) {
        this.intervals = intervals;
    }

    public void addIntervals(Interval interval) {
        if (interval != null) {

            if (this.intervals == null) {
                this.intervals = new LinkedList<>();
            }
            this.intervals.add(interval);
        }
    }

    public List<PostAggregation> getPostAggregations() {
        return postAggregations;
    }

    public void setPostAggregations(List<PostAggregation> postAggregations) {
        this.postAggregations = postAggregations;
    }

    public void addPostAggregations(PostAggregation postAggregation) {
        if (postAggregation != null) {
            if (this.postAggregations == null) {
                this.postAggregations = new LinkedList<>();
            }
            this.postAggregations.add(postAggregation);
        }
    }

    /*
            "metric": "count",
            "filter": {
        "type": "and",
                "fields": [
        {
            "type": "selector",
                "dimensions": "dim1",
                "value": "some_value"
        },
        {
            "type": "selector",
                "dimensions": "dim2",
                "value": "some_other_val"
        }
        ]
    },


    */
}
