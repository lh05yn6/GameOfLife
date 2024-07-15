package gol;

public class GOL {
    /**
     * Convierte una cadena de entrada en una matriz de enteros
     * @param input es una cadena de entrada que representa la confuguracion inicial de la poblacion
     * @return una matriz de enteros que representa la configuracion incial de la poblacion
     */
    public static int[][] ConverterStringMatrix(String input) {
        //Divido la cadena de entrada en secciones small separadas por "#"
        String[] numbers = input.split("#");
        //Creacion de una nueva matriz de enteros con el numero de filas igual a la cantidad de secciones, y las columnas igual a la long. de la primera seccion
        int[][] resultMatrix = new int[numbers.length][numbers[0].length()];
        //Recorre todas las secciones y cada caracter en cada seccion , y se asigna el valor numerbers[0] a cada posicion
        for (int i = 0; i < numbers.length; i++) {
            for (int j = 0; j < numbers[0].length(); j++) {
                resultMatrix[i][j] = Character.getNumericValue(numbers[i].charAt(j));
            }
        }
        return resultMatrix;
    }

    /**
     * Metodo principal se utiliza para ejecutar la simulacion del juego
     * @param args arreglo de argumentos de entrada: ancho,alto,numero maximo de genereaciones,velocidad de simulacion y poblacion inicial
     * @throws Exception si alguno de los parametros es invalido
     */
    public static void main(String[] args) throws Exception {
        //Se convierten los parametros de entra a una cadena de caracteres
        int width = Integer.parseInt(args[0]);
        int height = Integer.parseInt(args[1]);
        int maxNumberGeneration = Integer.parseInt(args[2]);
        int msVelocidad = Integer.parseInt(args[3]);
        String poblacion = args[4];

        //Llamo al metodo que valida los parametros
        parametrosValidos(width, height, maxNumberGeneration, msVelocidad, poblacion);

        //Convierto la configuracion inicial de la poblacion en una matriz de enteros
        int[][] grid = ConverterStringMatrix(poblacion);

        // Creo una instancia de la Class Simulation con la matriz de enteros de mi configuracion inicial de la poblacion
        final GolGenerator generator = new gol.Simulation(grid);

        // Llamo al metodo render de la CLass SwingRenderer para visualizar la simulacion
        SwingRenderer.render(generator, new GolSettings(10, 10, 1000, 0));
    }

    /**
     * Metodo para validar los parametros de entrada
     * @param w representa el ancho del tablero
     * @param h representa la altura del tablero
     * @param g representa el numero maximo de generaciones
     * @param s representa la velocidad de simulacion
     * @param p representa la configuracion actual de la poblacion
     * @throws Exception si alguno de los parametros es invalido lanza una excepcion
     */
    public static void parametrosValidos(int w,int h,int g,int s,String p) throws Exception {
        if (w != 10 && w != 20 && w != 40 && w != 80) {
            throw new Exception("Parametro invalido para las filas");
        }
        if (h != 10 && h != 20 && h != 40){
            throw new Exception("Parametro invalido para las columnas");
        }
        if (g < 0) {
            throw new Exception("Parametro invalido para el numero de generaciones");
        }
        if (s < 250 || s > 1000) {
            throw new Exception("Parametro invalido para la velocidad");
        }
        if (p.length() != h * w + h - 1) {
            throw new Exception("Parametro invalido para la poblacion");
        }
    }
}