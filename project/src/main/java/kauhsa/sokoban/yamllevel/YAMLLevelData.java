/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kauhsa.sokoban.yamllevel;

import java.util.Map;

/**
 *
 * @author mika
 */
class YAMLLevelData {
    private Map<String, String> metadata;
    private String world;

    public String getMetadata(String key) {
        return metadata.get(key);
    }

    public String getWorldString() {
        return world;
    }

    public void setMetadata(Map<String, String> metadata) {
        this.metadata = metadata;
    }

    public void setWorld(String world) {
        this.world = world;
    }
}
