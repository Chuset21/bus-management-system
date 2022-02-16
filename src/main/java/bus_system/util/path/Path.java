package bus_system.util.path;

import java.util.List;
import java.util.Optional;

public interface Path<E> {
    Path<E> prependToPath(E nodeToAdd);
    Path<E> setCost(double cost);
    boolean isEmpty();
    List<E> getPathTaken();
    Optional<Double> getTotalCost();
}
