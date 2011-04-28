/**
 *
 */
package org.arachna.dot4j.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * A GraphViz graph.
 *
 * @author Dirk Weigenand
 */
public final class Graph extends Attributes {
    /**
     * Id of this cluster.
     */
    private final String id;

    /**
     * common attributes for this graphs nodes.
     */
    private final HasAttributes nodeAttributes = new Attributes();

    /**
     * common attributes for this graphs edges.
     */
    private final HasAttributes edgeAttributes = new Attributes();

    /**
     * factory for cluster ids.
     */
    private final IdFactory clusterIdFactory;

    /**
     * factory for node ids.
     */
    private final IdFactory nodeIdFactory;

    /**
     * Clusters contained in this graph.
     */
    private final Collection<Graph> clusters = new ArrayList<Graph>();

    /**
     * nodes contained in this graph/cluster.
     */
    private final Collection<Node> nodes = new ArrayList<Node>();

    /**
     * edges in this graph.
     */
    private final Collection<Edge> edges = new ArrayList<Edge>();

    /**
     * Create a subgraph or cluster with the given parent graph.
     *
     * @param parent parent graph
     */
    public Graph(String id) {
        this.id = id;
        clusterIdFactory = new IdFactory();
        nodeIdFactory = new IdFactory();
    }

    /**
     * Create a subgraph or cluster with the given parent graph.
     *
     * @param parent parent graph
     * @param id
     */
    private Graph(Graph parent) {

        if (parent != null) {
            clusterIdFactory = parent.getClusterIdFactory();
            nodeIdFactory = parent.getNodeIdFactory();
        } else {
            clusterIdFactory = new IdFactory();
            nodeIdFactory = new IdFactory();
        }

        id = clusterIdFactory.nextId();
    }

    /**
     * Create a top level graph object.
     */
    public Graph() {
        this("");
    }

    /**
     * Create a new subgraph/cluster.
     *
     * @return a new subgraph/cluster.
     */
    public Graph newGraph() {
        final Graph child = new Graph(this);

        clusters.add(child);

        return child;
    }

    /**
     * Create a node and associate it with this graph/cluster.
     *
     * @return creates a new node object and associates it with this cluster.
     */
    public Node newNode() {
        return newNode(nodeIdFactory.nextId());
    }

    public Node newNode(int id) {
        return newNode(Integer.toString(id));
    }

    public Node newNode(long id) {
        return newNode(Long.toString(id));
    }

    public Node newNode(String id) {
        final Node node = new Node(id);

        nodes.add(node);

        return node;
    }

    public Edge newEdge(final Node startNode, final Node endNode) {
        if (startNode == null || endNode == null) {
            throw new IllegalArgumentException("start and end node must not be null!");
        }

        return newEdge(startNode.getId(), endNode.getId());
    }

    public Edge newEdge(int startNodeId, int endNodeId) {
        return newEdge(Integer.toString(startNodeId), Integer.toString(endNodeId));
    }

    public Edge newEdge(long startNodeId, long endNodeId) {
        return newEdge(Long.toString(startNodeId), Long.toString(endNodeId));
    }

    public Edge newEdge(String startNodeId, String endNodeId) {
        final Edge edge = new Edge(startNodeId, endNodeId);

        edges.add(edge);

        return edge;
    }

    /**
     * @return the clusters
     */
    public Collection<Graph> getClusters() {
        return Collections.unmodifiableCollection(clusters);
    }

    /**
     * @return the nodes
     */
    public Collection<Node> getNodes() {
        return Collections.unmodifiableCollection(nodes);
    }

    /**
     * @return the edges
     */
    public Collection<Edge> getEdges() {
        return Collections.unmodifiableCollection(edges);
    }

    /**
     * @return the clusterIdFactory
     */
    private IdFactory getClusterIdFactory() {
        return clusterIdFactory;
    }

    /**
     * @return the nodeIdFactory
     */
    private IdFactory getNodeIdFactory() {
        return nodeIdFactory;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @return the edgeAttributes
     */
    public HasAttributes getEdgeAttributes() {
        return edgeAttributes;
    }

    /**
     * @return the nodeAttributes
     */
    public HasAttributes getNodeAttributes() {
        return nodeAttributes;
    }

    protected static final class IdFactory {
        /**
         * counter for ids.
         */
        private long currentId;

        /**
         * Returns the current id and increments the internal id counter.
         *
         * @return next id value.
         */
        synchronized String nextId() {
            return Long.toString(currentId++);
        }
    }
}
