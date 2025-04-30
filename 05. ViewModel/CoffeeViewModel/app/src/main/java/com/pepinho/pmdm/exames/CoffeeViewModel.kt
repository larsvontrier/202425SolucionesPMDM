package com.pepinho.pmdm.exames

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pepinho.pmdm.exames.model.CafeConCategoria
import com.pepinho.pmdm.exames.model.CafeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CoffeeViewModel(private val repositorio: CafeRepository): ViewModel() {
    /**
     * Creamos un flujo de datos mutable que almacenará un café con categoría.
     * Lo hacemos privado para que no se pueda modificar desde fuera de la clase, desde la actividad.
     * QUeremos que sólo el ViewModel pueda modificarlo.
     */
    private val _cafeConCategoria = MutableStateFlow(CafeConCategoria())

    /**
     * Creamos un flujo de datos inmutable que se puede observar desde la actividad. Los cambios que se
     * produzcan en el flujo de datos _cafeConCategoria se reflejarán en este flujo de datos.
     */
    val cafeConCategoria = _cafeConCategoria.asStateFlow()

    init {
        getCoffeConCategoria()
    }

    fun getCoffeConCategoria(){
        /**
         * Lanzamos una corutina en el scope del ViewModel para obtener un café con categoría aleatorio
         * del repositorio y lo almacenamos en el flujo de datos _cafeConCategoria.
         * Hacerlo en un flujo de datos nos permite observar los cambios en la interfaz de usuario.
         * Además, cuando el proceso puede llear tiempo, es recomendable hacerlo en un hilo secundario
         * para no bloquear el hilo principal.
         * viewModelScope es un scope que se encarga de lanzar corutinas que se cancelan cuando el ViewModel
         * es destruido, que perdura ante los cambios de configuración de la actividad asociada.
         */
        viewModelScope.launch {
            _cafeConCategoria.value = repositorio.getCafeConCategoriaRandom()
        }
    }

}