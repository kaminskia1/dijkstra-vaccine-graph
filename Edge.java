/* ********************************************************************

    This class or object implements a skeleton for edges, which are
    used to link vertices to one another, which can be found in the
    vertex class. This class contains memory for a weight for the
    'line', or edge, a pointer to the starting vertex, and a pointer
    to the ending vertex allowing them to link bidirectionally. There
    is a method included for returning the opposite of the starting
    vertex as well, with said vertex's identifier as a prerequisite.

******************************************************************** */

public class Edge {

    // Weight of the edge
    public int weight;

    // First vertex in the link
    public Vertex first;

    // Second vertex in the link
    public Vertex second;

    /**
     * Create an edge from two vertices and a weight
     *
     * @param first  First vertex in the link
     * @param second Second vertex in the link
     * @param weight Weight / Distance of the edge
     */
    public Edge(Vertex first, Vertex second, int weight) {
        this.first = first;
        this.second = second;
        this.weight = weight;
    }

    /**
     * Get the first vertex
     *
     * @return Vertex First vertex in the link
     */
    public Vertex getFirst() {
        return this.first;
    }

    /**
     * Get the second vertex
     *
     * @return Vertex Second vertex in the link
     */
    public Vertex getSecond() {
        return this.second;
    }

    /**
     * Get the opposite Vertex of the one provided
     *
     * @param id Starting Vertex's id
     * @return Vertex Opposite vertex in the link
     */
    public Vertex getOpposite(int id) {
        // If provided is first, return second
        if (first.getId() == id) return second;

        // Vice-versa
        if (second.getId() == id) return first;

        // Invalid id, return nothing
        return null;
    }

    /**
     * Get the edge's weight
     *
     * @return int Weight of the edge
     */
    public int getWeight() {
        return this.weight;
    }

}
