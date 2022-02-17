package bus_system.util.graph;

import java.util.Comparator;

record Connection<E>(E connection, double weight) implements Comparator<Connection<E>> {
    public Connection() {
        this(null, -1);
    }

    @Override
    public int compare(Connection<E> o1, Connection<E> o2) {
        return Double.compare(o1.weight, o2.weight);
    }
}
