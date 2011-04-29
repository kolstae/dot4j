/**
 *
 */
package org.arachna.dot4j.model;

import java.util.Iterator;
import java.util.Map;

/**
 * @author Dirk Weigenand
 */
public final class Edge implements HasAttributes {
    private final String startNodeId;
    private final String endNodeId;
    private final Attributes attributes = new Attributes();

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

    public Edge setAttribute(String key, String value) {
        attributes.setAttribute(key, value);
        return this;
    }

    public Edge setIDAttribute(String key, String id) {
        attributes.setIDAttribute(key, id);
        return this;
    }

    public Edge setAttribute(String key, int value) {
        attributes.setAttribute(key, value);
        return this;
    }

    public Edge setAttribute(String key, long value) {
        attributes.setAttribute(key, value);
        return this;
    }

    public Edge setAttribute(String key, float value) {
        attributes.setAttribute(key, value);
        return this;
    }

    public Edge setAttribute(String key, double value) {
        attributes.setAttribute(key, value);
        return this;
    }

    public boolean isEmpty() {
        return attributes.isEmpty();
    }

    public Iterator<Map.Entry<String, String>> iterator() {
        return attributes.iterator();
    }
}
