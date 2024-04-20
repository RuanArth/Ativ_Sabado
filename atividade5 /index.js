const express = require('express');
const estoque = require('./util/estoque');
const app = express();
app.use(express.json());

app.get('/listar', (req, res) => {
    res.json(estoque.listarProdutos());
});

app.post('/adicionar/:id/:nome/:qtd', (req, res) => {
    const { id, nome, qtd } = req.params;
    estoque.adicionarProduto(id, nome, parseInt(qtd));
    res.send('Produto adicionado com sucesso!');
});

app.delete('/remover/:id', (req, res) => {
    const { id } = req.params;
    estoque.removerProduto(id);
    res.send('Produto removido com sucesso!');
});

app.put('/editar/:id/:qtd', (req, res) => {
    const { id, qtd } = req.params;
    estoque.editarQuantidade(id, parseInt(qtd));
    res.send('Quantidade do produto editada com sucesso!');
});

const PORT = 8080;
app.listen(PORT, function () {
    console.log('app rodando na porta ' + PORT);
});
