import tela.ProdutoList;
import javax.swing.*;

public class Play {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ProdutoList tela = new ProdutoList();
            tela.setVisible(true);
        });
    }
}
