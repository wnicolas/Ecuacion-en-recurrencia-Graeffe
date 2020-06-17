package ecuacion.en.recurrencia;


public class Gauss {

    //Métodos para solucionar la matriz mediante Gauss. Este método debe recibir una matriz de float
    //Filas =columnas-1
    public static float[][] GaussJordan(float[][] mat) {

        int piv = 0;
        int var = mat.length;

        for (int i = 0; i < var; i++) {
            mat = pivote(mat, piv, var);
            mat = ceros(mat, piv, var);
            piv++;
        }
        return mat;
    }

    public static float[][] pivote(float mat[][], int piv, int var) {
        float temp = mat[piv][piv];
        for (int y = 0; y < (var + 1); y++) {
            mat[piv][y] = mat[piv][y] / temp;
        }
        return mat;
    }

    public static float[][] ceros(float mat[][], int piv, int var) {
        for (int x = 0; x < var; x++) {
            if (x != piv) {
                float c = mat[x][piv];
                for (int z = 0; z < (var + 1); z++) {
                    mat[x][z] = ((-c) * mat[piv][z]) + mat[x][z];
                }
            }
        }
        return mat;
    }
    //Fin métodos para resolver Gauss
    

}
