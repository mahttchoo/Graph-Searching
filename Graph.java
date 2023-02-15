
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;

import java.util.Queue;
import java.util.LinkedList;

public class Graph {
    int[][] adjMat;
    char[] vertexes;

    Graph(int[][] a, char[] v) throws Exception {
        // Testing if matrix is squared
        for (int i = 0; i < a.length; i++) {
            if (a[i].length != a.length) {
                throw new Exception("\nError: Adjacency Matrix not square\nRow " + i + " doesn't match.");
            }
        }
        if (v.length != a.length) {
            throw new Exception("\nError: Vertex count is off.");
        }
        adjMat = a;
        vertexes = v;
    }

    // Constructor from .csv file
    Graph(String path) throws Exception {
        int v = 0;
        String line = "";
        File file = new File(path);
        Queue<String[]> data = new LinkedList<>();

        try {
            // BufferedReader reads the .csv a line at a time and gives a string, then the string is turned into an array, then this array is put into the matrix.
            BufferedReader br = new BufferedReader(new FileReader(file));
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                data.add(values);

                // Testing if matrix is squared
                // Tests if each array being inputted in the matrix matches in length
                if (values.length != data.peek().length) {
                    br.close();
                    throw new Exception("\nError: Adjacency Matrix not squared.\nDetected on Row " + v + ".");
                }
                v++;
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Testing if matrix is squared
        // v is the number of rows. Since all arrays are the same length (have the same number of columns), v must be equal to the number of columns.
        if (v != data.peek().length) {
            throw new Exception("\nError: Adjacency Matrix not squared. Number of rows and columns do not match. Check the .csv for spaces, it should only contain 1s, 0s, and commas.");
        }

        // Inputting the data from the .csv into the Graph variables.
        int[][] am = new int[v][v];
        char[] vs = new char[v];
        for (int row = 0; row < v; row++) {
            String[] s = data.remove();
            for (int col = 0; col < v; col++) {
                int n = (int)(s[col].charAt(0)) - 48; // Finding the char value of the 1 character string, then casting it as int, then subtracting 48 so it's its real value
                am[row][col] = n;
                vs[row] = (char)(row + 65); // A, B, C... etc
            }
        }
        adjMat = am;
        vertexes = vs;
    }

    char get(int i) {
        return vertexes[i];
    }

    int getSize() {
        return adjMat.length;
    }

    void printEdges() {
        int l = adjMat.length;

        // Printing list of vertexes on the top
        System.out.print("  ");
        for (int i = 0; i < l; i++) {
            System.out.print(vertexes[i] + " ");
        }
        System.out.println();

        // Printing actual data of adjacency matrix
        for (int row = 0; row < l; row++) {
            System.out.print(vertexes[row] + " ");
            for (int col = 0; col < l; col++) {
                System.out.print(adjMat[row][col] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
