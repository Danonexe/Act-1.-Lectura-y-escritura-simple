package Act1_Lectura_escritura_simple

import java.nio.file.Files
import java.nio.file.Path
import java.io.BufferedReader

// Leer los datos y guardarlos en un map
fun leerCotizaciones(ficheroArchivo: Path): Map<String, MutableList<Double>> {
    val datosPorColumna = mutableMapOf<String, MutableList<Double>>()

    BufferedReader(Files.newBufferedReader(ficheroArchivo)).use { br ->

        val cabecera = br.readLine()!!.split(";")

        // Inicializar las columnas
        cabecera.forEach { columna ->
            datosPorColumna[columna] = mutableListOf()
        }

        // Leer los datos de cada línea
        br.forEachLine { linea ->
            val valores = linea.split(";")
            for (i in 1 until valores.size) {
                val columna = cabecera[i]
                // Conversión de valores
                val valor = valores[i].replace(".", "").replace(",", ".")
                datosPorColumna[columna]?.add(valor.toDouble())
            }
        }
    }

    return datosPorColumna
}

// Estadísticas
fun calcularEstadisticas(datos: Map<String, List<Double>>, directorioNuevo: Path) {
    val estadisticas = mutableListOf("Columna;Minimo;Maximo;Media")

    datos.forEach { (columna, valores) ->
        if (valores.isNotEmpty()) {
            // Calcular estadísticas
            val min = valores.minOrNull() ?: 0.0
            val max = valores.maxOrNull() ?: 0.0
            val media = valores.average()

            estadisticas.add("$columna;$min;$max;$media")
        }
    }

    Files.write(directorioNuevo, estadisticas)
}
