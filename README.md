# MovieApp 🎬

MovieApp es una aplicación Android moderna diseñada para explorar y descubrir películas, utilizando las últimas tecnologías y mejores prácticas recomendadas por Google. La aplicación consume datos de la API de **The Movie Database (TMDB)**.

## 🚀 Tecnologías Utilizadas

### Core & UI
*   **[Kotlin](https://kotlinlang.org/):** Lenguaje de programación moderno y conciso.
*   **[Jetpack Compose](https://developer.android.com/jetpack/compose):** Toolkit moderno para construir UIs nativas de forma declarativa.
*   **[Material 3](https://m3.material.io/):** Última evolución de Material Design para un diseño adaptativo y moderno.
*   **[Compose Navigation 3](https://developer.android.com/jetpack/compose/navigation):** Gestión de rutas y navegación entre pantallas.

### Arquitectura & Inyección de Dependencias
*   **Clean Architecture:** Separación clara de responsabilidades en capas: **Data**, **Domain** y **Presentation**.
*   **MVVM (Model-View-ViewModel):** Patrón de arquitectura para desacoplar la lógica de negocio de la UI.
*   **[Dagger Hilt](https://developer.android.com/training/dependency-injection/hilt-android):** Solución estándar para la inyección de dependencias en Android.

### Networking & Datos
*   **[Retrofit](https://square.github.io/retrofit/):** Cliente REST para realizar peticiones HTTP de forma sencilla.
*   **[OkHttp](https://square.github.io/okhttp/):** Cliente HTTP eficiente con soporte para interceptores (logging).
*   **[Gson](https://github.com/google/gson):** Serialización y deserialización de objetos JSON.
*   **[Coil](https://coil-kt.github.io/coil/):** Carga de imágenes optimizada y asíncrona.
*   **[Kotlin Coroutines & Flow](https://developer.android.com/kotlin/coroutines):** Gestión de tareas asíncronas y flujos de datos reactivos.

### Recursos & Animaciones
*   **[Lottie](https://airbnb.io/lottie/#/android):** Integración de animaciones vectoriales de alta calidad.
*   **Dimensiones dinámicas:** Uso de constantes para mantener consistencia en márgenes y espaciados (`AppDimensions`).

---

## 🏗️ Estructura del Proyecto

El proyecto sigue los principios de **Clean Architecture**:

1.  **`data/`**: Implementación de repositorios, fuentes de datos (remotas) y modelos de respuesta de la API.
2.  **`domain/`**: Lógica de negocio pura. Contiene las definiciones de Repositorios, Modelos de dominio y Casos de Uso (Use Cases).
3.  **`presentation/`**: Capa de UI. Incluye ViewModels que manejan el estado y Composable functions para la interfaz de usuario.
4.  **`di/`**: Módulos de Dagger Hilt para la provisión de dependencias.
5.  **`core/`**: Utilidades comunes, componentes reutilizables y constantes globales.

---

## ✅ Buenas Prácticas Implementadas

*   **Principio de Responsabilidad Única (SRP):** Cada clase tiene una función clara y delimitada.
*   **Inyección de Dependencias:** Facilita el testing y desacopla los componentes.
*   **State UI Pattern:** Uso de clases selladas (`ResponseResult`) para representar los diferentes estados de la UI (Loading, Success, Error).
*   **Lifecycle Awareness:** Uso de `collectAsStateWithLifecycle` para observar flujos de datos de forma segura según el ciclo de vida.
*   **Seguridad:** Gestión de llaves de API sensibles a través de `local.properties` para evitar su exposición en el control de versiones.
*   **Clean UI:** Uso de `Scaffold`, componentes personalizados (`MovieText`, `MovieTopBar`) y manejo de diálogos persistentes con `rememberSaveable`.

---

## 🛠️ Configuración Local

Para ejecutar este proyecto, necesitas añadir tu API KEY de TMDB:

1. Crea un archivo `local.properties` en la raíz del proyecto.
2. Añade la siguiente línea:
   ```properties
   MOVIE_API_KEY=tu_api_key_aqui
   ```
3. Sincroniza el proyecto con Gradle y ejecuta.
