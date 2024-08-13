function calcularValor(preco, idPrato) {
        var quantidade = document.getElementById('quantidadeSppiner-' + idPrato).value;
        if (quantidade < 1) {
            alert("A quantidade deve ser pelo menos 1.");
            return false;
        }
        var valor = preco * quantidade;
        var link = document.getElementById('link-' + idPrato);
        link.href = '/adicionar-pedido?id=' + idPrato + '&valor=' + valor;
        return true;
}


