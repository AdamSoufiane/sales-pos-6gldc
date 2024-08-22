package ai.shreds.shared;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.AllArgsConstructor;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
public final class SharedTimestamp {
    @NotNull
    private final LocalDateTime value;
}