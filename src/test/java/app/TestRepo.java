package app;

import druid.repository.GetRepository;
import org.springframework.stereotype.Component;

import java.sql.Time;

/**
 * Created by Maksym_Bondarenko on 2/2/2017.
 */
@Component
public interface TestRepo extends GetRepository<Result, Time> {
}
