package eu.senla.library.api.repository;

import eu.senla.library.model.Language;

import java.util.List;

public interface LanguageRepository extends AbstractRepository<Language>{

    Language add(Language language);

    Language findById(Long id);

    List<Language> findAll();

    Language update(Language language);

    void delete(Language language);

    void deleteById(Long id);
}
