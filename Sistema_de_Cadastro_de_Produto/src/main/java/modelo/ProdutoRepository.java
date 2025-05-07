package modelo;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ProdutoRepository {
    private static final String ARQUIVO_JSON = "produtos.json";
    private static ProdutoRepository instance;
    private List<Produto> produtos;
    private Gson gson = new Gson();

    private ProdutoRepository() {
        carregarDados();
    }

    public static synchronized ProdutoRepository getInstance() {
        if (instance == null) {
            instance = new ProdutoRepository();
        }
        return instance;
    }

    private void carregarDados() {
        try (Reader reader = new FileReader(ARQUIVO_JSON)) {
            produtos = gson.fromJson(reader, new TypeToken<List<Produto>>(){}.getType());
            if (produtos == null) {
                produtos = new ArrayList<>();
            }
        } catch (IOException e) {
            produtos = new ArrayList<>();
        }
    }

    private void salvarDados() {
        try (Writer writer = new FileWriter(ARQUIVO_JSON)) {
            gson.toJson(produtos, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void adicionar(Produto produto) {
        produtos.add(produto);
        salvarDados();
    }

    public void atualizar(int index, Produto produto) {
        produtos.set(index, produto);
        salvarDados();
    }

    public void remover(int index) {
        produtos.remove(index);
        salvarDados();
    }

    public List<Produto> getProdutos() {
        return new ArrayList<>(produtos);
    }
}