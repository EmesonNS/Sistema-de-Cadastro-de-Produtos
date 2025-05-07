package tela;

import modelo.Produto;
import modelo.ProdutoRepository;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ProdutoList extends JFrame {
    private final DefaultListModel<String> listModel;
    private final JList<String> produtoJList;
    private final List<Integer> indicesOriginais;

    public ProdutoList() {
        setTitle("Listagem de Produtos");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        listModel = new DefaultListModel<>();
        produtoJList = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(produtoJList);
        indicesOriginais = new ArrayList<>();

        configurarBotoes();
        getContentPane().add(scrollPane, BorderLayout.CENTER);
        atualizarLista();
    }

    private void configurarBotoes() {
        JPanel botoesPanel = new JPanel();

        JButton cadastrarButton = new JButton("Cadastrar");
        cadastrarButton.addActionListener(e -> abrirFormulario(null, -1));

        JButton editarButton = new JButton("Editar");
        editarButton.addActionListener(e -> editarProdutoSelecionado());

        JButton excluirButton = new JButton("Excluir");
        excluirButton.addActionListener(e -> excluirProdutoSelecionado());

        botoesPanel.add(cadastrarButton);
        botoesPanel.add(editarButton);
        botoesPanel.add(excluirButton);
        getContentPane().add(botoesPanel, BorderLayout.SOUTH);
    }

    private void abrirFormulario(Produto produto, int index) {
        ProdutoForm form = new ProdutoForm(this, produto, index);
        form.setVisible(true);
    }

    private void editarProdutoSelecionado() {
        int indexVisual = produtoJList.getSelectedIndex();
        if (indexVisual >= 0) {
            int indexReal = indicesOriginais.get(indexVisual);
            Produto produto = ProdutoRepository.getInstance().getProdutos().get(indexReal);
            abrirFormulario(produto, indexReal);
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um produto para editar.");
        }
    }

    private void excluirProdutoSelecionado() {
        int indexVisual = produtoJList.getSelectedIndex();
        if (indexVisual >= 0) {
            int indexReal = indicesOriginais.get(indexVisual);
            ProdutoRepository.getInstance().remover(indexReal);
            atualizarLista();
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um produto para excluir.");
        }
    }

    public void atualizarLista() {
        List<Produto> produtos = ProdutoRepository.getInstance().getProdutos();
        List<ProdutoComIndice> produtosOrdenados = ordenarProdutosPorValor(produtos);

        listModel.clear();
        indicesOriginais.clear();

        for (ProdutoComIndice pci : produtosOrdenados) {
            indicesOriginais.add(pci.indice);
            listModel.addElement(pci.produto.toString());
        }
    }

    private List<ProdutoComIndice> ordenarProdutosPorValor(List<Produto> produtos) {
        List<ProdutoComIndice> produtosComIndice = new ArrayList<>();
        for (int i = 0; i < produtos.size(); i++) {
            produtosComIndice.add(new ProdutoComIndice(i, produtos.get(i)));
        }
        produtosComIndice.sort((a, b) ->
                Double.compare(a.produto.getValor(), b.produto.getValor()));
        return produtosComIndice;
    }

    private static class ProdutoComIndice {
        final int indice;
        final Produto produto;

        ProdutoComIndice(int indice, Produto produto) {
            this.indice = indice;
            this.produto = produto;
        }
    }
}