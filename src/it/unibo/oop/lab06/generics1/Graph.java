package it.unibo.oop.lab06.generics1;

import java.util.List;
import java.util.Set;

public interface Graph<N> {

    void addNode(N node);

    void addEdge(N source, N target);

    /**
     * @return all the nodes
     */
    Set<N> nodeSet();

    /**
     * Returns all the nodes directly targeted from a node.
     * 
     * @param node
     *            the node
     * @return all the nodes directly targeted from the passed node
     */
    Set<N> linkedNodes(N node);

    /**
     * Gets one sequence of nodes connecting source to target.
     * 
     * @param source
     *            the source node
     * @param target
     *            the target node
     * @return a sequence of nodes connecting sources and target
     */
    List<N> getPath(N source, N target);

}
