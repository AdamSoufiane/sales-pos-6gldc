package ai.shreds.domain;

import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import javax.persistence.*;
import java.time.LocalDateTime;
import javax.validation.constraints.Size;

@Data
@Builder
@Entity
@Table(name = "Supplier")
public class DomainSupplierEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false, length = 255)
    @Size(max = 255)
    private String name;

    @Column(name = "contact_info", nullable = false, length = 255)
    @Size(max = 255)
    private String contact_info;

    @Column(name = "address", nullable = false, length = 255)
    @Size(max = 255)
    private String address;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime created_at;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updated_at;

    @Column(name = "due_date")
    private LocalDateTime due_date;

    @Column(name = "company_name", nullable = false, length = 255)
    @Size(max = 255)
    private String company_name;
}