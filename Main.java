import java.util.Queue;
import java.util.Stack;
import java.util.LinkedList;
import java.util.Arrays;

public class Main {
    public static void main(String args[]) throws Exception {
        Graph graph = new Graph("graph3.csv");
        graph.printEdges();
        BFS(graph, 0);
        DFS(graph,0);
    }


    static void DFS(Graph g, int s) {
        int n = g.getSize();
        Boolean[] visited = new Boolean[n];
        Arrays.fill(visited, false);
        Stack<Integer> stack = new Stack<Integer>();
        String vertexTraversalInt = "";

        stack.push(s);

        System.out.println("Depth First Search:");
        while (!stack.isEmpty()) {
            int v = stack.pop();
            if (visited[v]) { // Ignore the rest of the code if v was already used in part of another path
                continue;
            }
            visited[v] = true;
            System.out.print((char)(v + 65) + " ");
            vertexTraversalInt = vertexTraversalInt + (v + 1) + " ";

            // For all unvisited neighbors of v, add to stack from largest to smallest
            for (int i = (n - 1); i >= 0; i--) {
                if (g.adjMat[v][i] == 1) {
                    if (visited[i] == false) {
                        stack.push(i);
                    }
                }
            }
        }
        System.out.println("\n" + vertexTraversalInt + "\n");
    }

    static void BFS(Graph g, int s) {
        int n = g.getSize();
        Boolean[] visited = new Boolean[n];
        Arrays.fill(visited, false);
        LinkedList<Edge> list = new LinkedList<Edge>();
        Queue<Integer> q = new LinkedList<>();
        String vertexTraversalInt = "";

        q.add(s);
        visited[s] = true;

        System.out.println("Breadth First Search Vertices Traversal Order:");
        while (!q.isEmpty()) {
            int v = q.remove();
            System.out.print((char)(v + 65) + " ");
            vertexTraversalInt = vertexTraversalInt + (v + 1) + " ";

            // For all unvisited neighbors of v, add to queue, mark as visited
            for (int i = 0; i < n; i++) {
                if (g.adjMat[v][i] == 1) {
                    if (visited[i] == false) {
                        Edge edge = new Edge(g.get(v), g.get(i));
                        list.add(edge);
                        q.add(i);
                        visited[i] = true;
                    }
                }
            }
        }
        System.out.println("\n" + vertexTraversalInt + "\n");

        System.out.println("Breadth First Search Edges Traversal Order:");
        for (int i = 0; i < list.size(); i++) {
            list.get(i).print();
        }
        System.out.println("\n");
    }
}

// SOURCES USED:
// https://graphonline.ru/en/
// https://rollbar.com/guides/java/how-to-throw-exceptions-in-java/
// https://www.geeksforgeeks.org/multidimensional-arrays-in-java/
// https://www.geeksforgeeks.org/queue-interface-java/
// https://www.youtube.com/watch?v=-Aud0cDh-J8&ab_channel=AlexLee