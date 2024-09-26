package Act1_Lectura_escritura_simple

import java.nio.file.Files
import java.nio.file.Path
import java.io.BufferedReader
import java.io.IOException

//leer los datos y guardarlos en un map
fun leerCotizaciones(ficheroArchivo: Path): Map<String, MutableList<Double>> {
    val datosPorColumna = mutableMapOf<String, MutableList<Double>>()

    BufferedReader(Files.newBufferedReader(ficheroArchivo)).use { br ->
        //Datos de arriba la excepción es por si el archivo está vacio
        val cabecera = br.readLine()?.split(";") ?: throw IOException("Archivo vacío")

        // Inicializar las columnas
        cabecera.forEach { columna ->
            datosPorColumna[columna] = mutableListOf()
        }

        // Leer los datos de cada línea
        br.forEachLine { linea ->
            val valores = linea.split(";")
            for (i in 1 until valores.size) {
                val columna = cabecera[i]
                //esto me dio muchos errores
                val valor = valores[i].replace(".", "").replace(",", ".") // Normalizamos los datos
                    datosPorColumna[columna]?.add(valor.toDouble())
            }
        }
    }
    return datosPorColumna
}
//estadisticas
fun calcularEstadisticas(datos: Map<String, List<Double>>, directorioNuevo: Path) {
    val estadisticas = mutableListOf("Columna;Minimo;Maximo;Media")

    datos.forEach { (columna, valores) ->
        if (valores.isNotEmpty()) {
            // la media el minimo y el máximo lo he buscado en internet
            val min = valores.minOrNull() ?: 0.0
            val max = valores.maxOrNull() ?: 0.0
            val media = valores.average()

            estadisticas.add("$columna;$min;$max;$media")
        }
    }

    Files.write(directorioNuevo, estadisticas)
}