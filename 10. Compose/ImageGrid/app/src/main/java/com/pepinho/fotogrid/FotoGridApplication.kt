
package com.pepinho.fotogrid

import android.app.Application
import com.pepinho.fotogrid.data.dependencies.AppInyectorContainer
import com.pepinho.fotogrid.data.dependencies.AppInyectorContainerImpl

class FotoGridApplication : Application() {
    /**
     * La instancia de AppInyectorContainer es utilizada por el resto de las clases para
     * obtener dependencias
     * */
    lateinit var contenedor: AppInyectorContainer
        private set // Solo se puede modificar desde esta clase

    /**
     * Inicializa el contenedor de inyección de dependencias.
     * Se llama al crear la aplicación y está disponible para el resto de las clases.
     * A partir de aquí se puede usar el contenedor para obtener las dependencias necesarias para
     * acceder a la API de fotos.
     */
    override fun onCreate() {
        super.onCreate()
        contenedor = AppInyectorContainerImpl()
    }
}
