import java.util.*;

class Graph {
    private Set<String> nodes = new HashSet<>();
    private Map<String, List<Edge>> edges = new HashMap<>();

    private static class Edge {
        String toNode;
        int weight;

        Edge(String toNode, int weight) {
            this.toNode = toNode;
            this.weight = weight;
        }
    }

    public void addEdge(String fromNode, String toNode, int weight) {
        nodes.add(fromNode);
        nodes.add(toNode);
        edges.putIfAbsent(fromNode, new ArrayList<>());
        edges.get(fromNode).add(new Edge(toNode, weight));
    }

    public Map<String, Integer> dijkstra(String start) {
        PriorityQueue<NodeDistance> queue = new PriorityQueue<>(Comparator.comparingInt(nd -> nd.distance));
        Map<String, Integer> distances = new HashMap<>();
        for (String node : nodes) {
            distances.put(node, Integer.MAX_VALUE);
        }
        distances.put(start, 0);
        queue.add(new NodeDistance(start, 0));

        while (!queue.isEmpty()) {
            NodeDistance current = queue.poll();
            String currentNode = current.node;

            if (current.distance > distances.get(currentNode)) {
                continue;
            }

            List<Edge> neighbors = edges.getOrDefault(currentNode, new ArrayList<>());
            for (Edge edge : neighbors) {
                int newDist = distances.get(currentNode) + edge.weight;
                if (newDist < distances.get(edge.toNode)) {
                    distances.put(edge.toNode, newDist);
                    queue.add(new NodeDistance(edge.toNode, newDist));
                }
            }
        }

        return distances;
    }

    private static class NodeDistance {
        String node;
        int distance;

        NodeDistance(String node, int distance) {
            this.node = node;
            this.distance = distance;
        }
    }

    public static void main(String[] args) {
        Graph graph = new Graph();
        graph.addEdge("A", "B", 1);
        graph.addEdge("A", "C", 4);
        graph.addEdge("B", "C", 2);
        graph.addEdge("B", "D", 5);
        graph.addEdge("C", "D", 1);

        Map<String, Integer> distancesFromA = graph.dijkstra("A");
        System.out.println("Distances from A: " + distancesFromA);
    }
}
