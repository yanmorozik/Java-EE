package eu.senla.library.repository;

import eu.senla.library.api.repository.LanguageRepository;
import eu.senla.library.model.Language;
import org.springframework.stereotype.Repository;

@Repository
public class LanguageRepositoryImpl extends AbstractRepositoryImpl<Language> implements LanguageRepository {

    public LanguageRepositoryImpl() {
        super(Language.class);
    }

    @Override
    protected String getNameGraph() {
        return "languageEntityGraph";
    }
}
