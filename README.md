# Soluciones a los ejercicios y prácticas de la asignatura de Programación Multimedia y Dispositivos Móviles
 
Las soluciones estána agrupadas por unidades didácticas y se irán completando a lo largo del curso.

Se tratan de proyectos de IntelliJ IDEA y Android Studio que se pueden importar directamente.

## Unidad 1. Introducción a Kotlin

Kotlin básico, estructuras de control, funciones, clases, objetos, herencia, interfaces, colecciones, etc.

- Tarea 1. Gestión de exámenes

## Unidad 2. Introducción a Android

### QuestionTest

Aplicación con una única actividad que muestra una pregunta de test con cuatro opciones y un botón para comprobar la respuesta.
Incluye:
- Layout `activity_main.xml`:
  - De tipo LinearLayout, anidadas.
  - Vistas: `TextView`, `Button`, `RadioButton`, `RadioGroup`.
- Recursos de texto multilingual (en inglés, galego y castellano) para los mensajes, botones y textos de las preguntas.
- Actividad principal `MainActivity.kt`:
  - Recuperación de las vistas de modo clásico con `findViewById`.
  - Escritura en el LogCat con `Log.d` (debug).
  - Comprobación de respuesta en mensaje emergente con `Snackbar`. Uso de `Toast` para cuando no se selecciona ninguna opción.
  - Situación del `Snackbar` sobre el botón con `setAnchorView` (en Kotlin con asignación de propiedades).
  - Asignación de una acción al `Snackbar` con `setAction` (setAction { ... }, recoge una cadena y una lambda que, en este caso, cierra el `Snackbar`).
  - Cambio de color del `Snackbar` con `setBackgroundTint` y modo de superposición con `setBackgroundTintMode`.

### DadoSuzuki

Aplicación con una única actividad que simula el lanzamiento de un dado de seis caras que se corresponde con un ritmo Suzuki.

#### Modelo de datos

- Creación de una modelo de datos `Dado` con un número de lados con una función `lanzar` que devuelve un número aleatorio entre 1 y número de lados.
- `DadoSuzuki` hereda de `Dado`y construye un dado de 6 caras.
- `Ritmo` es un objeto que contiene el nombre del ritmo y el id de la imagen asociada.

#### Recursos

- Layout `activity_main.xml`:
  - De tipo ConstraintLayout introducido dentro de un ScrollView para que sea scrollable si no cabe en la pantalla (no es la mejor solución, pero es la más sencilla cuando no queremos que el contenido se solape o crear layout diferentes para cada tamaño de pantalla u orientación).
  - Vistas: `ImageView`, `Button`.
- Recursos de imagen para los ritmos Suzuki y para los iconos asociados a cada ritmo. De tipo PNG.
- Recurso de imagen para la app icon (se incluye para todas las densidades) (mipmap). Además, existe un background de tipo XML para el icono de la app (drawable). Este background se puede usar para cambiar el color del icono de la app.

#### Actividad principal

- Se han creado **constantes con los nombres de los ritmos Suzuki** y los id de las imágenes asociadas. Se hace con `companion object` (podría hacerse con un enum, pero en este caso no es necesario).
- Se han creado una constante con una array de imágenes del dado (de tipo `IntArray`).
- Para el acceso a las vistas **se emplea `viewBinding`** (activado en el `build.gradle.kts`).
- Se hace uso de `MediaPlayer` para reproducir un sonido al lanzar el dado (MediaPlayer es una clase de Android que permite reproducir archivos de audio).` mediaAudio?.start()` reproduce el audio.
- Una vez seleccionada una imagen aleatoria, se muestra en el `ImageView` correspondiente, animándola con el método `animate()`, que permite rotar (en este caso, una rotación de 720f grados), asignar una duración (2000ms) y un interpolador (DecelerateInterpolator). Además, para controlar el audio, cuando se lanza el dado, se deshabilita el botón de lanzamiento y se habilita cuando finaliza la animación, así como reproducir el sonido de lanzamiento del dado (ved detalles en el código).
- Se ha creado una función de extensión de ImageView para hacer un fade en el icono:

```kotlin
fun ImageView.fade(fadeIn: Boolean = true, duration: Long = 1000L): ViewPropertyAnimator {
    return this.animate()
        .alpha(if (fadeIn) 1f else 0f)
        .setDuration(duration)
        .setInterpolator(DecelerateInterpolator())
}
```

## Unidad 3. Layouts y navegación en Android

### Cuestionarios

Aplicación con una única actividad que muestra una pregunta de test con cuatro opciones y un botón para comprobar la respuesta.

#### Modelo de datos

El modelo de datos se compone de las clases con las que se ha trabajado en la primera unidad: `Cuestionario`, `Opcion`, `Pregunta`, `PreguntaTest`, `PreguntaVerdaderoFalso`. Además, para similar un respositorio de preguntas, se ha creado la clase `PreguntaDAO`cib kas listas de preguntas y métodos para hacer operaciones CRUD con las preguntas.

#### Recursos

- Layout `activity_main.xml`:
  - De tipo LinearLayout, anidadas. Se han añadido botones para Siguiente (navegar entre preguntas), Limpiar y Comprobar (comprobar la respuesta).
  - Vistas: `TextView`, `Button`, `RadioButton`, `RadioGroup`.
  - Recursos de texto multilingual (en inglés, galego y castellano) para los mensajes, botones. También se incluye para las preguntas y opciones, pero sólo en modo `tools:text` para facilitar el diseño (`tools:text="@tools:sample/lorem`").

#### Actividad principal

- Utiliza el modo tradicional para recuperar las vistas con `findViewById`.
- Actualiza la pregunta al pulsar el botón _Siguiente_.
- Si es la última pregunta (índice igual al tamaño de la lista de preguntas), no muestra el botón _Siguiente_.

### Índice de masa corporal

Aplicación con una única actividad que calcula el índice de masa corporal (IMC) a partir de la altura y el peso introducidos por el usuario.

#### Modelo de datos

- Género: enumerado con los valores `HOMBRE`, `MUJER`, `SIN_ESPECIFICAR`.
- UserData: clase de datos con el peso, la altura, isAdulto y el género. Además, incluye un método para calcular el IMC.

#### Recursos

- Layout `activity_main.xml`:
  - De tipo `ConstraintLayout`, dentro de un `ScrollView`.
  - Vistas: `EditText` (en su versión Material Design: `com.google.android.material.textfield.TextInputEditText`, dentro de un `com.google.android.material.textfield.TextInputLayout`), `TextView` (en su versión Material Design: `com.google.android.material.textview.MaterialTextView`), `Switch` (en su versión Material Design: `com.google.android.material.switchmaterial.SwitchMaterial`), `Button` (en su versión Material Design: `com.google.android.material.button.MaterialButton`), `TextView` (en su versión Material Design: `com.google.android.material.textview.MaterialTextView`), `RadioButton`, `RadioGroup`, `ImageView`.
- Recursos de texto multilingual (en inglés, galego y castellano) para los mensajes, botones y textos de las preguntas.
- Recursos de font (fuente) para los textos de la aplicación (merienda y sus variantes), de tipo TTF.
- Se ha usado un Thema personalizado para la aplicación, que se define en el archivo `themes.xml`. Dicho tema se ha importado en el `AndroidManifest.xml` con la propiedad `android:theme="@style/Theme.IMCApp"` y se ha creado con Material Design Builder.

#### Actividad principal

- Se ha creado una vista personalizada `ImageViewBarra` que hereda de `AppCompatImageView` y que se usa para mostrar una barra con una lína vertical para ver el rango de IMC:
  - Se hereda de `AppCompatImageView`.
  - Se sobreescribe el método `onDraw` para dibujar la barra:
  ```kotlin
    override fun onDraw(canvas: Canvas) {
      super.onDraw(canvas)
      if (posicionLinea > 0.0f) // Posición linea es una propiedad de la clase
      canvas.drawLine(posicionLinea, 0f, posicionLinea, height.toFloat(), paint)
    }
    ```
- `MainActivity`:
  - Emplea `viewBinding` para acceder a las vistas.
  - Se añade un método para ocultar el teclado al pulsar el botón de calcular y enter.