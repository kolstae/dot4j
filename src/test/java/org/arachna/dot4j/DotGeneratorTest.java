/**
 *
 */
package org.arachna.dot4j;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Arrays;

import org.arachna.dot4j.model.Attributes;
import org.arachna.dot4j.model.Edge;
import org.arachna.dot4j.model.Graph;
import org.arachna.dot4j.model.HasAttributes;
import org.arachna.dot4j.model.Node;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author Dirk Weigenand
 *
 */
public class DotGeneratorTest {
    private Graph graph;

    @Before
    public void setUp() throws Exception {
        graph = new Graph("1");
    }

    @Test
    public final void testGenerateEmptyGraph() throws IOException {
        final StringWriter result = new StringWriter();
        DotGenerator.generate(result, graph);

        assertEquals("digraph 1 {\n}\n", result.toString());
    }

    @Test
    public final void testGenerateOneNode() throws IOException {
        graph.newNode();
        final StringWriter result = new StringWriter();
        DotGenerator.generate(result, graph);

        assertEquals("digraph 1 {\n0;\n}\n",  result.toString());
    }

    @Test
    public final void testGenerateTwoNodes() throws IOException {
        graph.newNode();
        graph.newNode();

        final StringWriter result = new StringWriter();
        DotGenerator.generate(result, graph);

        assertEquals("digraph 1 {\n0;\n1;\n}\n", result.toString());
    }

    @Test
    public final void testEmitEdgesWithoutAttributes() {
        final Node start = graph.newNode();
        final Node middle = graph.newNode();
        final Node end = graph.newNode();

        final Edge firstEdge = graph.newEdge(start, middle);
        final Edge secondEdge = graph.newEdge(middle, end);
        final String result = DotGenerator.emitEdges(Arrays.asList(firstEdge, secondEdge)).toString();

        assertEquals("0 -> 1;\n1 -> 2;\n", result);
    }

    @Test
    public final void testEmitEdgeWithoutAttributes() {
        final Node start = graph.newNode();
        final Node end = graph.newNode();

        final Edge edge = graph.newEdge(start, end);
        final String result = DotGenerator.emitEdge(edge);

        assertEquals("0 -> 1;\n", result);
    }

    @Test
    public final void testEmitClusters() {
        final Graph firstCluster = graph.newGraph();
        final Graph secondCluster = graph.newGraph();
        final String result = DotGenerator.emitClusters(Arrays.asList(firstCluster, secondCluster)).toString();

        assertEquals("subgraph cluster0 {\n}\nsubgraph cluster1 {\n}\n", result);
    }

    @Test
    public final void testEmitEmptyCluster() {
        final String result = DotGenerator.emitCluster(graph).toString();

        assertEquals("subgraph cluster1 {\n}\n", result);
    }

    @Test
    public final void testEmitEmptyClusterWithAttributes() {
        final Graph cluster = graph.newGraph();
        cluster.setAttribute("label", "label");
        final String result = DotGenerator.emitCluster(cluster).toString();

        assertEquals("subgraph cluster0 {\nlabel=\"label\";\n}\n", result);
    }

    @Test
    public final void testEmitNodeWithAttribute() {
        final Node node = graph.newNode();
        node.setAttribute("font", "Helvetica");
        node.setAttribute("label", "label");

        final String result = DotGenerator.emitNode(node);

        assertEquals("0 [font=\"Helvetica\", label=\"label\"];\n", result);
    }

    @Test
    public final void testEmitEmptyAttributes() {
        final HasAttributes attributes = new Attributes();

        final String result = DotGenerator.emitAttributes(attributes).toString();

        assertEquals("", result);
    }

    @Test
    public final void testEmitNonEmptyAttribute() {
        final HasAttributes attributes = new Attributes();
        attributes.setAttribute("label", "label");

        final String result = DotGenerator.emitAttributes(attributes).toString();

        assertEquals(" [label=\"label\"]", result);
    }

    @Test
    public final void testEmitTwoNonEmptyAttributes() {
        final HasAttributes attributes = new Attributes();
        attributes.setAttribute("font", "Helvetica")
                .setAttribute("label", "label");

        final String result = DotGenerator.emitAttributes(attributes).toString();

        assertEquals(" [font=\"Helvetica\", label=\"label\"]", result);
    }
}
