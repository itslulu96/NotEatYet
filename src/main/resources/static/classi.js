
class Ristorante {
       constructor(id, ragione_sociale, p_iva, regione, citta, via, n_civico, email, password) {
        this.id = id;
        this.ragione_sociale = ragione_sociale;
        this.p_iva = p_iva;
        this.regione = regione;
        this.citta = citta;
        this.via = via;
        this.n_civico = n_civico;
       
        if(email != undefined && password != undefined) {
			this.email = email;
        	this.password = password;
		}
    }

    renderRistorante() {
        return `<li>
                    <div>
                        ${this.ragione_sociale},
                        ${this.via}
                        ${this.n_civico},
                        ${this.citta},
                        ${this.regione}
                    </div>
                </li>`;
    }
    
   toString() {
		return this.ragione_sociale;
	}
}

class Piatto {
    constructor(id, nome, prezzo, categoria, vegano, vegetariano, ingredienti = [], idRistorante) {
        this.id = id;
        this.nome = nome;
        this.prezzo = prezzo;
        this.categoria = categoria;
        this.vegano = vegano;
        this.vegetariano = vegetariano;
        this.ingredienti = ingredienti; 
        this.idRistorante = idRistorante;
    }

    renderPiatto() {
        let risIngredienti = '';
        for(let i = 0; i < this.ingredienti.length; i++) {
            risIngredienti += this.ingredienti[i].renderIngrediente();
        }

        console.log("ID" + this.id);

        return `<div>
                    <p>${this.nome}</p>
                    <p>${this.prezzo}</p>
                    <p>${this.categoria}</p>
                    <p>Vegano: ${this.vegano}</p>
                    <p>Vegetariano: ${this.vegetariano}</p>
                    <p>Ingredienti:</p>
                    <ul class="ingredienti-piatto">${risIngredienti}</ul>
                    <button id="conferma-eliminazione" data-id="${this.id}" onclick="eliminaPiatto(${this.id})">&times;</button>
                    <input type="button" data-id="${this.id}" onclick='enterModificaPiatto(${this.id});' value="Modifica"/>
                </div>`;
                
    }

}

class Ingrediente {
    constructor(id, nome, idPiatto) {
        this.id = id;
        this.nome = nome;
        this.idPiatto = idPiatto;
    }

    renderIngrediente() {
        
        return `<span class="ingrediente${this.id}">${this.nome} </span>`;
    }
    
}

function httpGet(theUrl)
{
    var xmlHttp = new XMLHttpRequest();
    xmlHttp.open( "GET", theUrl, false ); // false for synchronous request
    xmlHttp.send( null );
    return xmlHttp.responseText;
}
