package druid.support;

import org.junit.Test;
import org.springframework.data.repository.query.QueryLookupStrategy;

import static org.junit.Assert.*;

/**
 * Created by Maksym_Bondarenko on 2/2/2017.
 */

public class DruidRepositoryFactoryTest {

    DruidRepositoryFactory factory = new DruidRepositoryFactory();
    @Test
    public void name() throws Exception {
        QueryLookupStrategy queryLookupStrategy = factory.getQueryLookupStrategy(QueryLookupStrategy.Key.CREATE);

        assertEquals(factory,queryLookupStrategy);

    }
}