# Create a separate container for nginx

# Using nginx as the base image for the container
FROM nginx:1.24 as build

# Copy the build files in this directory over to the /dist directory in the container, the container will not initially have these files because it is using a new image
COPY dist dist

# Copy the default.conf file in this directory into the container at this specific location because nginx read their config at this location
COPY default.conf /etc/nginx/conf.d/default.conf

# Expose port 8080 of the container
EXPOSE 8080

# If you are wondering how do i know the location of where to copy the files, you can run the containers in interactive mode or run the docker in docker desktop and look at the files tab


