/* ********************************************************************

    The main or "driver" class for this program. This links everything
    up together and serves as a starting point as well as a way to
    utilize the algorithm included in this project. Everything not saved
    as a class but more as a script is included here in the main
    function.

******************************************************************** */

import java.util.Scanner;

public class Main {

    /**
     * Main entrypoint / driver for this program.
     *
     * @param args
     */
    public static void main(String[] args)
    {
        // Create a graph out of the try catch scope
        Graph g = null;
        Scanner s = new Scanner(System.in);

        // Loop until valid file provided
        boolean loop = true;
        while(loop)
        {
            // Try-catch to handle invalid file exception
            try{
                // Prompt user
                System.out.print("Enter graph file name: ");
                String file = s.nextLine();

                // Attempt to create graph from provided file
                g = new Graph(file);

                // If this point is reach, graph creation was successful
                loop = false;
            } catch(Exception e)
            {
                // Invalid file provided, output failure and prompt again
                System.out.println("Invalid file name!");
            }
        }

        // Create instance of the algo and run it
        Dijkstra d = new Dijkstra(g, 0);

        // Iterate through all of the calculated paths
        for (int i = 1; i<g.getVertices().size(); i++)
        {
            // "Vertex {x} distance from 0: {y}"
            System.out.print("Vertex " + d.getVertex(i).getId() + " distance from 0: " + d.getVertex(i).getDistance() + "\n");
        }

    }

}
