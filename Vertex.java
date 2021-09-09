/* ********************************************************************

    This class / object layout acts as a vertex for implementing the
    graph datatype. It contains an id, list for attached edges, alongside
    a distance and route for use with the algorithm itself. It contains
    basic getters/setters to allow for basic manipulation. Edges can be
    used to link vertexes to one another in an adjacency-list format.

******************************************************************** */

import java.util.ArrayList;
import java.util.Stack;

public class Vertex {

    // Id / name of the vertex
    private int id;

    // List of all connected edges
    private ArrayList<Edge> edges;

    // Current distance, used in algo calculation
    private int distance;

    // Current route for said node from the base one, used in algo calculation
    private Stack<Vertex> route;


    /**
     * Construct a vertex from an id / name
     *
     * @param id Id of the Vertex
     */
    public Vertex(int id) {
        // Create a blank list for the edges
        edges = new ArrayList<Edge>();

        // Set id to provided id
        this.id = id;

        // Create a blank list for the route
        this.route = new Stack<Vertex>();

        // Set the default distance to integer max
        this.distance = Integer.MAX_VALUE;
    }

    /**
     * Add an edge to the vertex
     *
     * @param e Edge to add
     */
    public void add(Edge e) {
        this.edges.add(e);
    }

    /**
     * Get all vertices connected to this vertex
     *
     * @return ArrayList<Vertex> All connected vertices
     */
    public ArrayList<Vertex> getConnectedNodes() {
        // Create a blank list
        ArrayList<Vertex> tmp = new ArrayList<Vertex>();

        // Cycle through every edge
        for (int i = 0; i < this.getEdges().size(); i++) {
            // Add every connected vertex to the temporary list
            tmp.add(this.getEdges().get(i).getOpposite(this.getId()));
        }

        // Return the temporary list
        return tmp;
    }

    /**
     * Get the vertex's id
     *
     * @return int Id of the vertex
     */
    public int getId() {
        return this.id;
    }

    /**
     * Get all edges attached to this vertex
     *
     * @return ArrayList<Edge> All attached edges
     */
    public ArrayList<Edge> getEdges() {
        return edges;
    }

    /**
     * Get the distance attached to this vertex
     *
     * @return int The distance attached
     */
    public int getDistance() {
        return distance;
    }

    /**
     * Set the vertex's distance
     *
     * @param distance Distance to set
     */
    public void setDistance(int distance) {
        this.distance = distance;
    }

    /**
     * Get the vertex's route
     *
     * @return Stack<Vertex> Route attached
     */
    public Stack<Vertex> getRoute() {
        return route;
    }

    /**
     * Set the vertex's route
     *
     * @param route Route to be set
     */
    public void setRoute(Stack<Vertex> route) {
        this.route = route;
    }

    /**
     * Push an item to the route stack
     *
     * @param v Vertex to be pushed
     */
    public void pushStack(Vertex v) {
        route.push(v);
    }

    /**
     * Pop an item from the route
     *
     * @return Vertex Item that has been popped
     */
    public Vertex popStack() {
        return route.pop();
    }

}
