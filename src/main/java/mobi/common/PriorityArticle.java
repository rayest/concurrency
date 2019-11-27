package mobi.common;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Comparator;

@Data
@Accessors(chain = true)
public class PriorityArticle implements Comparable<PriorityArticle> {
    private int id;
    private String name;

    @Override
    public int compareTo(PriorityArticle o) {
        return Integer.compare(this.getId(), o.getId());
    }
}
