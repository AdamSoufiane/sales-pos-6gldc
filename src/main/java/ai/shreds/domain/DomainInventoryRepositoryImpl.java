package ai.shreds.domain;

import ai.shreds.shared.SharedInventoryDomainEntity;
import ai.shreds.infrastructure.InfrastructureInventoryRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class DomainInventoryRepositoryImpl implements DomainInventoryRepositoryPort {

    private final InfrastructureInventoryRepositoryPort infrastructureInventoryRepositoryPort;

    @Override
    public SharedInventoryDomainEntity findByProductId(UUID productId) {
        return infrastructureInventoryRepositoryPort.findByProductId(productId).orElse(null);
    }

    @Override
    @Transactional
    public void save(SharedInventoryDomainEntity inventory) {
        infrastructureInventoryRepositoryPort.save(inventory);
    }

    @Override
    @Transactional
    public void deleteByProductId(UUID productId) {
        infrastructureInventoryRepositoryPort.deleteByProductId(productId);
    }
}