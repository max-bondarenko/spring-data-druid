package druid.query.postaggregations;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Created by Maksym_Bondarenko on 4/21/2016.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FieldAccess extends PostAggregation {
    private String fieldName;

    /**
     * <code>{ "type" : "fieldAccess", "name": _output_name_, "fieldName" : _aggregator_name_ }</code>
     * @param name  name in responce event
     * @param fieldName reference to aggregator name in query
     */
    public FieldAccess(String name, String fieldName) {
        super(Type.fieldAccess,name);
        this.fieldName = fieldName;
    }

    public FieldAccess( String fieldName) {
        super(Type.fieldAccess,fieldName);
        this.fieldName = fieldName;
    }

    public String getFieldName() {
        return fieldName;
    }
}
