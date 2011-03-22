/**
 *
 */
package org.arachna.dot4j;

import java.io.IOException;
import java.io.Writer;
import java.util.Collection;

import org.arachna.dot4j.model.Attributes;
import org.arachna.dot4j.model.Edge;
import org.arachna.dot4j.model.Graph;
import org.arachna.dot4j.model.Node;

/**
 * @author Dirk Weigenand
 */
public final class DotGenerator {

    /**
     * Graph to generate .dot file from.
     */
    private final Graph graph;

    public DotGenerator(final Graph graph) {
        if (graph == null) {
            throw new IllegalArgumentException("Graph must not be null!");
        }

        this.graph = graph;
    }

    public void generate(final Writer writer) throws IOException {
        writer.append("digraph ");
        writer.append("{\n");

        writer.append(emitNodes(graph.getNodes()));
        writer.append(emitEdges(graph.getEdges()));
        writer.append(emitClusters(graph.getClusters()));

        writer.append("}\n");
    }

    protected StringBuffer emitEdges(final Collection<Edge> edges) {
        final StringBuffer result = new StringBuffer();

        for (final Edge edge : edges) {
            result.append(emitEdge(edge));
        }

        return result;
    }

    /**
     * @param edge
     */
    protected String emitEdge(final Edge edge) {
        return String.format("node%s -> node%s%s;\n", edge.getStartNode().getId(), edge.getEndNode().getId(),
            emitAttributes(edge.getAttributes()));
    }

    /**
     * @param clusters
     * @throws IOException
     */
    protected StringBuffer emitClusters(final Collection<Graph> clusters) {
        final StringBuffer result = new StringBuffer();

        for (final Graph cluster : clusters) {
            result.append(emitCluster(cluster));
        }

        return result;
    }

    /**
     * @param writer
     * @param nodes
     * @throws IOException
     */
    protected StringBuffer emitNodes(final Collection<Node> nodes) {
        final StringBuffer result = new StringBuffer();

        for (final Node node : nodes) {
            result.append(emitNode(node));
        }

        return result;
    }

    protected StringBuffer emitCluster(final Graph cluster) {
        final StringBuffer result = new StringBuffer();

        result.append(String.format("subgraph cluster%s {\n", cluster.getId()));
        result.append(emitNodes(cluster.getNodes()));

        for (final Graph subGraph : cluster.getClusters()) {
            result.append(this.emitCluster(subGraph));
        }

        result.append("}\n");

        return result;
    }

    protected String emitNode(final Node node) {
        return String.format("node%s%s;\n", node.getId(), emitAttributes(node.getAttributes()));
    }

    protected StringBuffer emitAttributes(final Attributes attributes) {
        final StringBuffer result = new StringBuffer();

        if (!attributes.isEmpty()) {
            result.append(" [");

            for (final Attributes.KeyValuePair pair : attributes) {
                result.append(String.format(" %s=\"%s\"", pair.getKey(), pair.getValue()));
            }

            result.append("]");
        }

        return result;
    }
}