## Running docker compose
A docker compose file allows you to run multiple docker containers at once using a single command.
Which means instead of running 
```
docker run --rm -it -p 3000:3000 <image-name> 
```
You can run
```
docker compose up (to start all containers)
docker compose down (to stop all containers)
```

## Prerequisites
In order to run docker compose, each of your services (backend, frontend, database) must have a dockerfile.
To easily understand the docker-compose file, it is just a combination of all the dockerfiles commands above
written in a yaml file such as the --rm -p 3000:3000, the name of the container etc.


### Create a docker compose yaml file
Create docker-compose.yml file in the root directory of your project.

```
Refer to the sample compose.yaml file for more details on the syntax
```




