window.addEventListener('load', function () {

    //Al cargar la pagina buscamos y obtenemos el formulario donde estarán
    //los datos que el usuario cargará de la nueva pelicula
    const formulario = document.querySelector('#add_new_odontologo');

    //Ante un submit del formulario se ejecutará la siguiente funcion

    formulario.addEventListener('submit', function (event) {

       //creamos un JSON que tendrá los datos de la nueva película
        const formData = {
            apellido: document.querySelector('#apellido').value,
            nombre: document.querySelector('#nombre').value,
            matricula: document.querySelector('#matricula').value,

        };

        //invocamos utilizando la función fetch la API peliculas con el método POST que guardará
        //la película que enviaremos en formato JSON
        const url = '/odontologos';
        const settings = {
            method: 'POST',
            headers: {
                   'Content-Type': 'application/json',
                     },
            body: JSON.stringify(formData)
        }

        fetch(url,settings)
              .then(response => response.json())
              .then(data => {
              //recorremos la colección de peliculas del JSON
                 for(odontologo of data){
                    //por cada pelicula armaremos una fila de la tabla
                    //cada fila tendrá un id que luego nos permitirá borrar la fila si eliminamos la pelicula
                    var table = document.getElementById("odontologoTable");
                    var odontologoRow =table.insertRow();
                    let tr_id = 'tr_' + odontologo.id;
                    odontologoRow.id = tr_id;

                    //por cada pelicula creamos un boton delete que agregaremos en cada fila para poder eliminar la misma
                    //dicho boton invocara a la funcion de java script deleteByKey que se encargará
                    //de llamar a la API para eliminar una pelicula
                    let deleteButton = '<button' +
                                              ' id=' + '\"' + 'btn_delete_' + odontologo.id + '\"' +
                                              ' type="button" onclick="deleteBy('+odontologo.id+')" class="btn btn-danger btn_delete">' +
                                              '&times' +
                                              '</button>';

                    //por cada pelicula creamos un boton que muestra el id y que al hacerle clic invocará
                    //a la función de java script findBy que se encargará de buscar la pelicula que queremos
                    //modificar y mostrar los datos de la misma en un formulario.
                    let updateButton = '<button' +
                                              ' id=' + '\"' + 'btn_id_' + odontologo.id + '\"' +
                                              ' type="button" onclick="findBy('+odontologo.id+')" class="btn btn-info btn_id">' +
                                              odontologo.id +
                                              '</button>';

                    //armamos cada columna de la fila
                    //como primer columna pondremos el boton modificar
                    //luego los datos de la pelicula
                    //como ultima columna el boton eliminar
                    odontologoRow.innerHTML = '<td>' + updateButton + '</td>' +
                            '<td class=\"td_nombre\">' + odontologo.nombre.toUpperCase() + '</td>' +
                            '<td class=\"td_apellido\">' + odontologo.apellido.toUpperCase() + '</td>' +
                            '<td class=\"td_matricula\">' + odontologo.matricula.toUpperCase() + '</td>' +
                            '<td>' + deleteButton + '</td>';

                };

            })
            })


            });
