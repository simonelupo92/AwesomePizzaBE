# Awesome Pizza
## API realizzate
Il basepath è "/orders"
### createOrder()
createOrder() crea un nuovo ordine, con una post, assegnando un orderCode randomico e fissa la data di creazione nel momento in cui viene creato.
### getOrder()
getOrder() è una chiamata get che serve per vedere a che punto di lavorazione si trova l'ordine e la data dell'ultimo aggiornamento su quell'ordine.
### getAll()
getAll() è una get utilizzata per reperire tutti gli ordini, anche quelli già completati.
### takeNextOrder()
takeNextOrder() è utilizzata dal pizzaiolo per prendere in carico l'ordine successivo. Se ha già un ordine in corso di lavorazione non ne può prendere altri.
### completeOrder(orderCode)
completeOrder(orderCode) è utilizzata per comunicare che un'ordine è stato completato

## Test
### createOrder()
### completeOrder(orderCode)
### getOrder()
### takeNextOrder()