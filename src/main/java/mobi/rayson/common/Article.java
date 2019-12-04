package mobi.rayson.common;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Article {
    private int id;
    private String name;
}
