package eu.senla.library;

import eu.senla.library.config.DatabaseConfiguration;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = DatabaseConfiguration.class)
public class BaseRepositoryTest extends DatabaseHelper { }