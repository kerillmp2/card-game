package core.player;

import java.util.Map;
import java.util.TreeMap;

public class ResourcePool {
    private Map<Resource, Integer> resources = new TreeMap<>();

    public boolean takeResource(Resource resource, Integer amount) {
        if(this.getResource(resource) < amount) {
            return false;
        }
        this.resources.put(resource, this.resources.getOrDefault(resource, 0) - Math.max(amount, 0));
        return true;
    }

    public boolean haveResource(Resource resource, Integer amount) {
        return this.resources.getOrDefault(resource, 0) >= amount;
    }

    public ResourcePool addResource(Resource resource, Integer amount) {
        this.resources.put(resource, this.resources.getOrDefault(resource, 0) + Math.max(amount, 0));
        return this;
    }

    public ResourcePool setResource(Resource resource, Integer amount) {
        this.resources.put(resource, Math.max(amount, 0));
        return this;
    }

    public int getResource(Resource resource) {
        return this.resources.getOrDefault(resource, 0);
    }

    public Map<Resource, Integer> getResources() {
        return resources;
    }

    public void setResources(Map<Resource, Integer> resources) {
        this.resources = resources;
    }
}
