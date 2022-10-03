var piattoDaModificare;
$(document).ready(function() {
    piattoDaModificare = JSON.parse(localStorage.getItem('piattoDaMostrare'));
    riempiCampiInput(piattoDaModificare);
    // qui sto preparando il binding, non mettere le parentesi nelle funzioni che altrimenti le chiami
    $('#conferma-modifica').click(confermaModifica);
    $('#ingredienti-da-modificare').on('click', '.btn-eliminaIngrediente', eliminaIngrediente);
})

var countIngredienti = 0; 

function riempiCampiInput(piattoDaModificare) {
    document.getElementById('nome-piattoDaModificare').value = piattoDaModificare.nome;
    document.getElementById('prezzo-piattoDaModificare').value = piattoDaModificare.prezzo;
    document.getElementById('categoria-piattoDaModificare').value = piattoDaModificare.categoria;
    document.getElementById('vegano-piattoDaModificare').value = piattoDaModificare.vegano;
    document.getElementById('vegetariano-piattoDaModificare').value = piattoDaModificare.vegetariano;

    for(let i = 0; i < piattoDaModificare.ingredienti.length; i++) {
        $("#ingredienti-da-modificare").append('<div id="div-' + i +'"><br><input type="text" id="ingrediente-' + i +'">' + 
        ' <button type="button" class="btn-eliminaIngrediente" data-id="ingrediente-' + i + '"> Elimina</button></div>');
        $("#ingrediente-"+ i).val(piattoDaModificare.ingredienti[i].nome);
    }
}

function aggiungiInputIngrediente() { 
    $("#ingredienti-da-modificare").append('<div id="div-' + ++countIngredienti +'"><br><input type="text" id="ingrediente-' + countIngredienti +'">' + ' <button type="button" class="btn-eliminaIngrediente" data-id="ingrediente-' + countIngredienti + '"> Elimina</button></div>');
}

function eliminaIngrediente() {
    let ingredienti = piattoDaModificare.ingredienti;
    const idIngrediente = $(this).attr('data-id');
    let idI = +idIngrediente.substring(12);
    for (let i = 0; i < ingredienti.length; i++) { 
        if (ingredienti[i].id === idI) {
            ingredienti.splice(i, 1);
            break;
        }
        
    }
    console.log(ingredienti);
    $(`#div-${idI}`).remove();
    //document.querySelector('[data-id="ingrediente-' + idI + '"]').remove();
}



function confermaModifica() {
	var piattoInModifica = JSON.parse(localStorage.getItem('piattoDaMostrare'));
    const nome = $('#nome-piattoDaModificare').val();
    const prezzo = $('#prezzo-piattoDaModificare').val();
    const categoria = $('#categoria-piattoDaModificare').val();
    const vegano = $('#vegano-piattoDaModificare').val();
    const vegetariano = $('#vegetariano-piattoDaModificare').val();
    const ingredienti = [];

    for(let i = 1; i <= countIngredienti; i++) {
        ingredienti.push(new Ingrediente(-1, $("#ingrediente-"+ i).val(), piattoInModifica.id));
    }
    console.log('Gli ingredienti sono:' + ingredienti);
	
	let idRistorante = localStorage.getItem('cookieRistorante');
    const piatto = new Piatto(piattoDaModificare.id, nome, prezzo, categoria, vegano, vegetariano, ingredienti, idRistorante);
    localStorage.setItem('piattoDaMostrare', JSON.stringify(piatto));
    chiamaPutPerModificarePiatto(piatto);

    window.location.href = "lista-piatti.html";
}

function chiamaPutPerModificarePiatto(piatto) {
 
console.log(JSON.stringify(piatto));

$.ajax({
    url: 'piatti',
    type: 'PUT',
    data: JSON.stringify(piatto),
    contentType: "application/json; charset=utf-8",
  	dataType:"json",
    success: function(result) {
        console.log(result);
    }
});

}