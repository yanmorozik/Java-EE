package eu.senla.library.repository;

import eu.senla.library.api.repository.LanguageRepository;
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
@RequiredArgsConstructor
public class LanguageRepositoryImpl implements LanguageRepository {

    private final Connection connection;

    @Override
    @SneakyThrows
    public Language add(Language language) {
        try (final PreparedStatement preparedStatement = connection
                .prepareStatement("INSERT INTO languages (name_language) VALUES (?)")) {
            preparedStatement.setString(1, language.getNameLanguage());
            preparedStatement.executeUpdate();
        }
        return language;
    }

    @Override
    @SneakyThrows
    public Language findById(Long id) {
        Language language = null;
        try (final PreparedStatement preparedStatement = connection
                .prepareStatement("SELECT * FROM languages WHERE id=?")) {
            preparedStatement.setInt(1, Math.toIntExact(id));
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            language = new Language();
            language.setId((long) resultSet.getInt("id"));
            language.setNameLanguage(resultSet.getString("name_language"));
        }
        return language;
    }

    @Override
    @SneakyThrows
    public List<Language> findAll() {
        List<Language> languages = new ArrayList<>();
        try (final Statement statement = connection.createStatement()) {
            String SQL = "SELECT * FROM languages";
            ResultSet resultSet = statement.executeQuery(SQL);
            while (resultSet.next()) {
                Language language = new Language();
                language.setId((long) resultSet.getInt("id"));
                language.setNameLanguage(resultSet.getString("name_language"));
                languages.add(language);
            }
        }
        return languages;
    }

    @Override
    @SneakyThrows
    public Language update(Language language) {
        Long index = language.getId();
        try (final PreparedStatement preparedStatement = connection
                .prepareStatement("UPDATE languages SET name_language=? WHERE id=?")) {
            preparedStatement.setString(1, language.getNameLanguage());
            preparedStatement.setInt(2, Math.toIntExact(index));
            preparedStatement.executeUpdate();
        }
        return language;
    }

    @Override
    public void delete(Language language) {
    }

    @Override
    @SneakyThrows
    public void deleteById(Long id) {
        try (final PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM languages WHERE id=?")) {
            preparedStatement.setInt(1, Math.toIntExact(id));
            preparedStatement.executeUpdate();
        }
    }
}
