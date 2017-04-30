/**
 *
 */
package org.arachna.dot4j;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;

import org.arachna.dot4j.model.Edge;
import org.arachna.dot4j.model.Graph;
import org.arachna.dot4j.model.HasAttributes;
import org.arachna.dot4j.model.Node;

/**
 * @author Dirk Weigenand
 */
public final class DotGenerator {

    private DotGenerator() {
    }

    public static void generate(Appendable appendable, Graph graph) throws IOException {
        appendable.append("digraph ");
        appendable.append(graph.getId());
        appendable.append(" {\n");
        appendable.append(emitGraph(graph));
        appendable.append("}\n");
    }

    /**
     * @return
     * @param graph
     * @param result
     */
    private static void appendCommonEdgeAttributes(Graph graph, StringBuilder result) {

        if (!graph.getEdgeAttributes().isEmpty()) {
            result.append("edge");
            appendAttributes(graph.getEdgeAttributes(), result);
            result.append(";\n");
        }
    }

    /**
     * @return
     * @param graph
     * @param result
     */
    private static void appendCommonNodeAttributes(Graph graph, StringBuilder result) {
        if (!graph.getEdgeAttributes().isEmpty()) {
            result.append("node");
            appendAttributes(graph.getNodeAttributes(), result);
            result.append(";\n");
        }
    }

    /**
     * @param writer
     * @throws IOException
     */
    private static String emitGraph(Graph graph) {
        final StringBuilder result = new StringBuilder();

        appendGraphAttributes(graph, result);
        appendCommonNodeAttributes(graph, result);
        appendCommonEdgeAttributes(graph, result);
        appendNodes(graph.getNodes(), result);
        appendEdges(graph.getEdges(), result);
        result.append(emitClusters(graph.getClusters()));

        return result.toString();
    }

    private static void appendGraphAttributes(Graph graph, StringBuilder result) {

        for (Map.Entry<String, String> entry : graph) {
            result.append(entry.getKey()).append('=').append(entry.getValue()).append(";\n");
        }
    }

    protected static StringBuilder emitEdges(final Collection<Edge> edges) {
        final StringBuilder result = new StringBuilder();
        appendEdges(edges, result);
        return result;
    }

    private static void appendEdges(Collection<Edge> edges, StringBuilder result) {
        for (final Edge edge : edges) {
            appendEdge(edge, result);
        }
    }

    /**
     * @param edge
     */
    protected static String emitEdge(final Edge edge) {
        final StringBuilder buf = new StringBuilder();
        appendEdge(edge, buf);
        return buf.toString();
    }

    private static void appendEdge(Edge edge, StringBuilder buf) {
        buf.append(edge.getStartNodeId());
        buf.append(" -> ");
        buf.append(edge.getEndId());
        appendAttributes(edge, buf);
        buf.append(";\n");
    }

    /**
     * @param clusters
     * @throws IOException
     */
    protected static StringBuffer emitClusters(final Collection<Graph> clusters) {
        final StringBuffer result = new StringBuffer();

        for (final Graph cluster : clusters) {
            result.append(emitCluster(cluster));
        }

        return result;
    }

    private static void appendNodes(Collection<Node> nodes, StringBuilder buf) {
        for (final Node node : nodes) {
            appendNode(node, buf);
        }
    }

    protected static StringBuffer emitCluster(final Graph cluster) {
        final StringBuffer result = new StringBuffer();

        result.append(String.format("subgraph subgraph%s {\n", cluster.getId()));
        result.append(emitGraph(cluster));
        result.append("}\n");

        return result;
    }

    protected static String emitNode(final Node node) {
        final StringBuilder buf = new StringBuilder();
        appendNode(node, buf);
        return buf.toString();
    }

    private static void appendNode(Node node, StringBuilder buf) {
        buf.append(node.getId());
        appendAttributes(node, buf);
        buf.append(";\n");
    }

    protected static StringBuilder emitAttributes(final HasAttributes attributes) {
        final StringBuilder result = new StringBuilder();
        appendAttributes(attributes, result);
        return result;
    }

    private static void appendAttributes(HasAttributes attributes, StringBuilder buf) {
        if (attributes != null && !attributes.isEmpty()) {
            buf.append(" [");

            for (final Map.Entry<String, String> pair : attributes) {
                buf.append(pair.getKey()).append("=").append(pair.getValue()).append(", ");
            }
            buf.setLength(buf.length() - 1);
            buf.setCharAt(buf.length() - 1, ']');
        }
    }
}
