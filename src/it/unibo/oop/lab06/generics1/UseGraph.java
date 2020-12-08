package it.unibo.oop.lab06.generics1;

public final class UseGraph {

    private UseGraph() {
    }

    public static void main(final String... args) {
        final Graph<String> g = new GraphImpl<>();
        final Graph<String> f = new GraphImpl<>();
        g.addNode("a");
        g.addNode("b");
        g.addNode("c");
        g.addNode("d");
        g.addNode("e");
        g.addEdge("a", "b");
        g.addEdge("b", "c");
        g.addEdge("c", "d");
        g.addEdge("d", "e");
        g.addEdge("c", "a");
        g.addEdge("e", "a");

        System.out.println(g.nodeSet());
        System.out.println(f.nodeSet());

        System.out.println(g.linkedNodes("c"));
        System.out.println(f.linkedNodes("c"));

        System.out.println(g.getPath("b", "a"));
        System.out.println(f.getPath("b", "a"));
    }
}
