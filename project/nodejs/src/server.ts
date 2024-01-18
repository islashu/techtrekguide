import {Request, Response} from "express";
import {createUser} from "./repository/user.repo";
const express = require('express');
const PORT:number | string = process.env.PORT || 3000;
const app = express();

app.get('/', (req: Request, res: Response) => {
  res.send('Hello World!');
});

createUser().then(()=> {
  console.log("Prisma test")
})

app.listen(PORT, () => {
  console.log('Example app listening on port + ' + PORT + '!');
})





