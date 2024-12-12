import java.util.HashMap;
import java.util.Map;


class DistanceVectorSimulation {
    public static void simulateDistanceVector(Node[] nodes) {
        while (true) {
            boolean changes = false;
            for (Node node : nodes) {
                for (Map.Entry<String, Integer> entry : node.neighbors.entrySet()) {
                    String neighborName = entry.getKey();
                    Node neighborNode = findNodeByName(nodes, neighborName);
                    if (neighborNode != null) {
                        boolean updated = node.receiveVector(neighborName, neighborNode.sendVector());
                        if (updated) {
                            changes = true;
                        }
                    }
                }
            }
            if (!changes) {
                break;
            }
        }
    }

    public static Node findNodeByName(Node[] nodes, String name) {
        for (Node node : nodes) {
            if (node.name.equals(name)) {
                return node;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        Node nodeA = new Node("A", Map.of("B", 1, "C", 4));
        Node nodeB = new Node("B", Map.of("A", 1, "C", 2, "D", 5));
        Node nodeC = new Node("C", Map.of("A", 4, "B", 2, "D", 1));
        Node nodeD = new Node("D", Map.of("B", 5, "C", 1));

        Node[] nodes = {nodeA, nodeB, nodeC, nodeD};

        simulateDistanceVector(nodes);

        for (Node node : nodes) {
            System.out.println("Node " + node.name + " distances: " + node.distances);
        }
    }

    static class Node {
        String name;
        Map<String, Integer> distances;
        Map<String, Integer> neighbors;

        public Node(String name, Map<String, Integer> neighbors) {
            this.name = name;
            this.distances = new HashMap<>();
            this.distances.put(name, 0);
            this.neighbors = new HashMap<>(neighbors);
        }

        public boolean receiveVector(String neighborName, Map<String, Integer> neighborVector) {
            boolean updated = false;
            Integer neighborDistance = distances.get(neighborName);
            if (neighborDistance == null) {
                neighborDistance = Integer.MAX_VALUE;
            }
            for (Map.Entry<String, Integer> entry : neighborVector.entrySet()) {
                String node = entry.getKey();
                int distance = entry.getValue();
                Integer currentDistance = distances.get(node);
                if (currentDistance == null || currentDistance > neighborDistance + distance) {
                    distances.put(node, neighborDistance + distance);
                    updated = true;
                }
            }
            return updated;
        }

        public Map<String, Integer> sendVector() {
            return new HashMap<>(distances);
        }
    }
}
