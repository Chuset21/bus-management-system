package bus_system.util.path;

import java.text.DecimalFormat;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ConcretePath<E> implements Path<E> {
    private final static DecimalFormat formatter = new DecimalFormat("0.%s".formatted("#".repeat(6)));
    private final List<E> pathTaken;
    private double totalCost;

    public ConcretePath() {
        pathTaken = new LinkedList<>();
        totalCost = 0;
    }

    @Override
    public Path<E> prependToPath(E nodeToAdd) {
        prependNode(nodeToAdd);
        return this;
    }

    private void prependNode(E node) {
        pathTaken.add(0, node);
    }

    @Override
    public boolean isEmpty() {
        return pathTaken.isEmpty();
    }

    @Override
    public Path<E> setCost(double cost) {
        this.totalCost = cost;
        return this;
    }

    @Override
    public List<E> getPathTaken() {
        return pathTaken;
    }

    @Override
    public Optional<Double> getTotalCost() {
        return Optional.of(totalCost);
    }

    @Override
    public String toString() {
        return ("""
                Path taken, with a total distance of %s:
                %s""").formatted(
                formatter.format(totalCost),
                getPathTaken().stream().map(Object::toString).collect(Collectors.joining(",\n")));
    }
}
