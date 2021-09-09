/* ********************************************************************

    This class acts as a layer of abstraction for both the Vertex and
    Edge class, in turn forming a usable and interactable graph datatype
    featuring methods for basic manipulation. This utilizes a list to
    hold both vertices and edges.

******************************************************************** */

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Graph {

    // Arraylist of all the vertices
    private ArrayList<Vertex> vertices;

    // Arraylist of all the edges
    private ArrayList<Edge> edges;


    /**
     * Establish a blank graph of a predesignated size
     *
     * @param size Number of vertices to be added
     */
    public Graph(int size) {
        this.vertices = new ArrayList<Vertex>(size);
        for (int i = 0; i < size; i++) {
            this.vertices.add(new Vertex(i));
        }
        this.edges = new ArrayList<Edge>();
    }

    /**
     * Establish a graph from a set of vertices
     *
     * @param vertices Vertices to be added
     */
    public Graph(ArrayList<Vertex> vertices) {
        this.vertices = vertices;
        this.edges = new ArrayList<Edge>();
    }

    /**
     * Establish a graph from a file name
     *
     * @param fileName File name to be loaded and implemented
     * @throws Exception Provided filename does not exist
     */
    public Graph(String fileName) throws Exception {
        // File load func from canvas
        File file = new File(fileName);
        Scanner reader, tokenizer;
        String inputLine;
        reader = new Scanner(file);
        inputLine = reader.nextLine();
        tokenizer = new Scanner(inputLine);
        int size = Integer.parseInt(tokenizer.next());
        vertices = new ArrayList<Vertex>(Arrays.asList(new Vertex[size]));
        edges = new ArrayList<Edge>();

        // Add n blank nodes with their index as their id
        for (int i = 0; i < size; i++) {
            vertices.set(i, new Vertex(i));
        }

        // Begin loading in the edges
        int start, end;
        while (reader.hasNextLine()) {
            inputLine = reader.nextLine();
            tokenizer = new Scanner(inputLine);
            // Syntax: <start>,<end>,<weight>/n<start>,<end>,<weight>
            addEdge(
                    /* Start */ Integer.parseInt(tokenizer.next()),
                    /* end */ Integer.parseInt(tokenizer.next()),
                    /* weight */ Integer.parseInt(tokenizer.next())
            );
        }
    }

    /**
     * Add an edge to the graph from two node's indexes and a predesignated weight
     *
     * @param start  The sending vertex's index
     * @param end    The receiving vertex's index
     * @param weight The 'distance', or weight of the edge
     */
    public void addEdge(int start, int end, int weight) {
        // Create an edge
        Edge e = new Edge(vertices.get(start), vertices.get(end), weight);

        // Call the secondary addEdge with the new edge
        addEdge(e);
    }

    /**
     * Add an edge to the graph from a precreated edge
     *
     * @param e Edge to be added
     */
    public void addEdge(Edge e) {
        // Add the edge to the edge array
        this.edges.add(e);

        // Add edge reference to both sets, many-to-many relationship vibe
        vertices.get(e.getFirst().getId()).add(e);
        vertices.get(e.getSecond().getId()).add(e);
    }

    /**
     * Get the graph's vertices
     *
     * @return ArrayList<Vertex> The graph's vertices
     */
    public ArrayList<Vertex> getVertices() {
        return this.vertices;
    }

    /**
     * Get the graph's edges
     *
     * @return ArrayList<Edge> The graph's edges
     */
    public ArrayList<Edge> getEdges() {
        return this.edges;
    }


}
