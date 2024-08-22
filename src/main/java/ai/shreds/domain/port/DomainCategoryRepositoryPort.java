package ai.shreds.domain.port;

import ai.shreds.domain.DomainCategoryEntity;
import java.util.Optional;
import java.util.UUID;

public interface DomainCategoryRepositoryPort {
    Optional<DomainCategoryEntity> findById(UUID id);
}