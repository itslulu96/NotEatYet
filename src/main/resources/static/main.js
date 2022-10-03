// localStorage rimane sul locale anche se chiudi le pagine del tuo sito web
// sessionStorage quando chiudi tutte le pagine del tuo sito web si cancella 

//  questo deve essere visualizzato quando la pagina si carica

/*
function renderRistoranti() {
    let ris = '';

    for (let r of ristoranti) {
        ris += r.renderRistorante();
    }

   $('#lista').html(ris);
}
*/

function renderRistoranti() {
		
		$.get('ristoranti', function(res) {
			for (let i = 0; i < res.length; i++) {
				$(`<div class="container">
                    <div class="row">
                    	<div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
                    		<div class="box-part text-center">
                    			<div class="title">
		                        	<h3 class="mt-2">${res[i].ragione_sociale}</h3>
		                        </div>
		                        <div class="text">
			                        <span class="mt-4">${res[i].via}
			                        ${res[i].n_civico},
			                        ${res[i].citta},
			                        ${res[i].regione}</span>
		                        </div>
		                    </div>
                        </div>
                    </div>
                </div>`).hide().appendTo($('#elenco-ristoranti')).fadeIn(i * 2000);
                
			}	
			
		})
}
	
function aggiungiRistorante() {
	 let email = $('#emailAggiungi').val();
    let password = $('#passwordAggiungi').val();
    let ragioneSociale = $('#ragioneSocialeAggiungi').val();
    let partitaIva = $('#pIvaAggiungi').val();
    let regione = $('#regioneAggiungi').val();
    let citta = $('#cittaAggiungi').val();
    let via = $('#viaAggiungi').val();
    let nCivico = $('#nCivicoAggiungi').val(); 
        
    console.log("Email: " + email);
    let ristoranteDaAggiungere = new Ristorante(0, ragioneSociale,
        partitaIva, regione, citta, via, nCivico, email, password);

	addRistorante(ristoranteDaAggiungere);
	
	window.location.href='pannello-controllo.html';
}
                            
function addRistorante(ristorante) {
	console.log(JSON.stringify(ristorante));
    //$.post('ristoranti', JSON.stringify(ristorante));
    $.ajax({
  		url: 'ristoranti',
  		type: "POST",
  		data: JSON.stringify(ristorante),
  		contentType:"application/json; charset=utf-8",
  		dataType:"json",
  		async: false,
  		success: function(result) {
        localStorage.setItem('cookieRistorante', result);
    }
	})
}

$(document).ready(function() {
    
    renderRistoranti();
    
    //PER IL LOGIN NON VA TOCCATO NESSUN ALTRO FILE: SI CREA HTML E JS NUOVO
    //ANDRà TOLTA SOLO DA QUI LA RIGA 91 E MESSA NELLA PAGINA DI LOGIN CORRETTO
    //consiglio: unica cosa da toccare ancora potrebbe essere fare un file con le chiamate che restituiscono un array di piatti/ristoranti con le varie casistiche/mappature utili
   // crea tabella con id ristorante, email e password
   // il backend controlla se il login è giusto (restituisce -1 se è sbagliato, altrimenti l'id del ristorante')
   // nel front end se ti arriva -1 fai uscire la scritta 'login errato' altrimenti setti la variabile [IL NOSTRO 2 QUI SOPRA!!!] all'id che ti viene restituito E FINE!!!!!!!!!!!!!!!!!!!
   //bonus: prendere il nome del ristorante nel benvenuto lmao
    $('#conferma-aggiunta').click(aggiungiRistorante);
})