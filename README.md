# umleditor

Esta esta una plicación demostrativa del uso de técnicas SOLID en el desarrollo de aplicaciones.

Simula la interación entre entidades UML como clases e interfaces y algunos conectores como implements, exteds, use y otras.

El método main se encuentra en la clase UmlEditor.

En la demo, primeramente se crean las reglas que defien si una entidad puede enlazarse con otra. Estas reglas se pasan como parametro a cada entidad uml que se crea. Luego se crea una lista de instancias de los conectores y una instancia de interface y class; a continuación se elige un conector, aleatoriamente e intenta enlazarlo con la interfaz y con la clase, si es exitoso mostrará en pantalla el texto con el nombre del conector y las entidades enlazadas. En caso de no poder enlazar, debido a las reglas creadas, se muestra un texto indicativo y el conector con elementos enlazados vacio.
