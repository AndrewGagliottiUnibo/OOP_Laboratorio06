package it.unibo.oop.lab06.generics1;

import java.util.ArrayList; 
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class GraphImpl<N> implements Graph<N> {

    private final Map<N, Set<N>> map;

    public GraphImpl() {
        this.map = new TreeMap<>();
    }

    /**
     * @param node
     */
    public void addNode(final N node) {
        if (!this.map.containsKey(node)) {
            this.map.put(node, new TreeSet<>());
        }
    }

    /**
     * @param source
     * @param target
     */
    public void addEdge(final N source, final N target) {
        if (source != null && target != null) {
            this.map.get(source).add(target);
        }
    }

    /**
     * @return a Set
     */
    public Set<N> nodeSet() {
        if (!this.map.isEmpty()) {
            return this.map.keySet();
        }
        return new TreeSet<>();
    }

    /**
     * @param node
     * @return a Set
     */
    public Set<N> linkedNodes(final N node) {
        if (this.map.containsKey(node)) {
            return this.map.get(node);
        }
        return new TreeSet<>();
    }

    /**
     * Ho voluto di testa mia far sì che il metodo riportasse il percorso specifico che va da source a target
     * partendo però da source, non è la soluzione migliore e neanche la più bella, però funziona.
     * 
     * @param source
     * @param target
     * @return a List
     */
    public List<N> getPath(final N source, final N target) {
        final List<N> returnList = new ArrayList<>();
        if (!this.map.containsKey(source) || target == null) {
            System.out.println("Source doesn't exists, creating a new one");
            this.addNode(source);
            this.addEdge(source, target);
        }

        if (source.equals(target)) {
            returnList.add(source);
            return returnList;
        }

        final Set<N> controlSet = map.get(source);
        for (final N element: controlSet) {
            if (!element.equals(source) || !element.equals(target)) {
                returnList.add(element);
            }
        }
        return returnList;
    }

}
