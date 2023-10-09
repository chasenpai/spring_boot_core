package memory;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Memory {

    private long used;

    private long max;

    public Memory(long used, long max) {
        this.used = used;
        this.max = max;
    }

}
