package bus_system.util.path;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class EmptyPath<E> implements Path<E>{
    @Override
    public Path<E> prependToPath(E nodeToAdd) {
        final Path<E> result = new ConcretePath<>();
        return result.prependToPath(nodeToAdd);
    }

    @Override
    public Path<E> setCost(double cost) {
        final Path<E> result = new ConcretePath<>();
        return result.setCost(cost);
    }

    @Override
    public boolean isEmpty() {
        return true;
    }

    @Override
    public List<E> getPathTaken() {
        return Collections.emptyList();
    }

    @Override
    public Optional<Double> getTotalCost() {
        return Optional.empty();
    }
}
