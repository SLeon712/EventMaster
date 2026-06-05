# EventMaster
## Sistema de gestión de eventos desarrollado como proyecto de ingeniería. Permite la administración de categorías y la planificación detallada de eventos asociados.

Integrantes:
Sebastian Leon Brevis,
Cristobal Ulloa Barboza

## Descripción
EventMaster es una solución full-stack que conecta una aplicación móvil (Android/Kotlin) con un backend robusto (Laravel). El sistema permite a los usuarios gestionar su agenda, clasificar eventos por categorías y mantener un seguimiento organizado.

## Requisitos previos
Para ejecutar este proyecto, asegúrate de tener instalados los siguientes componentes:

* **PHP** (>= 8.2)
* **Composer** (Gestor de dependencias para PHP)
* **MySQL/MariaDB**
* **Android Studio** (con SDK 36+)
* **Git**


### 1. Backend (Laravel)

# Iniciar el servidor de desarrollo
php artisan serve --host=0.0.0.0 --port=8000

### Backend (Laravel)
1.  Clona el repositorio: `git clone <url-del-repo>`
2.  Instala las dependencias: `composer install`
3.  Configura el entorno: Copia `.env.example` a `.env` y ajusta tus credenciales de base de datos.
4.  Genera la clave: `php artisan key:generate`
5.  Migra la base de datos: `php artisan migrate`
6.  Inicia el servidor: `php artisan serve --host=0.0.0.0 --port=8000`

### Frontend (Android)
1.  Abre el proyecto en Android Studio.
2.  Ajusta la `BASE_URL` en `NetworkModule.kt` según tu entorno:
    * **Emulador:** `http://10.0.2.2:8000/api/`
    * **Dispositivo Físico:** `http://<IP_DE_TU_PC>:8000/api/`
3.  Sincroniza el proyecto con Gradle y ejecuta en tu dispositivo o emulador.

## Tecnologías utilizadas
* **Backend:** Laravel 11, PHP 8.2, MySQL.
* **Frontend:** Kotlin, Jetpack Compose, Retrofit 2, Hilt (Dagger).

## Detalles
* Asegurese que el repositorio de eventmaster se encuentre en la misma carpeta que eventmaster-api