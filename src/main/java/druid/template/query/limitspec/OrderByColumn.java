package druid.template.query.limitspec;

/**
 * Created by Maksym_Bondarenko on 4/20/2016.
 */
public class OrderByColumn {

    public enum Direction {
        ascending, descending

    }

    String dimension;
    Direction direction = Direction.ascending;

    public OrderByColumn(String dimension) {
        this.dimension = dimension;
    }

    public OrderByColumn(String dimension, Direction direction) {
        this.dimension = dimension;
        this.direction = direction;
    }

    public String getDimension() {
        return dimension;
    }

    public Direction getDirection() {
        return direction;
    }
}
