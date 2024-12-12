import java.util.*;

class NodePath {
    String name;
    Map<String, List<String>> paths;
    Map<String, Integer> neighbors;

    public NodePath(String name, Map<String, Integer> neighbors) {
        this.name = name;
        this.paths = new HashMap<>();
        this.paths.put(name, new ArrayList<>(Collections.singletonList(name)));
        this.neighbors = new HashMap<>(neighbors);
    }

    public boolean receivePathVector(String neighborName, Map<String, List<String>> neighborPaths) {
        boolean updated = false;
        List<String> neighborPath = paths.get(neighborName);
        if (neighborPath == null) {
            neighborPath = new ArrayList<>();
        }
        for (Map.Entry<String, List<String>> entry : neighborPaths.entrySet()) {
            String node = entry.getKey();
            List<String> path = entry.getValue();
            if (!paths.containsKey(node) || paths.get(node).size() > neighborPath.size() + path.size()) {
                List<String> newPath = new ArrayList<>();
                newPath.add(name);
                newPath.addAll(path);
                paths.put(node, newPath);
                updated = true;
            }
        }
        return updated;
    }

    public Map<String, List<String>> sendPathVector() {
        Map<String, List<String>> copiedPaths = new HashMap<>();
        for (Map.Entry<String, List<String>> entry : paths.entrySet()) {
            copiedPaths.put(entry.getKey(), new ArrayList<>(entry.getValue()));
        }
        return copiedPaths;
    }
}

class PathVectorSimulation {
    public static void simulatePathVector(List<NodePath> nodes) {
        boolean changes;
        do {
            changes = false;
            for (NodePath node : nodes) {
                for (String neighborName : node.neighbors.keySet()) {
                    NodePath neighborNode = nodes.stream().filter(n -> n.name.equals(neighborName)).findFirst().orElse(null);
                    if (neighborNode != null) {
                        boolean updated = node.receivePathVector(neighborName, neighborNode.sendPathVector());
                        if (updated) {
                            changes = true;
                        }
                    }
                }
            }
        } while (changes);
    }

    public static void main(String[] args) {
        List<NodePath> nodes = Arrays.asList(
                new NodePath("A", Map.of("B", 1, "C", 4)),
                new NodePath("B", Map.of("A", 1, "C", 2, "D", 5)),
                new NodePath("C", Map.of("A", 4, "B", 2, "D", 1)),
                new NodePath("D", Map.of("B", 5, "C", 1))
        );

        simulatePathVector(nodes);

        for (NodePath node : nodes) {
            System.out.println("Node " + node.name + " paths: " + node.paths);
        }
    }
}
