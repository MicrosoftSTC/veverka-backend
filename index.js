import * as admin from 'firebase-admin';

const express = require('express');

const app = express(); // express init

const port = process.env.PORT || 5000;

var admin = require("firebase-admin");

// Fetch the service account key JSON file contents
var serviceAccount = require("service-api-key.json");

// Initialize the app with a service account, granting admin privileges
admin.initializeApp({
  credential: admin.credential.cert(serviceAccount),
  databaseURL: "https://veverka-back.firebaseio.com"
});

// As an admin, the app has access to read and write all data, regardless of Security Rules
var db = admin.database();
var ref = db.ref("restricted_access/secret_document");
ref.once("value", function(snapshot) {
  console.log(snapshot.val());
});

app.use(express.urlencoded({ extended: false }));
app.use(express.static('assets'));

app.get('/', (_req, res) => {
  res.status(200);
  res.send('Initial route');
});

app.listen(port, () => {
  console.log(`Editor listening on port ${port}!`);
});
