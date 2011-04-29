/**
 *
 */
package org.arachna.dot4j.model;

import java.util.Iterator;
import java.util.Map;

/**
 * @author Dirk Weigenand
 */
public final class Node implements HasAttributes {
    /**
     * node Id.
     */
    private final String id;
    private final Attributes attributes = new Attributes();

    /**
     * Create a new node with the given id.
     *
     * @param id
     *            node Id.
     */
    Node(final String id) {
        this.id = id;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    public Node setAttribute(String key, String value) {
        attributes.setAttribute(key, value);
        return this;
    }

    public Node setIDAttribute(String key, String id) {
        attributes.setIDAttribute(key, id);
        return this;
    }

    public Node setAttribute(String key, int value) {
        attributes.setAttribute(key, value);
        return this;
    }

    public Node setAttribute(String key, long value) {
        attributes.setAttribute(key, value);
        return this;
    }

    public Node setAttribute(String key, float value) {
        attributes.setAttribute(key, value);
        return this;
    }

    public Node setAttribute(String key, double value) {
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
