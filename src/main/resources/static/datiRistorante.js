$(document).ready(function() {

//qui faccio una get, che mi restituisce il JSON 
//il JSON deve avere le variabili con lo stesso nome che abbiamo qui su js altrimenti non riesce a mapparle
//il setItem invece è una put
//con localStorage puoi salvarti l'id, ricorda
/*var ristoranteDaMostrare = JSON.parse(localStorage.getItem('ristoranteDaMostrare'));

document.getElementById('ragioneSocialeDati').value = ristoranteDaMostrare.ragione_sociale;
    document.getElementById('pIvaDati').value = ristoranteDaMostrare.p_iva;
    document.getElementById('regioneDati').value = ristoranteDaMostrare.regione;
    document.getElementById('cittaDati').value = ristoranteDaMostrare.citta;
    document.getElementById('viaDati').value = ristoranteDaMostrare.via;
    document.getElementById('nCivicoDati').value = ristoranteDaMostrare.n_civico;
  */
     var idRistoranteDaModificare = (localStorage.getItem('cookieRistorante'));
     renderRistorante(idRistoranteDaModificare);
    $('#dati-ristorante').click(renderRistorante);

/*
    function getRistorante(id) {
    $.get(`ristoranti/${id}`, function(res) {
        $('#ragioneSocialeDati').val(res.ragione_sociale);
        $('#pIvaDati').val(res.p_iva);
        $('#regioneDati').val(res.regione);
        $('#cittaDati').val(res.citta);
        $('#viaDati').val(res.via);
        $('#nCivicoDati').val(res.n_civico);
        })
    }
    */

}) 

function riempiCampiInput(ristoranteDaModificare) {
    document.getElementById('ragioneSocialeDati').value = ristoranteDaModificare.ragione_sociale;
    document.getElementById('pIvaDati').value = ristoranteDaModificare.p_iva;
    document.getElementById('regioneDati').value = ristoranteDaModificare.regione;
    document.getElementById('cittaDati').value = ristoranteDaModificare.citta;
    document.getElementById('viaDati').value = ristoranteDaModificare.via;
    document.getElementById('nCivicoDati').value = ristoranteDaModificare.n_civico;
}

function renderRistorante(id) {
		
		$.get(`ristoranti/${id}`, function(res) {
				riempiCampiInput(res);
		})
}