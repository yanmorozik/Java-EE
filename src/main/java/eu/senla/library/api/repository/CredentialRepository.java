package eu.senla.library.api.repository;

import eu.senla.library.model.Credential;

public interface CredentialRepository extends AbstractRepository<Credential> {
    Credential findByLogin(String login);
}
