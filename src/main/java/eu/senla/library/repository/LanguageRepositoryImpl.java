package eu.senla.library.repository;

import eu.senla.library.api.repository.GenreRepository;
import eu.senla.library.api.repository.LanguageRepository;
import eu.senla.library.model.Genre;
import eu.senla.library.model.Language;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Repository
public class LanguageRepositoryImpl extends AbstractRepositoryImpl<Language> implements LanguageRepository {

    public LanguageRepositoryImpl() {
        super(Language.class);
    }
}
