package tela;

import modelo.Produto;
import modelo.ProdutoRepository;

import javax.swing.*;
import java.awt.*;

public class ProdutoForm extends JFrame {
    private JTextField nomeField;
    private JTextField descricaoField;
    private JTextField valorField;
    private JCheckBox disponivelCheck;
    private final ProdutoList produtoList;
    private final Produto produtoEdicao;
    private final int indexEdicao;

    public ProdutoForm(ProdutoList produtoList, Produto produtoEdicao, int indexEdicao) {
        this.produtoList = produtoList;
        this.produtoEdicao = produtoEdicao;
        this.indexEdicao = indexEdicao;

        setTitle(produtoEdicao == null ? "Cadastrar Produto" : "Editar Produto");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(5, 2));
        adicionarCampos(panel);
        adicionarBotaoSalvar(panel);
        getContentPane().add(panel);

        if (produtoEdicao != null) {
            preencherCampos(produtoEdicao);
        }
    }

    private void adicionarCampos(JPanel panel) {
        panel.add(new JLabel("Nome:"));
        nomeField = new JTextField();
        panel.add(nomeField);

        panel.add(new JLabel("Descrição:"));
        descricaoField = new JTextField();
        panel.add(descricaoField);

        panel.add(new JLabel("Valor (R$):"));
        valorField = new JTextField();
        panel.add(valorField);

        panel.add(new JLabel("Disponível:"));
        disponivelCheck = new JCheckBox("Sim");
        panel.add(disponivelCheck);
    }

    private void adicionarBotaoSalvar(JPanel panel) {
        JButton salvarButton = new JButton("Salvar");
        salvarButton.addActionListener(e -> salvarProduto());
        panel.add(salvarButton);
    }

    private void preencherCampos(Produto produto) {
        nomeField.setText(produto.getNome());
        descricaoField.setText(produto.getDescricao());
        valorField.setText(String.valueOf(produto.getValor()));
        disponivelCheck.setSelected(produto.isDisponivel());
    }

    private void salvarProduto() {
        try {
            Produto produto = criarProduto();
            ProdutoRepository repo = ProdutoRepository.getInstance();

            if (produtoEdicao == null) {
                repo.adicionar(produto);
            } else {
                repo.atualizar(indexEdicao, produto);
            }

            produtoList.atualizarLista();
            dispose();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Valor inválido! Use números (ex: 10.50).");
        }
    }

    private Produto criarProduto() throws NumberFormatException {
        String nome = nomeField.getText().trim();
        String descricao = descricaoField.getText().trim();
        double valor = Double.parseDouble(valorField.getText().trim());
        boolean disponivel = disponivelCheck.isSelected();

        return new Produto(nome, descricao, valor, disponivel);
    }
}
