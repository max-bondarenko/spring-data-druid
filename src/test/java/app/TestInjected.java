package app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Maksym_Bondarenko on 2/2/2017.
 */
//@Component
public class TestInjected {

    @Autowired
    public TestInjected(TestRepo repo) {
        this.repo = repo;
    }

    public TestRepo repo;
}