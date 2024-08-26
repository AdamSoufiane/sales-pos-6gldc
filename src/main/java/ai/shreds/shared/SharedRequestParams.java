package ai.shreds.shared;

import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SharedRequestParams {
    @NotNull
    private String param1;
    @NotNull
    private Integer param2;
}