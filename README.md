# ProgettoPizzeria
L'applicazione rappresenta il servizio di backend che si occupa della gestione
degli ordini di una pizzeria.
Le tecnologie utilizzate sono java 17 con framework Spring Boot e database postgres.
Per i test è stato utilizzato Postgres come testcontainer per garantire che i test di integrazione
siamo tarati su un db compatibile con quello dell'applicazione.



1) Per lanciare l'applicazione con Docker in locale usare i seguenti comandi:

- docker build -t app-pizzeria .   (crea immagine docker)
- docker run -p 5432:5432 -e POSTGRES_PASSWORD=password -e POSTGRES_DB=db  postgres:16-alpine (lancia il db)
- docker run -p 8080:8080 app-pizzeria (fa partire container)
- da postmand richiamare http://localhost:8080/....

2) Per lanciare l'applicazione con docker-compose in locale usare i seguenti comandi:
- docker-compose up -d
- da postmand richiamare http://localhost:8080/....

3) Per lanciare l'applicazione in kubernetes
- kubectl apply -f deployment.yaml
- kubectl apply -f service.yaml
- kubectl port-forward service/app-pizzeria 8080:80
- da postmand richiamare http://localhost:8080/....


