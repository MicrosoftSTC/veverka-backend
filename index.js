const express = require('express');
const session = require('express-session');

const app = express(); // express init

const port = process.env.PORT || 5000;

app.use(express.urlencoded({ extended: false }));
app.use(express.static('assets'));

if (app.get('env') === 'production') {
  app.set('trust proxy', 1); // trust first proxy
  session.cookie.secure = true; // serve secure cookies
}

app.get('/', (_req, res) => {
  res.status(200);
  res.send('Initial route');
});

app.listen(port, () => {
  console.log(`Server listening on port ${port}!`);
});
