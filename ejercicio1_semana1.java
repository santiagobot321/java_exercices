import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);

        // Tomo la entrada
        System.out.println("Escribe tu nombre: ");
        String nombre = entrada.nextLine();

        System.out.println("Escribe tu edad: ");
        int edad = entrada.nextInt();

        System.out.println("Escribe tu altura (en metros): ");
        double altura = entrada.nextDouble();

        System.out.println("Escribe tu peso (en kg): ");
        double peso = entrada.nextDouble();
        entrada.nextLine(); // Consumir el salto de línea pendiente

        System.out.println("Escribe tu plan (BASICO / PLUS / PREMIUM): ");
        String plan = entrada.nextLine().toUpperCase(); // Convertimos a mayúsculas

        System.out.println("¿Es la primera vez que vienes? (si/no): ");
        String respuesta = entrada.nextLine().toLowerCase(); // Convertimos a minúsculas
        boolean primeraVez = respuesta.equals("si");

        // Valido la edad
        if (edad < 14) {
            System.out.println("Lo sentimos, no eres elegible.");
            return; // termina el programa aquí
        } else if (edad < 18) {
            System.out.println("Requieres autorización de un acudiente.");
        }

        // Calculo el precio segun el plan que se elije
        double precioBase = 0.0;
        switch (plan) {
            case "BASICO":
                precioBase = 80.0;
                break;
            case "PLUS":
                precioBase = 120.0;
                break;
            case "PREMIUM":
                precioBase = 180.0;
                break;
            default:
                System.out.println("Plan no válido.");
                return;
        }

        // Calculo los descuentos
        double descuento = 0.0;
        if (primeraVez) {
            descuento += 0.10;
        }
        if (edad >= 16 && edad <= 25) {
            descuento += 0.10;
        }
        if (descuento > 0.20) {
            descuento = 0.20; // máximo 20%
        }

        // Calculo el precio final
        double precioFinal = precioBase * (1 - descuento);

        // BMI
        double bmi = peso / (altura * altura);
        String categoriaBMI = "";

        if (bmi < 18.5) {
            categoriaBMI = "Bajo peso";
        } else if (bmi < 25) {
            categoriaBMI = "Normal";
        } else if (bmi < 30) {
            categoriaBMI = "Sobrepeso";
        } else {
            categoriaBMI = "Obesidad";
        }

        // Muestra en consola
        System.out.println("----- RESUMEN DE COTIZACIÓN -----");
        System.out.println("Nombre: " + nombre);
        System.out.println("Plan elegido: " + plan);
        System.out.println("Precio base: $" + precioBase);
        System.out.println("Descuento aplicado: " + (descuento * 100) + "%");
        System.out.println("Precio final: $" + precioFinal);
        System.out.println("Categoría de BMI: " + categoriaBMI);
    }
}
