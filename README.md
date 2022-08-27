## Running PostgreSQL and Flyway in Docker

The database can be viewed in pgAdmin that is inside the docker compose image
To view the database, go to pgAdmin at localhost:5050, log in with username: root@root.com and password: root1
And then connect to the server with name: postgres | hostname/url: postgres | port: 5432

Run PostgreSQL in Docker and apply schema using flyway, you have to run:
```
./run_in_docker.sh
```

Press Ctrl+C when you no longer need db to run. To remove instance from your local machine run:
```
./destroy_docker_instance.sh
```
