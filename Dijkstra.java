/* ********************************************************************

    This class implements Dijkstra's algorithm for finding the shortest
    possible route from a target vertex to the one's around it,
    alongside the cost associated with such. It uses a binary heap /
    priority queue for storing unsettled vertices, and orders them by
    their current distance to the source node. Implementation and
    execution only requires instancing the object, as it is automatic
    and done upon creation. Settled vertices are then ordered by the
    index / id associated with itself.

******************************************************************** */

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Dijkstra {

    // The attached graph
    private final Graph baseGraph;

    // All settled vertices
    private final ArrayList<Vertex> settledVertices;

    // All attached edges
    private final ArrayList<Edge> edges;

    // All unsettled vertices
    private PriorityQueue<Vertex> unsettledVertices;

    /**
     * Construct an instance from a graph and a source vertex index
     *
     * @param graph  Graph to use
     * @param source Source vertex index to use
     */
    public Dijkstra(Graph graph, int source) {
        // Set the graph and edges
        this.baseGraph = graph;
        this.edges = graph.getEdges();

        // Instance the settled vertices to a blank list with size of all total vertices
        this.settledVertices = new ArrayList<Vertex>(graph.getVertices().size());

        // Create a comparator to use for the priorityqueue / unsettled list
        Comparator<Vertex> cmp = new Comparator<Vertex>() {
            @Override
            public int compare(Vertex v1, Vertex v2) {
                // Return comparison of distance with vertex 1 and 2, returns -1,0,1
                return Integer.compare(v1.getDistance(), v2.getDistance());
            }
        };

        // Instance the unsettled vertices with size of all vertices, and comparator above
        this.unsettledVertices = new PriorityQueue<Vertex>(graph.getVertices().size(), cmp);

        // Grab source vertex
        Vertex sourceVertex = baseGraph.getVertices().get(source);

        // Set distance to zero, push itself to it's own route, and add it into the priorityqueue
        sourceVertex.setDistance(0);
        sourceVertex.pushStack(sourceVertex);
        this.unsettledVertices.add(sourceVertex);

        // Add all other vertices into the unsettled queue
        ArrayList<Vertex> vert = graph.getVertices();
        for (int i = 0; i < vert.size(); i++) {
            // If not source vertex
            if (i != source) {
                // Add onto the queue
                this.unsettledVertices.add(vert.get(i));
            }
        }

        // Begin the next steps
        this.loadShortestPaths(source);
    }


    /**
     * Load the shortest path for all nodes from a provided node index
     *
     * @param s Vertex index to base
     */
    public void loadShortestPaths(int s) {
        // While unsettled vertices exist, loop
        while (settledVertices.size() != baseGraph.getVertices().size()) {
            // Grab the highest priority vertex in the queue and pop it off
            Vertex current = this.unsettledVertices.remove();
            // Add the current to settled
            this.settledVertices.add(current);

            // Settle the current node
            settle(current);

            // Rebalance the priority queue to factor in distance changes
            this.unsettledVertices = rebalance(this.unsettledVertices);
        }

        // Create a comparator for vertices based off their id
        Comparator<Vertex> cmp = new Comparator<Vertex>() {
            @Override
            public int compare(Vertex o1, Vertex o2) {
                // Compare id to id, returns -1,0,1
                return Integer.compare(o1.getId(), o2.getId());
            }
        };
        // Sort the vertices with the custom comparator
        settledVertices.sort(cmp);
    }

    /**
     * Settle a provided vertex
     *
     * @param current Vertex to settle
     */
    private void settle(Vertex current) {
        // Grab and save all vertices connected to the current one to a lost
        ArrayList<Vertex> connected = current.getConnectedNodes();

        // Iterate through all connected vertices
        for (int i = 0; i < connected.size(); i++) {
            // Grab the current edge for the selected sub-vertex
            Edge currentBar = current.getEdges().get(i);

            // If distance + weight is shorter than the current distance, update the node's distance
            if (current.getDistance() + currentBar.getWeight() < currentBar.getOpposite(current.getId()).getDistance()
                    && current.getDistance() + currentBar.getWeight() > 0) {

                // Update the distance to the newly-calculated shorter one
                currentBar.getOpposite(current.getId()).setDistance(current.getDistance() + currentBar.getWeight());

            }
        }
    }


    public Vertex getVertex(int vertex) {
        return settledVertices.get(vertex);
    }

    private PriorityQueue<Vertex> rebalance(PriorityQueue<Vertex> old) {
        Comparator<Vertex> cmp = new Comparator<Vertex>() {
            @Override
            public int compare(Vertex v1, Vertex v2) {
                return Integer.compare(v1.getDistance(), v2.getDistance());
            }
        };
        PriorityQueue<Vertex> fresh = new PriorityQueue<Vertex>(cmp);

        while (old.size() != 0) {
            fresh.add(old.poll());
        }
        return fresh;
    }
}
