# Description: Dockerfile for backend

# Using node:16.12 as the base environment
FROM node:16.12-slim as base
LABEL authors="Ben"
# The working directory is where all the next commands will be executed
WORKDIR /
# Copy packe.json and package-lock.json to the working directory
COPY package*.json .

# We may sometimes want to create an image from another image. This is known as a multistage build.
# Creating the development image using the base image above
FROM base as development
# Set the environment to development, not needed if you have a docker compose file
ENV NODE_ENV=development
# Set the cache directory to /src/app/.npm (this can be any location) so that the node_modules folder is cached and speed up the build process
RUN --mount=type=cache,target=/src/app/.npm \
    npm set cache /src/app/.npm && \
    npm ci # Run npm clean install

# Copy everything
COPY . .
# Run node server.js
CMD ["node", "server.js"]


# Production image
FROM base as Production
ENV NODE_ENV=production

RUN --mount=type=cache,target=/src/app/.npm \
    npm set cache /src/app/.npm && \
    npm ci --only=production

ARG PORT=9999
ENV PORT=${PORT}
EXPOSE $PORT

COPY . .
CMD ["node", "server.js"]
