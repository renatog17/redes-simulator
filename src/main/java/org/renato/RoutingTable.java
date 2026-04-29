package org.renato;

import java.util.HashMap;
import java.util.Map;

public class RoutingTable {

    private final Map<String, Route> routes = new HashMap<>();

    public void updateRoute(String destination, Node nextHop, int cost) {
        Route existing = routes.get(destination);

        if (existing == null || cost < existing.getCost()) {
            routes.put(destination, new Route(destination, nextHop, cost));
        }
    }

    public Route lookup(String destination) {
        return routes.get(destination);
    }

    public Map<String, Route> getAll() {
        return routes;
    }
}