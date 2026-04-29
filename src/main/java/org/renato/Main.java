package org.renato;

public class Main {

    public static void main(String[] args) {

        Node a = new Node("A", "1");
        Node b = new Node("B", "2");
        Node c = new Node("C", "3");

        a.addNeighbor(b);
        b.addNeighbor(a);

        b.addNeighbor(c);
        c.addNeighbor(b);

        // simula RIP
        for (int i = 0; i < 3; i++) {
            a.sendRoutingTable();
            b.sendRoutingTable();
            c.sendRoutingTable();
        }

        a.printRoutingTable();
        b.printRoutingTable();
        c.printRoutingTable();

        a.send(new Packet("Hello C", "3", "1"));
    }
}