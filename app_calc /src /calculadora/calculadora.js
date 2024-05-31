function calcular(valor1, valor2, operacao) {
    let resultado;
    switch (operacao) {
        case '+': resultado = valor1 + valor2; break;
        case '-': resultado = valor1 - valor2; break;
        case '*': resultado = valor1 * valor2; break;
        case '/': resultado = valor1 / valor2; break;
    }
    return resultado;
}

function nomeOperador(operacao) {
    let nomeOperacao;
    switch (operacao) {
        case '+': nomeOperacao = "soma"; break;
        case '-': nomeOperacao = "subtração"; break;
        case '*': nomeOperacao = "multiplicação"; break;
        case '/': nomeOperacao = "divisão"; break;
    }
    return nomeOperacao;
}

module.exports = {
    calcular,
    nomeOperador
}
