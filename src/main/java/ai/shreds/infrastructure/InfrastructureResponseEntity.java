package ai.shreds.infrastructure;

import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InfrastructureResponseEntity<T> {
    private T data;
    private String status;
    private String message;
    private LocalDateTime timestamp;

    public InfrastructureResponseEntity(T data, String status, String message) {
        this.data = data;
        this.status = status;
        this.message = message;
        this.timestamp = LocalDateTime.now();
    }

    public InfrastructureResponseEntity(T data) {
        this.data = data;
        this.status = HttpStatus.OK.value() + "";
        this.message = "";
        this.timestamp = LocalDateTime.now();
    }

    public InfrastructureResponseEntity(T data, String status) {
        this.data = data;
        this.status = status;
        this.message = "";
        this.timestamp = LocalDateTime.now();
    }

    public InfrastructureResponseEntity(T data, String status, LocalDateTime timestamp) {
        this.data = data;
        this.status = status;
        this.message = "";
        this.timestamp = timestamp;
    }
}