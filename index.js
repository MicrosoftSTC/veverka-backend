import * as admin from 'firebase-admin';

const express = require('express');

const app = express(); // express init

const port = process.env.PORT || 5000;

app.use(express.urlencoded({ extended: false }));
app.use(express.static('assets'));

app.get('/', (_req, res) => {
  res.status(200);
  res.send('Initial route');
});

app.listen(port, () => {
  console.log(`Editor listening on port ${port}!`);
});
