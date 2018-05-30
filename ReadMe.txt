Practica dse Fundamentos de ingeniera del software para la UPM, un chat "funcional" 
Enunciado:
El plazo máximo para realizar la tercera entrega de la práctica obligatoria se indicará en la tarea
de Moodle correspondiente. En esta entrega se deberán realizar las siguientes actividades:

1. Diagrama de componentes: se debe organizar la aplicación en componentes
arquitectónicos y generar un diagrama de componentes que los relacione a través de sus
interfaces.

2. Diagrama de despliegue: se realizará, un diagrama de despliegue ideal en el que pueden
considerarse tantos nodos como se desee, organizando dichos nodos como si se fuera a
disponer de la aplicación en su totalidad.

3. Código fuente: a partir de todos los modelos creados se codificará una parte de la
aplicación WhatsUPM. Queda a elección de cada grupo la cantidad de funcionalidades
a codificar. No obstante, se sugiera codificar, al menos, las operaciones básicas de
gestión de la agenda (Alta, Baja, Modificación y Borrado); el envío de un mensaje entre
dos usuarios (solo la creación del mensaje y la simulación del envío, quedando este
registrado como enviado y no siendo necesario implementar ningún protocolo de
comunicación); y la consulta de mensajes recibidos (se simulará que existen ya
mensajes recibidos). Para acceder a todas esas opciones del código, la aplicación debe
disponer de algún tipo de interfaz que permita invocar la ejecución de la funcionalidad
implementada (P. Ej. interfaz modo texto en la consola). Se recomienda que, en el caso
de decidir abordar unas funcionalidades diferentes, se discuta primero su viabilidad con
el profesor de prácticas. En cualquier caso, debe poderse verificar una trazabilidad clara
entre las partes codificadas y los modelos previos de diseño y análisis. Opcionalmente,
se permitirá la incorporación de una interfaz gráfica de usuario y de una base de datos.
El proyecto a entregar debe tener la estructura generada por un proyecto Java básico de
Maven usando la plantilla: maven.archetype-quickstart.

4. Pruebas: será necesario que se realicen las pruebas unitarias correspondientes a parte del
código desarrollado. En concreto, se pide que cada integrante del grupo realice la prueba
unitaria de una clase implementada. Para ello, debe crear las pruebas necesarias para
verificar el correcto funcionamiento de todos los métodos de la clase. Las pruebas se
proporcionarán junto con el código, en la carpeta correspondiente dentro de la estructura
creada por el proyecto Maven.

5. Utilización del repositorio de código: en esta tercera entrega todos los grupos deberán
incluir en su repositorio de código del servidor de la asignatura, el código fuente generado.
Tal y como se mencionó en anteriores entregas, el repositorio debe estructurarse en tres
carpetas denominadas “código” (contendrá el código fuente y la carpeta de test con las
pruebas unitarias), “modelos” (donde se colocará el artefacto con extensión .uml, generado
a partir del proyecto de StarUML, así como todos los modelos generados exportados en
formato imagen) y la carpeta “documentación” (donde se colocará cualquier tipo de
documento adicional necesaria para la práctica. Dicho repositorio deberá sincronizarse con
el repositorio del servidor de la asignatura mediante la tecnología Subversion.