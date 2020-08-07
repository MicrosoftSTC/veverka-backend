const firebaseConfig = {
  apikey: process.env.API_KEY,
  authDomain: 'veverka-back.firebaseapp.com',
  databaseURL: 'https://veverka-back.firebaseio.com',
  projectId: 'veverka-back',
  storageBucket: 'veverka-back.appspot.com',
  messagingSenderId: process.env.MESSAGINGSENDERID,
  appId: process.env.APPID,
  measurementId: process.env.MEASUREMENTID,
};

firebase.initializeApp(firebaseConfig);
firebase.analytics();

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
