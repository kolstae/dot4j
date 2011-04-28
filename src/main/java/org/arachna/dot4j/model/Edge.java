/**
 *
 */
package org.arachna.dot4j.model;

/**
 * @author Dirk Weigenand
 */
public final class Edge extends Attributes {
    private final String startNodeId;
    private final String endNodeId;

    Edge(String startNodeId, String endNodeId) {
        if (startNodeId == null || endNodeId == null) {
            throw new IllegalArgumentException("start and end node must not be null!");
        }

        this.startNodeId = startNodeId;
        this.endNodeId = endNodeId;
    }

    public String getStartNodeId() {
        return startNodeId;
    }

    public String getEndId() {
        return endNodeId;
    }
}
