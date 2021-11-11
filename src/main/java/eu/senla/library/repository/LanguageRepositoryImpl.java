package eu.senla.library.repository;

import eu.senla.library.api.repository.LanguageRepository;
import eu.senla.library.model.Language;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class LanguageRepositoryImpl implements LanguageRepository {

    private final List<Language> languages = new ArrayList<>();

    private static Long facilitiesIdSequence = 0L;

    private static Long generateFacilitiesId() {
        return facilitiesIdSequence++;
    }

    @Override
    public Language add(Language language) {
        language.setId(generateFacilitiesId());
        languages.add(language);
        return language;
    }

    @Override
    public Language findById(Long id) {
        return languages.get(id.intValue());
    }

    @Override
    public List<Language> findAll() {
        return languages;
    }

    @Override
    public Language update(Language language) {
        Long index = language.getId();
        languages.set(index.intValue(), language);
        return language;
    }

    @Override
    public void delete(Language language) {
        languages.remove(language);
    }

    @Override
    public void deleteById(Long id) {
        languages.remove(id.intValue());
    }
}
