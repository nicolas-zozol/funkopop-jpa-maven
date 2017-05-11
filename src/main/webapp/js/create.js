/**
 * Created by nicorama on 06/04/2017.
 */

$('button').on('click', create);

function create(){

    const name = $('input[name=name]').val();
    const universeId=$('select').val();

    const data = {
        name,
        universe:{
            id:universeId
            }
        };

    fetch('/api/pops', {
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        method: "POST",
        body: JSON.stringify(data)
    });


}