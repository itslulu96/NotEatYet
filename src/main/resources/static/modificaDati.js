//anche questo è un binding
$(document).ready(function() {
    var idRistoranteDaModificare = (localStorage.getItem('cookieRistorante'));
    renderRistorante(idRistoranteDaModificare);
    // qui sto preparando il binding, non mettere le parentesi nelle funzioni che altrimenti le chiami
    $('#conferma-modifica').click(confermaModifica);
})

/*
function renderRistorante() {
		
		$.get(`ristoranti/${id}`, function(res) {
				$(`
                    <div>
                        ${res[i].ragione_sociale},
                        ${res[i].p_iva}
                        ${res[i].regione}
                        ${res[i].citta},
                        ${res[i].via}
                         ${res[i].n_civico},
                    </div>
                `).appendTo($('#form-modifica-dati'));
		})
		
		riempiCampiInput(res);
}
*/

function renderRistorante(id) {
		
		$.get(`ristoranti/${id}`, function(res) {
				riempiCampiInput(res);
		})
}

// assegno ai campi di input i valori da modificare
function riempiCampiInput(ristoranteDaModificare) {
    document.getElementById('ragioneSocialeModifica').value = ristoranteDaModificare.ragione_sociale;
    document.getElementById('pIvaModifica').value = ristoranteDaModificare.p_iva;
    document.getElementById('regioneModifica').value = ristoranteDaModificare.regione;
    document.getElementById('cittaModifica').value = ristoranteDaModificare.citta;
    document.getElementById('viaModifica').value = ristoranteDaModificare.via;
    document.getElementById('nCivicoModifica').value = ristoranteDaModificare.n_civico;
}


// qui avviene la modifica effettiva
// quando l'utente preme conferma, modifica legge i campi di input e se li salva nell'array
function confermaModifica() {

    // prendere i valori dei campi modificati però non solo quelli modificati tutti tutti
    // assegnare al ristorante i valori "nuovi" al posto dei valori presi da riempiCampiInput
    // sovrascrivi il ristorante salvato con quello modificato 
    
    // qui mi salvo il valore
    const ragione_sociale = $('#ragioneSocialeModifica').val();
    const p_iva = $('#pIvaModifica').val();
    const regione = $('#regioneModifica').val();
    const citta = $('#cittaModifica').val();
    const via = $('#viaModifica').val();
    const n_civico = $('#nCivicoModifica').val();
	var idRistoranteDaModificare = (localStorage.getItem('cookieRistorante'));
    const ristorante = new Ristorante(idRistoranteDaModificare, ragione_sociale, p_iva, regione, citta, via, n_civico);
    chiamataPutPerModificareRistorante(ristorante);

    window.location.href = "datiRistorante.html";
}

function chiamataPutPerModificareRistorante(ristorante) {
 
console.log(JSON.stringify(ristorante));

$.ajax({
    url: 'ristoranti',
    type: 'PUT',
    data: JSON.stringify(ristorante),
    contentType: "application/json; charset=utf-8",
  	dataType:"json",
    success: function(result) {
        console.log(result);
    }
});
}
// se metti qui la funzione te la esegue appena carica il file js e prima di tutto ci che sta nel ready