# Burritaco-backend
BURRITACO

Creada por el equipo de desarrollo Futuchi, Burritaco es una aplicación web que permite conocer la percepción de los usuarios de Twitter sobre la congestión de tránsito en las comunas de la región Metropolitana.

Futuchile está integrado por:

Marcelo Morales Sebastián Vallejos Marcelo Muñoz Javier Vásquez Arthur Peña Nicolás Olivares

RECORDAR: CAMBIAR LA DIRECCION DEL INDICE INVERTIDO A SU COMPUTADOR O VPS
          CAMBIAR LA CLAVE DE NEO4J A SU COMPUTADOR O VPS

LLENAR LAS CONGESTIONES
http://localhost:4848/Burritaco-backend/communes/create/id
donde id corresponde al id de la comuna que van a llenar la tabla de congestiones.
deben llenar las congestiones con las 53 comunas.

OBTENER EL ARREGLO PARA LOS GRAFICOS
http://localhost:4848/Burritaco-backend//congestions/id
donde id correspone al id de la comuna a la cual consultaran el gráfico.

CREAR EL INDICE INVERTIDO (teniendo ya el mongo ejecutado y con datos)
http://localhost:4848/Burritaco-backend/main
PD: AL INSTALARLO EN EL VPS CAMBIAR LOS DIRECTORIOS DE LA CLASE searcher.java A UNA CORRESPONDIENTE CON EL VPS.

CREAR LA BASE DE DATOS DE GRAFOS CON LOS DATOS DE MONGO (teniendo datos en mongo y el índice invertido creado).
http://localhost:4848/Burritaco-backend/communes/create/nodes

SERVICIO DE LAS CONSULTAS AL GRAFO (hecho por Marcelo)
http://localhost:4848/Burritaco-backend//neo4j/nodes
