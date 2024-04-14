const express = require('express');
const router = express.Router();
const db = require('../database');

// GET /api/Kolcsonzesek
router.get('/', (req, res) => {
 db.all('SELECT * FROM Kolcsonzesek', [], (err, rows) => {
    if (err) {
      throw err;
    }
    res.json(rows);
 });
});

// További CRUD műveletek...

module.exports = router;
