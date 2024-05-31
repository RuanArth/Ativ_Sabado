const calculadora = require('../calculadora/calculadora');

function index(req, res) {
    let erro = req.query.erro_valor;
    res.render('index.html', { erro });
}

function resultado(req, res) {
    let valor1 = Number(req.body.valor1);
    let valor2 = Number(req.body.valor2);
    let operacao = req.body.operacao;
    let resultado;
    let nomeOperacao = calculadora.nomeOperador(operacao);
    
    if (isNaN(valor1) || isNaN(valor2)) {
        res.redirect('/?erro_valor=true');
    } else {
        resultado = calculadora.calcular(valor1, valor2, operacao);
        res.render('resultado.html', { valor1, valor2, nomeOperacao, resultado });
    }
}

module.exports = {
    index,
    resultado
}
