/**
 *
 */
package org.arachna.dot4j.model;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Dirk Weigenand
 */
public final class Attributes implements HasAttributes {
    /**
     *
     */
    private final Map<String, String> attributes = new LinkedHashMap<String, String>();

    public Attributes setAttribute(String key, String value) {
        attributes.put(key, '"' + value + '"');
        return this;
    }

    public Attributes setIDAttribute(String key, String id) {
        attributes.put(key, id);
        return this;
    }

    public Attributes setAttribute(String key, int value) {
        attributes.put(key, Integer.toString(value));
        return this;
    }

    public Attributes setAttribute(String key, long value) {
        attributes.put(key, Long.toString(value));
        return this;
    }

    public Attributes setAttribute(String key, float value) {
        attributes.put(key, Float.toString(value));
        return this;
    }

    public Attributes setAttribute(String key, double value) {
        attributes.put(key, Double.toString(value));
        return this;
    }

    public boolean isEmpty() {
        return attributes.isEmpty();
    }

    public Iterator<Map.Entry<String,String>> iterator() {
        return attributes.entrySet().iterator();
    }
}
