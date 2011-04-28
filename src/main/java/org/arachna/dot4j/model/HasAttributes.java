package org.arachna.dot4j.model;

import java.util.Map;

public interface HasAttributes extends Iterable<Map.Entry<String,String>> {
    HasAttributes setAttribute(String key, String value);

    HasAttributes setIDAttribute(String key, String id);

    HasAttributes setAttribute(String key, int value);

    HasAttributes setAttribute(String key, long value);

    HasAttributes setAttribute(String key, float value);

    HasAttributes setAttribute(String key, double value);

    boolean isEmpty();
}
