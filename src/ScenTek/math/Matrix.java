/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ScenTek.math;

/**
 *
 * @author Andrew
 */
public class Matrix {
    private float[][] matrix;
    public final int n_rows;
    public final int n_cols;
    public Matrix(float[][] matrix){
        this.matrix = matrix;
        n_rows = matrix.length;
        n_cols = matrix[0].length;
    }
    /**
     * Returns the value at the passed (r,c)
     * @param r
     * @param c
     * @return value at (r,c)
     */
    public float get(int r, int c){
        return matrix[r][c];
    }
    /**
     * Adds passed matrix to the current matrix
     * @param m Matrix to be added
     * @return The matrix result of the operation
     */
    public Matrix add(Matrix m){
        if(m.n_cols != n_cols || m.n_rows != n_rows) throw new IllegalArgumentException("Matrices are not the same size");
        float[][] temp = new float[n_rows][n_cols];
        for(int r = 0; r < n_rows; r++){
            for(int c = 0; c < n_rows; c++){
                temp[r][c] = m.get(r, c) + get(r, c);
            }
        }
        return new Matrix(temp);
    }
    /**
     * Subtracts the passed matrix from the current
     * @param m matrix to be subtracted
     * @return the matrix result from the operation 
     */
    public Matrix subtract(Matrix m){
        if(m.n_cols != n_cols || m.n_rows != n_rows) throw new IllegalArgumentException("Matrices are not the same size");
        float[][] temp = new float[n_rows][n_cols];
        for(int r = 0; r < n_rows; r++){
            for(int c = 0; c < n_rows; c++){
                temp[r][c] = get(r, c) - m.get(r, c);
            }
        }
        return new Matrix(temp);
    }
    /**
     * Multiplies the matrix by m.
     * @param m the matrix to be multiplied by
     * @return the resulting matrix of the operation
     */
    public Matrix multiply(Matrix m){
        if(n_rows != m.n_cols) throw new IllegalArgumentException("Rows and columns need to match for multiplication of matrices");
        float[][] temp = new float[m.n_rows][n_cols];
        int m1Row = 0;// stores the current row being multiplied in the first matrix
        int m2Col = 0;// stores the current col being multiplied in the 2nd matrix
        for(int c = 0; c < n_cols; c++){
            int sum = 0;
            // find the Dot Product
            for(int r = 0; r < m.n_rows; r++){
                sum += get(m1Row,c) * m.get(r, m2Col);
            }
            temp[m1Row][m2Col] = sum;
            m1Row++;
            m2Col++;
        }
        return new Matrix(temp);
    }
    
    public static Matrix genIdentityMatrix(int size){
        float[][] temp = new float[size][size];
        for(int index = 0; index < size; index++) temp[size][size] = 1.0f;
        return null; 
    }
}
