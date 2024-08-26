package ai.shreds.adapter;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AdapterSupplierResponseDTO {
    private Integer id;
    private String name;
    private String contact_info;
    private String address;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
    private LocalDateTime due_date;
    private String company_name;
}