# App ModoGuardian — Proyecto personal de Gonzalo Sandoval

# intengrantes de grupo
Gonzalo Sandoval
Maximiliano Fuentes

Aplicación Android desarrollada en Kotlin + Jetpack Compose para demostrar las guías del caso ModoGuardian.

## Flujo principal

Login → Menú principal → Guías según rol.

## Autenticación de demostración

La aplicación usa cuentas sintéticas locales con los roles Admin, Supervisor y Operador, únicamente para la evaluación. Las credenciales no se muestran en la pantalla de inicio de sesión ni se guardan en el dispositivo.

## Permisos demostrativos

- Admin: Guías 9, 10, 11, 12 y 13.
- Supervisor: Guías 9, 10, 12 y 13.
- Operador: Guías 9 y 13.

## Login

El login valida los campos cuando se pulsa el botón, que permanece disponible aunque estén vacíos. Muestra carga y errores controlados y permite simular una desconexión para probar el manejo de errores. Al cerrar sesión se limpian correo, contraseña, rol y estado de autenticación. No se persiste información de la sesión.

## Estructura relevante

- `ui/`: pantallas Compose, componentes y utilidades de tamaño.
- `viewmodel/`: lógica de estado y navegación.
- `model/`: estados, errores, roles y usuario autenticado.
- `repository/`: autenticación demo sin persistencia de credenciales.
- `navigation/`: rutas y navegación existente del proyecto.
