const express = require('express');
const app = express();

// Estrutura de dados simulada para o estoque
let estoque = [];

// Rota para adicionar um novo produto ao estoque
app.get('/adicionar/:id/:nome/:qtd', (req, res) => {
    const { id, nome, qtd } = req.params;
    estoque.push({ id, nome, quantidade: parseInt(qtd) });
    res.send('Produto adicionado ao estoque.');
});

// Rota para listar todos os produtos do estoque
app.get('/listar', (req, res) => {
    res.json(estoque);
});

// Rota para remover um produto do estoque
app.get('/remover/:id', (req, res) => {
    const { id } = req.params;
    estoque = estoque.filter(produto => produto.id !== id);
    res.send('Produto removido do estoque.');
});

// Rota para editar a quantidade de um produto no estoque
app.get('/editar/:id/:qtd', (req, res) => {
    const { id, qtd } = req.params;
    const index = estoque.findIndex(produto => produto.id === id);
    if (index !== -1) {
        estoque[index].quantidade = parseInt(qtd);
        res.send('Quantidade do produto atualizada.');
    } else {
        res.send('Produto não encontrado no estoque.');
    }
});

// Configuração da porta do servidor
const PORT = process.env.PORT || 3000;
app.listen(PORT, () => {
    console.log(`Servidor rodando na porta ${PORT}`);
});

