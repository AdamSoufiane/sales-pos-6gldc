package ai.shreds.domain.port;

import ai.shreds.domain.DomainProductEntity;
import java.util.Optional;
import java.util.UUID;

public interface DomainProductServicePort {
    void save(DomainProductEntity product);
    Optional<DomainProductEntity> findById(UUID id);
    void deleteById(UUID id);
}