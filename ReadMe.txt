Practica dse Fundamentos de ingeniera del software para la UPM, un chat "funcional" 
Enunciado:
El plazo m�ximo para realizar la tercera entrega de la pr�ctica obligatoria se indicar� en la tarea
de Moodle correspondiente. En esta entrega se deber�n realizar las siguientes actividades:

1. Diagrama de componentes: se debe organizar la aplicaci�n en componentes
arquitect�nicos y generar un diagrama de componentes que los relacione a trav�s de sus
interfaces.

2. Diagrama de despliegue: se realizar�, un diagrama de despliegue ideal en el que pueden
considerarse tantos nodos como se desee, organizando dichos nodos como si se fuera a
disponer de la aplicaci�n en su totalidad.

3. C�digo fuente: a partir de todos los modelos creados se codificar� una parte de la
aplicaci�n WhatsUPM. Queda a elecci�n de cada grupo la cantidad de funcionalidades
a codificar. No obstante, se sugiera codificar, al menos, las operaciones b�sicas de
gesti�n de la agenda (Alta, Baja, Modificaci�n y Borrado); el env�o de un mensaje entre
dos usuarios (solo la creaci�n del mensaje y la simulaci�n del env�o, quedando este
registrado como enviado y no siendo necesario implementar ning�n protocolo de
comunicaci�n); y la consulta de mensajes recibidos (se simular� que existen ya
mensajes recibidos). Para acceder a todas esas opciones del c�digo, la aplicaci�n debe
disponer de alg�n tipo de interfaz que permita invocar la ejecuci�n de la funcionalidad
implementada (P. Ej. interfaz modo texto en la consola). Se recomienda que, en el caso
de decidir abordar unas funcionalidades diferentes, se discuta primero su viabilidad con
el profesor de pr�cticas. En cualquier caso, debe poderse verificar una trazabilidad clara
entre las partes codificadas y los modelos previos de dise�o y an�lisis. Opcionalmente,
se permitir� la incorporaci�n de una interfaz gr�fica de usuario y de una base de datos.
El proyecto a entregar debe tener la estructura generada por un proyecto Java b�sico de
Maven usando la plantilla: maven.archetype-quickstart.

4. Pruebas: ser� necesario que se realicen las pruebas unitarias correspondientes a parte del
c�digo desarrollado. En concreto, se pide que cada integrante del grupo realice la prueba
unitaria de una clase implementada. Para ello, debe crear las pruebas necesarias para
verificar el correcto funcionamiento de todos los m�todos de la clase. Las pruebas se
proporcionar�n junto con el c�digo, en la carpeta correspondiente dentro de la estructura
creada por el proyecto Maven.

5. Utilizaci�n del repositorio de c�digo: en esta tercera entrega todos los grupos deber�n
incluir en su repositorio de c�digo del servidor de la asignatura, el c�digo fuente generado.
Tal y como se mencion� en anteriores entregas, el repositorio debe estructurarse en tres
carpetas denominadas �c�digo� (contendr� el c�digo fuente y la carpeta de test con las
pruebas unitarias), �modelos� (donde se colocar� el artefacto con extensi�n .uml, generado
a partir del proyecto de StarUML, as� como todos los modelos generados exportados en
formato imagen) y la carpeta �documentaci�n� (donde se colocar� cualquier tipo de
documento adicional necesaria para la pr�ctica. Dicho repositorio deber� sincronizarse con
el repositorio del servidor de la asignatura mediante la tecnolog�a Subversion.