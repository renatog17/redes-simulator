package org.renato;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Node {

    private final String id;
    private final String ip;

    private final List<Node> neighbors = new ArrayList<>();
    private final RoutingTable routingTable = new RoutingTable();

    public Node(String id, String ip) {
        this.id = id;
        this.ip = ip;

        // rota para si mesmo
        routingTable.updateRoute(ip, this, 0);
    }

    public String getId() {
        return id;
    }

    public String getIp() {
        return ip;
    }

    public void addNeighbor(Node node) {
        neighbors.add(node);

        // rota direta
        routingTable.updateRoute(node.getIp(), node, 1);
    }

    // =============================
    // DATA PLANE
    // =============================
    public void send(Packet packet) {
        Route route = routingTable.lookup(packet.getIpDestination());

        if (route == null) {
            System.out.println(id + " does not know route to " + packet.getIpDestination());
            return;
        }

        route.getNextHop().receive(packet, this);
    }

    public void receive(Packet packet, Node from) {
        if (this.ip.equals(packet.getIpDestination())) {
            System.out.println(id + " received: " + packet.getPayload());
            return;
        }

        send(packet);
    }

    // =============================
    // CONTROL PLANE (RIP)
    // =============================

    public void sendRoutingTable() {
        for (Node neighbor : neighbors) {
            neighbor.receiveRoutingTable(this, routingTable.getAll());
        }
    }

    public void receiveRoutingTable(Node from, Map<String, Route> routes) {

        for (Route route : routes.values()) {

            String destination = route.getDestination();

            // custo via vizinho
            int newCost = route.getCost() + 1;

            routingTable.updateRoute(destination, from, newCost);
        }
    }

    public void printRoutingTable() {
        System.out.println("Routing table of " + id);

        for (Route r : routingTable.getAll().values()) {
            System.out.println(
                    "dest=" + r.getDestination() +
                            " via=" + r.getNextHop().getId() +
                            " cost=" + r.getCost()
            );
        }

        System.out.println();
    }
}