package ecuacion.en.recurrencia;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import javax.swing.*;

public class Vista extends JFrame implements ActionListener {

    JTextField matrizTCondiciones[][];
    JTextField vectorTCoeficientes[];
    int grado;
    double matrizCondiciones[][];
    float vectorCoeficientes[];

    FormulaCuadratica cuadratica = new FormulaCuadratica();
    float raicesGrado2[] = new float[2];

    JPanel panelComponentes = new JPanel();
    JPanel panelCentral = new JPanel();
    JLabel etiqueta = new JLabel("Ingrese el grado de la ecuación: ");
    JTextField txtGrado = new JTextField("Grado");

    JButton btnGenerar = new JButton("Abrir campos");
    JButton btnCalcular = new JButton("Calcular");

    public Vista() {
        btnGenerar.addActionListener(this);
        btnCalcular.addActionListener(this);
        setBounds(0, 0, 1300, 500);
        setTitle("Ecuaciones en recurrencia");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panelCentral.setLayout(null);
        panelComponentes.add(etiqueta);
        panelComponentes.add(txtGrado);
        panelComponentes.add(btnGenerar);
        panelComponentes.add(btnCalcular);
        panelComponentes.setBackground(Color.cyan);
        // panelCentral.setBackground(Color.white);

        add(panelComponentes, BorderLayout.NORTH);
        add(panelCentral, BorderLayout.CENTER);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnGenerar) {
            grado = Integer.parseInt(txtGrado.getText());

            matrizTCondiciones = new JTextField[grado][2];
            matrizCondiciones = new double[grado][2];
            vectorTCoeficientes = new JTextField[grado + 1];
            vectorCoeficientes = new float[grado + 1];

            for (int i = 0; i < grado; i++) {
                for (int j = 0; j < 2; j++) {
                    JTextField txtMatriz = new JTextField();
                    panelCentral.add(txtMatriz);
                    txtMatriz.setBounds(30 + j * 40, 80 + i * 35, 40, 30);
                    matrizTCondiciones[i][j] = txtMatriz;
                }
            }

            for (int i = 0; i < grado + 1; i++) {
                JTextField txtVector = new JTextField();
                panelCentral.add(txtVector);
                txtVector.setBounds(30 + i * 40, 30, 40, 30);
                vectorTCoeficientes[i] = txtVector;
            }
        } else {
            for (int i = 0; i < grado; i++) {
                for (int j = 0; j < 2; j++) {
                    matrizCondiciones[i][j] = Integer.parseInt(matrizTCondiciones[i][j].getText());
                }

            }

            for (int i = 0; i < grado + 1; i++) {
                vectorCoeficientes[i] = Integer.parseInt(vectorTCoeficientes[i].getText());

            }

            if (grado == 2) {
                raicesGrado2 = cuadratica.hallarRaices(vectorCoeficientes);
                if (cuadratica.discriminante < 0) {

                    JLabel labelFinal = new JLabel("No tiene soluciones reales");
                    panelCentral.add(labelFinal);
                    labelFinal.setBounds(130, 100, 1500, 30);
                } else if (cuadratica.discriminante == 0) {
                    String solucionGeneral = ("Fn = C1*n^0*" + cuadratica.raices[0] + "^n + C2*n^1*" + cuadratica.raices[1] + "^n");

                    float matriz[][] = {{(float) Math.pow(cuadratica.raices[0], matrizCondiciones[0][0]), (float) (matrizCondiciones[0][0] * Math.pow(cuadratica.raices[1], matrizCondiciones[0][0])), (float) matrizCondiciones[0][1]},
                    {(float) Math.pow(cuadratica.raices[0], matrizCondiciones[1][0]), (float) (matrizCondiciones[1][0] * Math.pow(cuadratica.raices[1], matrizCondiciones[1][0])), (float) matrizCondiciones[1][1]}};

                    float solucion[][] = Gauss.GaussJordan(matriz);

                   /* for (int i = 0; i < grado; i++) {
                        for (int j = 0; j < grado + 1; j++) {
                            System.out.println(solucion[i][j] + " ");
                        }
                        System.out.println("");
                    }
                    System.out.println("");
                    for (int i = 0; i < grado; i++) {
                        for (int j = 0; j < grado + 1; j++) {
                            System.out.println(solucion[i][j] + " ");
                        }
                        System.out.println("");
                    }*/

                    float coeficientes[] = new float[2];

                    for (int i = 0; i < 2; i++) {
                        coeficientes[i] = solucion[i][2];
                    }

                    String ecuacion = ("Fn = " + coeficientes[0] + "*" + cuadratica.raices[0] + "^n + " + coeficientes[1] + "*n*" + cuadratica.raices[1] + "^n");

                    JLabel labelFinal = new JLabel(ecuacion);
                    panelCentral.add(labelFinal);
                    labelFinal.setBounds(130, 100, 1500, 30);

                } else {
                    String solucionGeneral = ("Fn = C1*n^0*" + cuadratica.raices[0] + "^n + C2*n^0*" + cuadratica.raices[1] + "^n");

                    float matriz[][] = {{(float) Math.pow(cuadratica.raices[0], matrizCondiciones[0][0]), (float) (Math.pow(cuadratica.raices[1], matrizCondiciones[0][0])), (float) matrizCondiciones[0][1]},
                    {(float) Math.pow(cuadratica.raices[0], matrizCondiciones[1][0]), (float) (Math.pow(cuadratica.raices[1], matrizCondiciones[1][0])), (float) matrizCondiciones[1][1]}};

                    float solucion[][] = Gauss.GaussJordan(matriz);
                    float coeficientes[] = new float[2];

                    for (int i = 0; i < 2; i++) {
                        coeficientes[i] = solucion[i][2];

                    }

                    String ecuacion = ("Fn = " + coeficientes[0] + "*" + cuadratica.raices[0] + "^n + " + coeficientes[1] + "*" + cuadratica.raices[1] + "^n");

                    JLabel labelFinal = new JLabel(ecuacion);
                    panelCentral.add(labelFinal);
                    labelFinal.setBounds(130, 100, 1500, 30);

                }

            } else {
                float raices[];

                double a[] = new double[vectorCoeficientes.length];
                for (int i = 0; i < vectorCoeficientes.length; i++) {
                    a[i] = (double) vectorCoeficientes[i];
                }
                Graeffe graeffe = new Graeffe(a);
                raices = graeffe.mostrarRaices();

                //Organización de arreglo de raices
                Arrays.sort(raices);

                float aux[] = new float[raices.length];

                for (int i = raices.length - 1; i >= 0; i--) {
                    aux[(raices.length - 1) - i] = raices[i];

                }
                raices = aux;
                //*********************************
//*************Se puede quitar
                String solucionGeneral = ("Fn=C1*(" + raices[0] + ")^n");

                int multiplicidad = 0;
                for (int i = 1; i < raices.length; i++) {
                    if (raices[i] == raices[i - 1]) {
                        multiplicidad++;
                        solucionGeneral += (" + C" + (i + 1) + "*n^" + multiplicidad + "*(" + raices[i] + ")^n");

                    } else {
                        multiplicidad = 0;
                        solucionGeneral += (" + C" + (i + 1) + "*n^" + multiplicidad + "*(" + raices[i] + ")^n");
                    }

                }
//************************************
                float matriz[][] = new float[grado][grado + 1];

                for (int i = 0; i < grado; i++) {
                    multiplicidad = 0;
                    matriz[i][0] = (float) (Math.pow(raices[0], (double) matrizCondiciones[i][0]));
                    for (int j = 1; j < grado; j++) {

                        if (raices[j] == raices[j - 1]) {
                            multiplicidad++;
                            matriz[i][j] = (float) (Math.pow(raices[j], (double) matrizCondiciones[i][0]) * Math.pow(matrizCondiciones[i][0], multiplicidad));
                            System.out.println(matrizCondiciones[j][0]);

                        } else {
                            multiplicidad = 0;
                            matriz[i][j] = (float) (Math.pow(raices[j], (double) matrizCondiciones[i][0]));
                            System.out.println(matrizCondiciones[j][0]);
                        }

                    }
                }
                for (int i = 0; i < grado; i++) {
                    matriz[i][grado] = (float) matrizCondiciones[i][1];
                }

                for (int i = 0; i < grado; i++) {
                    for (int j = 0; j < grado + 1; j++) {
                        System.out.print(matriz[i][j] + " ");
                    }
                    System.out.println("");
                }

                float coeficientes[][] = Gauss.GaussJordan(matriz);

                for (int i = 0; i < grado; i++) {
                    for (int j = 0; j < grado + 1; j++) {
                        System.out.print(coeficientes[i][j] + " ");
                    }
                    System.out.println("");
                }

                float resultadoCoeficientes[] = new float[grado];

                for (int i = 0; i < grado; i++) {
                    resultadoCoeficientes[i] = coeficientes[i][grado];
                    System.out.println(resultadoCoeficientes[i]);
                }

                String solucionFinal = ("Fn=" + resultadoCoeficientes[0] + "*(" + raices[0] + ")^n");
                int multiplicidad2 = 0;
                for (int i = 1; i < raices.length; i++) {
                    if (raices[i] == raices[i - 1]) {
                        multiplicidad2++;
                        solucionFinal += ("+" + resultadoCoeficientes[i] + "*n^" + multiplicidad2 + "*(" + raices[i] + ")^n");

                    } else {
                        multiplicidad2 = 0;
                        solucionFinal += ("+" + resultadoCoeficientes[i] + "*n^" + multiplicidad2 + "*(" + raices[i] + ")^n");
                    }

                }

                JLabel labelFinal = new JLabel(solucionFinal);
                panelCentral.add(labelFinal);
                labelFinal.setBounds(130, 100, 1500, 30);

            }
        }
    }
}
