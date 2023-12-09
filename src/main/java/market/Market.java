package market;

import model.Produto;
import utili.Util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Market {
    private  static final Scanner input = new Scanner(System.in);
    private  static ArrayList<Produto> produtos;
    private  static Map<Produto, Integer> carrinho;

    public  static void main(String[]args) {
        produtos = new ArrayList<>();
        carrinho = new HashMap<>();
        menu();
    }

    private static void menu() {


            System.out.println("-----------------------------------------");
            System.out.println("--------------- BEM VINDO ---------------");
            System.out.println("-----------------------------------------");
            System.out.println("----------SELECIONE UMA OPÇÃO------------");
            System.out.println("|          1: CADASTRAR        |");
            System.out.println("|          2: LISTAR           |");
            System.out.println("|          3: COMPRAR PRODUTO  |");
            System.out.println("|          4: VER CARRINHO     |");
            System.out.println("|          5: SAIR             |");


    int option = input.nextInt();
        switch (option) {
        case 1:
            cadastrarProdutos();
            break;
        case 2:
            listarProdutos();
            break;
        case 3:
            comprarProduto();
            break;
        case 4:
            verCarrinho();
            break;
        case 5:
            System.out.println("Até mais!");
            System.exit(0);

        default:
            System.out.println("Opção inválida");
            menu();
            break;

    }

}

    private static  void cadastrarProdutos() {
        System.out.println("Nome Produto:");
        String nome = input.next();

        System.out.println("Preço do Produto");
        double preco = input.nextDouble();

        Produto produto = new Produto(nome, preco);
        produtos.add(produto);

        System.out.println(produto.getNome()+ "Cadastrado com sucesso!");
        menu();
    }


    private  static void listarProdutos (){

        if (produtos.size()> 0) {
            System.out.println("Lista produtos\n");

            for (Produto p : produtos) {
                System.out.println(p);
            }
        } else {
            System.out.println("Nenhum produto cadastrado!\n");
        }

        menu();

    }
    private static void comprarProduto(){
        if (produtos.size()> 0) {
            System.out.println("Código do Produto:\n");

            System.out.println("----Produtos Disponíveis----");
            for (Produto p : produtos) {
                System.out.println(p + "\n");
            }

            int id = Integer.parseInt(input.next());
            boolean isPresent = false;

            for (Produto p: produtos){
                if (p.getId()== id) {
                    int qtd = 0;
                    try {
                        qtd = carrinho.get(p);
                        carrinho.put(p, qtd + 1);
                    } catch (NullPointerException e) {
                        carrinho.put(p, 1);

                    }

                    System.out.println(p.getNome()+ "Adicionado com Sucesso ao Carrinho!\n");
                    isPresent = true;

                    if (isPresent){
                        System.out.println("Deseja adicionar outro produto ao carrinho?");
                        System.out.println("Digite 1 para sim, e 0 para finalizar a compra.\n");
                        int option = Integer.parseInt(input.next());

                        if (option == 1){
                            comprarProduto();
                        }else {
                            finalizarCompra();
                        }
                    }
                }else {
                    System.out.println("Produto não encontrado.\n");
                    menu();
                }
            }

        }else {
            System.out.println(" Não existem produtos cadatrados!\n");
            menu();
        }

    }

    private static void verCarrinho(){
        System.out.println("----Produtos no seu carrinho!----");
        if (carrinho.size()>0){
            for (Produto p : carrinho.keySet()){
                System.out.println("Produto:!"+ p + "\nQuantidade: "+ carrinho.get(p));
            }

        }else {
            System.out.println("Carrinho Vazio!\n");
        }
        menu();
    }

    private static void finalizarCompra() {
        Double valorCompra = 0.0;
        System.out.println("Seus Produtos!\n");

        for (Produto p : carrinho.keySet()) {
            int qtd = carrinho.get(p);
            valorCompra += p.getPreco() * qtd;
            System.out.println(p);
            System.out.println("Quantidade: "+ qtd);
            System.out.println("****************");
        }

        System.out.println("Valor da sua compra é: " + Util.doubleToString(valorCompra));
        carrinho.clear();
        System.out.println("Obrigado pela compra. volte sempre!");
    }


}
