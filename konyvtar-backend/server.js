const express = require('express');
const app = express();
const port = 3000;
const kolcsonzokRouter = require('./routes/kolcsonzok');
const kolcsonzesekRouter = require('./routes/kolcsonzesek');

app.use(express.json() );

app.use('/api/Kolcsonzok', kolcsonzokRouter);
app.use('/api/Kolcsonzesek', kolcsonzesekRouter);

app.listen(port, () => {
 console.log(`Server running at http://localhost:${port}`);
});
