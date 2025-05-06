
package com.pepinho.fotogrid

import android.app.Application
import com.pepinho.fotogrid.data.AppContainer
import com.pepinho.fotogrid.data.DefaultAppContainer

class MarsPhotosApplication : Application() {
    /**
     * La instancia de AppContainer es utilizada por el resto de las clases para obtener dependencias
     * */
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}
