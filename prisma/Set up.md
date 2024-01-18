
## Installing prisma
```
npm install @prisma/client
```

## Using prisma CLI

## Initialise prisma CLI
```
npx prisma
```

## Format file with proper indexes
```
npx prisma format
```

## Update changes from schema to generator (required when update not shown after migration)
```
npx prisma generate
```
if still not update, restart IDE.

## Create a prisma folder with schema.prisma
```
npx prisma init
```

## Connect your database with prisma 
You will need to have a .env file to store the url of the database
```
Syntax:
postgresql://USER:PASSWORD@HOST:PORT/DATABASE?schema=SCHEMA

USER: The name of your database user
PASSWORD: The password for your database user
HOST: The name of your host name (for the local environment, it is localhost)
PORT: The port where your database server is running (typically 5432 for PostgreSQL)
DATABASE: The name of the database
SCHEMA: The name of the schema inside the database
```
```
In .env file:
e.g. DATABASE_URL="postgresql://johndoe:randompassword@localhost:5432/mydb?schema=public"
```

## Applying changes to your database after editing schema.prisma
```
npx prisma migrate dev --name <Name of migrate (Anything does not matter)>

npx prisma db push

```
## Reset/Drop database
```
npx prisma db push --force-reset
```

