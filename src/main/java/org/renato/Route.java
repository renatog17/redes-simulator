package org.renato;

public class Route {

    private final String destination;
    private final Node nextHop;
    private final int cost;

    public Route(String destination, Node nextHop, int cost) {
        this.destination = destination;
        this.nextHop = nextHop;
        this.cost = cost;
    }

    public String getDestination() {
        return destination;
    }

    public Node getNextHop() {
        return nextHop;
    }

    public int getCost() {
        return cost;
    }
}