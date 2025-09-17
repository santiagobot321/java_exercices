import java.util.*;
import javax.swing.*;

public class tienda_mini {
    // Modelo de datos
    private static ArrayList<String> productos = new ArrayList<>();
    private static double[] precios = {}; // sincronizado con nombres
    private static HashMap<String, Integer> stock = new HashMap<>();

    private static double total = 0;

    public static void main(String[] args) {
        // Datos iniciales
        addProducto("bombombun", 600, 51);
        addProducto("chewing gum", 300, 53);
        addProducto("juices", 2500, 20);
        addProducto("soda", 4000, 17);

        // Menú principal
        while (true) {
            String opcion = JOptionPane.showInputDialog(
                    "=== MINI TIENDA ===\n" +
                            "1. Agregar producto\n" +
                            "2. Listar inventario\n" +
                            "3. Comprar producto\n" +
                            "4. Mostrar estadísticas\n" +
                            "5. Buscar producto\n" +
                            "6. Salir\n\n" +
                            "Elige una opción:"
            );

            if (opcion == null) break; // salir si cierran ventana

            switch (opcion) {
                case "1":
                    agregarProducto();
                    break;
                case "2":
                    listarInventario();
                    break;
                case "3":
                    comprarProducto();
                    break;
                case "4":
                    mostrarEstadisticas();
                    break;
                case "5":
                    buscarProducto();
                    break;
                case "6":
                    salir();
                    return;
                default:
                    JOptionPane.showMessageDialog(null, "Opción inválida.");
            }
        }
    }

    // ===================== MÉTODOS USADOS =====================

    private static void addProducto(String nombre, double precio, int cantidad) {
        productos.add(nombre);
        precios = expandPrecios(precios, precio);
        stock.put(nombre, cantidad);
    }

    private static double[] expandPrecios(double[] oldArray, double nuevo) {
        double[] nuevoArray = new double[oldArray.length + 1];
        for (int i = 0; i < oldArray.length; i++) {
            nuevoArray[i] = oldArray[i];
        }
        nuevoArray[oldArray.length] = nuevo;
        return nuevoArray;
    }

    private static int indexOfNombre(String nombre) {
        return productos.indexOf(nombre);
    }



    private static void agregarProducto() {
        try {
            String nombre = JOptionPane.showInputDialog("Nombre del producto:");
            if (nombre == null || nombre.trim().isEmpty()) return;
            if (productos.contains(nombre)) {
                JOptionPane.showMessageDialog(null, "Ese producto ya existe.");
                return;
            }

            double precio = Double.parseDouble(JOptionPane.showInputDialog("Precio del producto:"));
            int cantidad = Integer.parseInt(JOptionPane.showInputDialog("Stock inicial:"));

            addProducto(nombre, precio, cantidad);
            JOptionPane.showMessageDialog(null, "Producto agregado exitosamente.");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Entrada inválida. Intente de nuevo.");
        }
    }

    private static void listarInventario() {
        StringBuilder sb = new StringBuilder("=== INVENTARIO ===\n");
        for (int i = 0; i < productos.size(); i++) {
            String nombre = productos.get(i);
            sb.append(nombre)
                    .append(" → $").append(precios[i])
                    .append(" (stock: ").append(stock.get(nombre)).append(")\n");
        }
        JOptionPane.showMessageDialog(null, sb.toString());
    }

    private static void comprarProducto() {
        String nombre = JOptionPane.showInputDialog("¿Qué producto deseas comprar?");
        if (nombre == null) return;

        if (!productos.contains(nombre)) {
            JOptionPane.showMessageDialog(null, "Producto no encontrado.");
            return;
        }

        try {
            int cantidad = Integer.parseInt(JOptionPane.showInputDialog("¿Cuántas unidades deseas comprar?"));
            int index = indexOfNombre(nombre);

            if (cantidad <= 0) {
                JOptionPane.showMessageDialog(null, "Cantidad inválida.");
                return;
            }

            if (stock.get(nombre) >= cantidad) {
                stock.put(nombre, stock.get(nombre) - cantidad);
                total += precios[index] * cantidad;
                JOptionPane.showMessageDialog(null, "Compra realizada.\nSubtotal acumulado: $" + total);
            } else {
                JOptionPane.showMessageDialog(null, "Stock insuficiente. Solo quedan " + stock.get(nombre));
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Entrada inválida.");
        }
    }

    private static void mostrarEstadisticas() {
        if (productos.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay productos.");
            return;
        }

        double min = precios[0], max = precios[0];
        String prodMin = productos.get(0), prodMax = productos.get(0);

        for (int i = 1; i < precios.length; i++) {
            if (precios[i] < min) {
                min = precios[i];
                prodMin = productos.get(i);
            }
            if (precios[i] > max) {
                max = precios[i];
                prodMax = productos.get(i);
            }
        }

        JOptionPane.showMessageDialog(null,
                "=== ESTADÍSTICAS ===\n" +
                        "Más barato: " + prodMin + " → $" + min + "\n" +
                        "Más caro: " + prodMax + " → $" + max);
    }

    private static void buscarProducto() {
        String termino = JOptionPane.showInputDialog("Escribe el nombre o parte del nombre a buscar:");
        if (termino == null || termino.trim().isEmpty()) return;

        StringBuilder sb = new StringBuilder("Resultados:\n");
        boolean encontrado = false;

        for (int i = 0; i < productos.size(); i++) {
            String nombre = productos.get(i);
            if (nombre.toLowerCase().contains(termino.toLowerCase())) {
                sb.append(nombre).append(" → $").append(precios[i])
                        .append(" (stock: ").append(stock.get(nombre)).append(")\n");
                encontrado = true;
            }
        }

        if (!encontrado) {
            JOptionPane.showMessageDialog(null, "No se encontraron coincidencias.");
        } else {
            JOptionPane.showMessageDialog(null, sb.toString());
        }
    }

    private static void salir() {
        JOptionPane.showMessageDialog(null, "Gracias por usar la MiniTienda.\nTotal de compras: $" + total);
    }
}
