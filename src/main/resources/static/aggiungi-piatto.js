function aggiungiPiatto() {
    let nome = $('#nome-aggiungiPiatto').val();
    let prezzo = $('#prezzo-aggiungiPiatto').val();
    let categoria = $('#categoria-aggiungiPiatto').val();
    let vegano = $('#vegano-aggiungiPiatto').val();
    let vegetariano = $('#vegetariano-aggiungiPiatto').val(); 
    let ingredientiDaAggiungere = [];
    ingredientiDaAggiungere = getIngredientiFromInput();
   const idRistorante = localStorage.getItem('cookieRistorante'); 
    
    let piattoDaAggiungere = new Piatto(0, nome, prezzo, categoria, vegano, vegetariano, ingredientiDaAggiungere, idRistorante);
    
    addPiatto(piattoDaAggiungere);

	/*
    $('#nome-aggiungiPiatto').val('');
    $('#prezzo-aggiungiPiatto').val('');
    $('#categoria-aggiungiPiatto').val('');
    $('#vegano-aggiungiPiatto').val('');
    $('#vegetariano-aggiungiPiatto').val('');
    $('#ingrediente-0').val('');
    */
    
    window.location.href = "lista-piatti.html";

	/*
    document.getElementById("aggiuntaInput").innerHTML 
    = '<p>Ingrediente:</p><input type="text" id="ingrediente-0">';
	*/
} 

function addPiatto(piatto) {
	console.log(JSON.stringify(piatto));
    $.ajax({
  		url: 'piatti',
  		type: "POST",
  		data: JSON.stringify(piatto),
  		contentType:"application/json; charset=utf-8",
  		dataType:"json"
	})
}

var count = 0;
function aggiungiInput() {
    console.log(count);
    //i++; ++i; i=i+1; i+=1; 
    $("#aggiuntaInput").append('<input type="text" id="ingrediente-' + ++count +'">'); 
}


function getIngredientiFromInput() {

    let ingredientiFromInput = [];
    for(let i = 0; i <= count; i++) {
        // post per creare l'ingrediente che mi resistuisce l'id dell'ingrediente
        // lo usi al posto dell'id ingrediente di default che hai messo qui
        ingredientiFromInput.push(new Ingrediente(0, $('#ingrediente-' + i).val())); //SI ROMPE
        console.log(ingredientiFromInput);
    }

    return ingredientiFromInput;

}

$(document).ready(function() {

    $('#aggiungi-piatto').click(aggiungiPiatto);
    //$('#lista-ingrediente').click(aggiungiInput);
})

