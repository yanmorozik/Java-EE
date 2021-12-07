package eu.senla.library;

import eu.senla.library.config.DatabaseConfiguration;
import eu.senla.library.config.DatabaseH2Configuration;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {DatabaseH2Configuration.class})
public class BaseRepositoryTest{
}
