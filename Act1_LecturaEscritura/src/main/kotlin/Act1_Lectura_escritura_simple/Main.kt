package Act1_Lectura_escritura_simple

import java.nio.file.Path

fun main() {
    val raiz = Path.of("src")
    val ficheroArchivo = raiz.resolve("main").resolve("resources").resolve("cotizacion.csv")
    val ficheroNuevo = raiz.resolve("main").resolve("resources").resolve("estadististicas.csv")


        // Leer las cotizaciones desde el archivo
        val datos = leerCotizaciones(ficheroArchivo)

        // Calcular estadísticas y escribirlas en un nuevo archivo
        calcularEstadisticas(datos, ficheroNuevo)

}
