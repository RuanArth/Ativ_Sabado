let estoque = [];

function adicionarProduto(id, nome, qtd) {
    estoque.push({ id, nome, qtd });
}

function listarProdutos() {
    return estoque;
}

function removerProduto(id) {
    estoque = estoque.filter(produto => produto.id !== id);
}

function editarQuantidade(id, qtd) {
    const produto = estoque.find(produto => produto.id === id);
    if (produto) {
        produto.qtd = qtd;
    }
}

module.exports = {
    adicionarProduto,
    listarProdutos,
    removerProduto,
    editarQuantidade
};
