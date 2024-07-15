package gol;

/**
 * La clase representa un simulador del juego, implementa la interfaz GolGenerator
 * Utiliza una matriz de enteros para la representacion de la poblacion actual
 */
public class Simulation implements GolGenerator{

    private final int[][] grid;

    /**
     * Constructor de class Simulation
     * @param grid representa una mtriz de enteros que es la poblacion inicial del juego
     */
    public Simulation(int[][] grid) {
        this.grid = grid;
    }

    /**
     * Metodo que implementa las reglas del juego para la siguiente generacion
     * @param matrix una matriz de enteros que representa la poblacion actual del juego
     */
    private void golNextGeneration(int[][] matrix) {
        int[][] nextGen = new int[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                int neighbors = countNeighbors(matrix,i,j);
                //Reglas
                if (matrix[i][j] == 1) {
                    if (neighbors < 2 || neighbors > 3) {
                        nextGen[i][j] = 0;
                    } else {
                        nextGen[i][j] = 1;
                    }
                }else {
                    if (neighbors == 3){
                        nextGen[i][j] = 1;
                    }
                }
            }
        }

        for (int i = 0; i < matrix.length; i++){
            System.arraycopy(nextGen[i],0,matrix[i],0,matrix[i].length);
        }
    }

    /**
     * Este metodo cuenta el numero de vecinos vivos de una celula dada
     * @param matrix una matriz de enteros que representa la poblacion
     * @param row la fila de la celula
     * @param col la columna de la celula
     * @return el numero de vecinos vivos de la celula
     */
    private int countNeighbors(int[][] matrix,int row,int col) {
        int count = 0;
        int numRows = matrix.length;
        int numCols = matrix[0].length;

        for (int i = -1; i <= 1;i++){
            for (int j = -1; j<= 1; j++){
                int neighborRow = row + i;
                int neighborCol = col + j;

                if(i==0 && j==0){
                    continue;
                }
                if (neighborRow >= 0 && neighborRow < numRows && neighborCol >= 0 && neighborCol <numCols){
                    count += matrix[neighborRow][neighborCol];
                }
            }
        }
        return count;
    }

    /**
     * Este metodo convierte la poblacion actual en una cadena de caracteres
     * @param matrix una matriz de enteros que representa la poblacion actual del juego
     * @return una cadena de caracteres que representa la poblacion actual
     */

    private String toGridString(int[][] matrix) {
        StringBuilder grid = new StringBuilder();
        int i = 0;
        while (i < matrix.length) {
            for (int j = 0; j < matrix[i].length; j++) {
                grid.append(matrix[i][j] == 1 ? "X" : ".");
            }
            grid.append("\n");
            i++;
        }
        return grid.toString();
    }

    /**
     * Metodo que devuelve la siguiente generacion del juego en forma de cadena de caracteres
     * @param generation el numero de la genereacion actual del juego
     * @return una cadena de caracteres que representa la siguiente generacion del juego
     */
    @Override
    public String getNextGenerationAsString(long generation) {
        if (generation != 0) {
            golNextGeneration(grid);
        }
        return toGridString(grid);
    }
}