package druid.query.filter;

import com.fasterxml.jackson.annotation.*;

/**
 * Created by Maksym_Bondarenko on 4/11/2016.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public abstract class Filter {

    protected String type;

    /**
     * Set to false if invalid value. EG value == null
     */
    protected boolean valid = false;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    @JsonIgnore
    public boolean isValid(){
        return valid;
    }

}
