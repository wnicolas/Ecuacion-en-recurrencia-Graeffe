package ecuacion.en.recurrencia;


public class FormulaCuadratica {

    float a, b, c;
    float raices[] = new float[2];
    float discriminante;

    public FormulaCuadratica() {
        
    }

    public float[] hallarRaices(float coeficientes[]) {
        this.a = coeficientes[0];
        this.b = coeficientes[1];
        this.c = coeficientes[2];

        discriminante = (b * b) - (4 * a * c);

        if (discriminante < 0) {
            System.out.println("Raíces complejas");
            return raices;
        } else {
            float x1 = (float) ((-b + Math.sqrt(discriminante)) / 2 * a);
            float x2 = (float) ((-b - Math.sqrt(discriminante)) / 2 * a);
            raices[0]=x1;
            raices[1]=x2;
            return raices;
        }

       
    }

}
