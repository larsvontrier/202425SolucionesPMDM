package com.pepinho.pmdm.exames

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pepinho.pmdm.exames.model.Cafe
import com.pepinho.pmdm.exames.model.CafeConCategoria
import com.pepinho.pmdm.exames.repository.CafeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
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

    val loadingStateCafe: StateFlow<Boolean> = (cafeConCategoria)
        .map { it.cafe.idCafe == 0 }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = true
        )



    private val _cafes = MutableStateFlow<List<Cafe>>(emptyList())
    val cafes = _cafes.asStateFlow()

    val loadingState: StateFlow<Boolean> = cafes
        .map { it.isEmpty() }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = true
        )

//    private val _cafe = MutableStateFlow<Cafe>(Cafe())
//    val cafe = _cafe.asStateFlow()

//    init {
//        getCoffeConCategoriaRandom()
//    }

    fun getCoffeConCategoriaRandom(){
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

    fun getCafes() {

        /**
         * Obtenemos una lista de cafés del repositorio y la almacenamos en el flujo de datos _cafes.
         * Esto nos permite observar los cambios en la interfaz de usuario.
         * Además, cuando el proceso puede llear tiempo, es recomendable hacerlo en un hilo secundario
         * para no bloquear el hilo principal.
         * viewModelScope es un scope que se encarga de lanzar corutinas que se cancelan cuando el ViewModel
         * es destruido, que perdura ante los cambios de configuración de la actividad asociada.
         */
        viewModelScope.launch {
            _cafes.value = repositorio.getCafes()
        }
    }


//    fun getCafeById(id: Long) {
//        /**
//         * Obtenemos un café del repositorio y lo almacenamos en el flujo de datos _cafeConCategoria.
//         * Esto nos permite observar los cambios en la interfaz de usuario.
//         * Además, cuando el proceso puede llear tiempo, es recomendable hacerlo en un hilo secundario
//         * para no bloquear el hilo principal.
//         * viewModelScope es un scope que se encarga de lanzar corutinas que se cancelan cuando el ViewModel
//         * es destruido, que perdura ante los cambios de configuración de la actividad asociada.
//         */
//        viewModelScope.launch {
//            _cafe.value = repositorio.getCafeById(id)
//        }
//    }

    fun getCafeConCategoriaById(id: Int) {
        /**
         * Obtenemos un café con categoría del repositorio y lo almacenamos en el flujo de datos _cafeConCategoria.
         * Esto nos permite observar los cambios en la interfaz de usuario.
         * Además, cuando el proceso puede llear tiempo, es recomendable hacerlo en un hilo secundario
         * para no bloquear el hilo principal.
         * viewModelScope es un scope que se encarga de lanzar corutinas que se cancelan cuando el ViewModel
         * es destruido, que perdura ante los cambios de configuración de la actividad asociada.
         */
        viewModelScope.launch {
            _cafeConCategoria.value = repositorio.getCafeConCategoriaById(id)
        }
    }


}