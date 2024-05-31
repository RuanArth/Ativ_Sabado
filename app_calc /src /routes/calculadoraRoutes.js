const express = require('express');
const router  = express.Router();
const calculadoraController = require('../controller/calculadoraController');

router.get('/', calculadoraController.index);
router.post('/resultado', calculadoraController.resultado);

module.exports = router;
