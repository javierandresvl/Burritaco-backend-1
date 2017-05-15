# Burritaco

Creada por el equipo de desarrollo Futuchi, Burritaco es una aplicación web que permite conocer la percepción de los usuarios de Twitter sobre la congestión de tránsito en las comunas de la región Metropolitana.

Futuchile está integrado por:
1. Marcelo Morales
2. Sebastián Vallejos
3. Marcelo Muñoz
4. Javier Vásquez
5. Arthur Peña
6. Nicolás Olivares

Instrucciones:

Para ejecutar backend, se deben seguir los siguientes pasos:
1. se debe iniciar glassfish con el comando 'asadmin start-domain'
2. compilar el código, para eso se debe acceder a la carpeta donde se encuentra el código desde una terminal y ejecutar el comando 'gradle war' (lo que genera un archivo .war).
3. Finalmente se debe desplegar el .war generado en glassfish con el comando 'asadmin deploy "nombre del archivo war"'.

### A continuación se explica como acceder a los servicios de la aplicación.

#### LLENAR LAS CONGESTIONES
http://localhost:8080/Burritaco-backend/communes/create/id
Donde id corresponde al id de la comuna que van a llenar la tabla de congestiones.
Deben llenar las congestiones con las 53 comunas.

#### OBTENER EL ARREGLO PARA LOS GRAFICOS
http://localhost:8080/Burritaco-backend/congestions/id
Donde id correspone al id de la comuna a la cual consultaran el gráfico.

#### CREAR EL INDICE INVERTIDO (teniendo ya el mongo ejecutado y con datos)
http://localhost:8080/Burritaco-backend/main
PD: AL INSTALARLO EN EL VPS CAMBIAR LOS DIRECTORIOS DE LA CLASE searcher.java A UNA CORRESPONDIENTE CON EL VPS.
