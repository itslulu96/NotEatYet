$(document).ready(function() {
    renderPiatti();
    $('#elenco-piatti').on('click', '.btn-eliminaPiatto', function() {
		const id = +$(this).attr('data-id');
		
		deletePiatto(id);  
	})
})

/*let piatti = [new Piatto(0, 'Pancake', 4.50, 'Dolci', 'no', 'sì', 
               [new Ingrediente(1, 'uova'), 
               new Ingrediente(2, 'farina'), 
               new Ingrediente(3, 'latte'), 
               new Ingrediente(4, 'zucchero'),
               new Ingrediente(5, 'lievito')]), 
               new Piatto(1, 'Patatine', 4, 'Fritti', 'sì', 'sì', 
               [new Ingrediente(6, 'patate'), new Ingrediente(7, 'olio')]), 
               new Piatto(2, 'Hamburger vegetale', 7, 'Secondi', 'sì', 'sì', 
               [new Ingrediente(8, 'piselli')]),
               new Piatto(3, 'Polpette al sugo', 6, 'Secondi', 'no', 'no', 
              [new Ingrediente(9, 'carne')])];
*/

let piatti = [];
let ingredienti = [];


function renderPiatti() {
	const idRistorante = localStorage.getItem('cookieRistorante');
    $.get(`piatti/ristoranti/${idRistorante}`, function(res) {
        const categorie = [['rim', 'Primi'], ['econd', 'Secondi'], ['olc', 'Dolci']];
        console.log(categorie);
        piatti = res;
        for(let categoria of categorie) {
            $(`<h2>${categoria[1]}</h2>`).appendTo($('#elenco-piatti'));
            for(let i = 0; i < res.length; i++){
                if(res[i].categoria.slice(1, -1) != categoria[0]) {
                    console.log("Evaluating: " + categoria + "Skipped" + res[i].categoria);
                    continue;
                }
                let nomeIngr = '<div style="margin-left: 30px">';
                for (let k of res[i].ingredienti) {
                    nomeIngr += `<li>${k.nome.substring(0, 1).toUpperCase() + k.nome.substring(1)}</li>`;
                }
                nomeIngr+= '</div>';
                
                $(`
                <ul class="piatto"><a href="#">
                        <h4 style="text-align: center">${res[i].nome}</h4>` +
                        (res[i].vegetariano == "si" ? '<img src="immagini/vegetariano.png" style="width: 40px; margin-right: 10px;"></img>' : "" )+
                        (res[i].vegano == "si" ? '<img src="immagini/vegan.png" style="width: 35px"></img>' : "") +
                        `<p><span class="tag">${res[i].prezzo} &euro;</span></p>
                        <b class="ingredienti-piatto">Ingredienti:</b>${nomeIngr}
                            <div class="box">
                            <div class="buttonRight">
                            <input class="btn-modificaPiatto" type="button" data-id="${res[i].id}" onclick="enterModificaPiatto(${res[i].id});" value="Modifica"/>
                            <input class="btn-eliminaPiatto" type="button" id="conferma-eliminazione" data-id="${res[i].id}" value="x"/>
                            </div>
                            </div>
                            <!--button class="btn-eliminaPiatto" id="conferma-eliminazione" data-id="${res[i].id}">&times;</button-->
                        </a> 
                    </ul>`).appendTo($('#elenco-piatti')); 
            }
        }
    })
}
    
    
    //  <input type="button" data-id="${res[i].id}" onclick='enterModificaPiatto(${res[i].id});' value="Modifica"/>
    
/*function renderIngredienti() {
   return $.get('ingredienti', function(res) {
        for (let k = 0; k < res.length; k++) {
            $(`<li class="ingrediente${res[k].id}">${res[k].nome}</li>`).appendTo($('.ingredienti-piatto'));
        }
    })
}

/*


/*
function renderPiatti() {

	
	$.get('piatti', function(res) {
		for(let i = 0; i < res.length; i++) {
			$(`<li>
                    <div>
                        ${res[i].nome},
                        ${res[i].prezzo}
                        ${res[i].categoria},
                        ${res[i].vegano},
                        ${res[i].vegetariano}
                        ${res[i].ingredienti}
                    </div>
                </li>`).appendTo($('#elenco-piatti'));
		}
	})
	
   /* let ris = '';
    for(let p of piatti) {
        ris += p.renderPiatto();
    }

    $('#elenco-piatti').html(ris);
    
    }
    */


function enterModificaPiatto(idPiatto) {

console.log(piatti);
    for(p of piatti) {
        if(p.id == idPiatto) {
            var piattoDaModificare = p;
            localStorage.setItem('piattoDaMostrare', JSON.stringify(piattoDaModificare));
            console.log('ho mal di testa');
            location.href='modifica-piatto.html';
            return;
        }
    }

    console.error('Non ho trovato il piatto');

}

function deletePiatto(id) {
		$.ajax({
			url: `piatti/${id}`,
			type: 'DELETE',
			success: function(res) {
					$('#elenco-piatti').html('');
					renderPiatti();
			}
		})
	}

function eliminaPiatto(id) {
    console.log('sono nella funzione elimina piatto');
    
    for(let i = 0; i < piatti.length; i++) {
        if(piatti[i].id == id) {
        piatti.splice(i, 1);
        console.log('id piatto:' + id);
        console.log('I:' + i);
        break;
        }
    }

    renderPiatti();
}