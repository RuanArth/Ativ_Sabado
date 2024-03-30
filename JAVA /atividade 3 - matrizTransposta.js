public class MatrixTranspose {
    
    public static void printMatrixAndTranspose(int[][] matrix) {
        System.out.println("Matriz Original:");
        printMatrix(matrix);
        
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[][] transposedMatrix = new int[cols][rows];
        
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                transposedMatrix[j][i] = matrix[i][j];
            }
        }
        
        System.out.println("\nMatriz Transposta:");
        printMatrix(transposedMatrix);
    }
    
    public static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int value : row) {
                System.out.print(value + " ");
            }
            System.out.println();
        }
    }
    
    public static void main(String[] args) {
        int[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        printMatrixAndTranspose(matrix);
    }
}
