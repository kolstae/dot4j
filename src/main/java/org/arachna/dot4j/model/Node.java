/**
 *
 */
package org.arachna.dot4j.model;

/**
 * @author Dirk Weigenand
 */
public final class Node extends Attributes {
    /**
     * node Id.
     */
    private final String id;

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
}
