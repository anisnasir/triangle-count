import org.junit.Assert;
import org.junit.Test;

import java.util.*;

public class MainTest {
    @Test
    public void test_basic() {
        Map<Long, HashSet<Long>> neighborMap = getDummyData();

        int triangleCount = 0;
        Set<Long> vertices = neighborMap.keySet();
        for (Long vertex : vertices) {
            List<Long> neighbors = new ArrayList<>(neighborMap.get(vertex));
            for (int i = 0; i < neighbors.size() - 1; i++) {
                Long neighborA = neighbors.get(i);
                if (vertex < neighborA) {
                    for (int j = i + 1; j < neighbors.size(); j++) {
                        Long neighborB = neighbors.get(j);
                        if (vertex < neighborB) {
                            if (neighborMap.get(neighborA).contains(neighborB) && neighborMap.get(neighborB).contains(neighborA)) {
                                triangleCount++;
                            }
                        }
                    }
                }
            }
        }

        Assert.assertEquals(2L, triangleCount);
    }

    private Map<Long, HashSet<Long>> getDummyData() {
        Map<Long, HashSet<Long>> neighborMap = new HashMap<>();
        HashSet<Long> objects1 = new HashSet<>();
        objects1.add(1L);
        objects1.add(2L);
        objects1.add(3L);
        neighborMap.put(0L, objects1);

        HashSet<Long> objects2 = new HashSet<>();
        objects2.add(0L);
        objects2.add(2L);
        objects2.add(3L);
        neighborMap.put(1L, objects2);

        HashSet<Long> objects3 = new HashSet<>();
        objects3.add(0L);
        objects3.add(1L);
        neighborMap.put(2L, objects3);

        HashSet<Long> objects4 = new HashSet<>();
        objects4.add(0L);
        objects4.add(1L);
        neighborMap.put(3L, objects4);
        return neighborMap;
    }
}

