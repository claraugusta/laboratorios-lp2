package dica;

import java.util.ArrayList;
import java.util.List;

import estudante.EstudanteController;

/**
 * A classe DicaController é responsável por gerenciar o cadastro, manipulação e listagem de dicas
 * relacionadas aos estudantes, integrando-se ao EstudanteController para validação de usuários.
 * Cada dica é composta por elementos de referência, texto ou multimídia.
 * 
 * @author Maria Clara Augusta Santos - 123210361
 */
public class DicaController {
	
	private List<Dica> dicas;
	private int proxID;
	private EstudanteController ec;
	
	
	/**
     * Construtor que inicializa o controlador de dicas com um controlador de estudantes.
     * 
     * @param ec O controlador de estudantes a ser usado para validação de credenciais.
     */
    public DicaController(EstudanteController ec) {
        dicas = new ArrayList<>();
        this.ec = ec;
        proxID = 0;
    }

    /**
     * Adiciona uma nova dica ao sistema. O estudante deve ser autenticado via CPF e senha.
     * 
     * @param cpf O CPF do estudante que está criando a dica.
     * @param senha A senha do estudante.
     * @param tema O tema da nova dica.
     * @return O ID da dica recém-adicionada.
     * @throws IllegalArgumentException Se a senha for inválida ou o estudante não existir.
     */
    public int adicionaDica(String cpf, String senha, String tema) {
        ec.validaSenha(cpf, senha);
        Dica novaDica = new Dica(ec.pegaEstudante(cpf), tema);
        dicas.add(novaDica);
        dicas.get(proxID).setID(proxID + 1);
        return proxID++;
    }

    /**
     * Método privado que retorna uma dica pela sua posição na lista.
     * 
     * @param posicao A posição da dica na lista (1-based).
     * @return A dica correspondente à posição.
     */
    private Dica pegaDica(int posicao) {
        return dicas.get(posicao - 1);
    }

    /**
     * Adiciona um elemento de referência a uma dica existente. O estudante deve ser autenticado via CPF e senha.
     * 
     * @param cpf O CPF do estudante.
     * @param senha A senha do estudante.
     * @param posicao A posição da dica na lista (1-based).
     * @param titulo O título da referência.
     * @param fonte A fonte da referência.
     * @param ano O ano da referência.
     * @param importancia O nível de importância da referência.
     * @param conferida Indicador se a referência foi conferida.
     * @return True se o elemento foi adicionado com sucesso, False caso contrário.
     */
    public boolean adicionaElementoReferenciaDica(String cpf, String senha, int posicao, String titulo, String fonte, int ano, int importancia, boolean conferida) {
        ec.validaSenha(cpf, senha);
        return pegaDica(posicao).adicionaElementoReferencia(titulo, fonte, ano, importancia, conferida);
    }

    /**
     * Adiciona um elemento de texto a uma dica existente. O estudante deve ser autenticado via CPF e senha.
     * 
     * @param cpf O CPF do estudante.
     * @param senha A senha do estudante.
     * @param posicao A posição da dica na lista (1-based).
     * @param texto O texto a ser adicionado.
     * @return True se o elemento foi adicionado com sucesso, False caso contrário.
     */
    public boolean adicionaElementoTextoDica(String cpf, String senha, int posicao, String texto) {
        ec.validaSenha(cpf, senha);
        return pegaDica(posicao).adicionaElementoTexto(texto);
    }

    /**
     * Adiciona um elemento multimídia a uma dica existente. O estudante deve ser autenticado via CPF e senha.
     * 
     * @param cpf O CPF do estudante.
     * @param senha A senha do estudante.
     * @param posicao A posição da dica na lista (1-based).
     * @param link O link para o conteúdo multimídia.
     * @param cabecalho O cabeçalho do conteúdo.
     * @param tempo O tempo de duração do conteúdo.
     * @return True se o elemento foi adicionado com sucesso, False caso contrário.
     */
    public boolean adicionaElementoMultimidiaDica(String cpf, String senha, int posicao, String link, String cabecalho, int tempo) {
        ec.validaSenha(cpf, senha);
        return pegaDica(posicao).adicionaElementoMultimidia(link, cabecalho, tempo);
    }

    /**
     * Retorna uma lista simples com as informações de todas as dicas cadastradas.
     * 
     * @return Um array de strings contendo as informações das dicas.
     */
    public String[] listaDicas() {
        String[] dicasList = new String[dicas.size()];
        int prox = 0;
        for (Dica d : dicas) {
            dicasList[prox++] = d.listaDica();
        }
        return dicasList;
    }

    /**
     * Retorna uma lista detalhada de todas as dicas cadastradas.
     * 
     * @return Um array de strings contendo as informações detalhadas das dicas.
     */
    public String[] listaDicasDetalhadas() {
        String[] dicasList = new String[dicas.size()];
        int prox = 0;
        for (Dica d : dicas) {
            dicasList[prox++] = d.listaDicaDetalhada();
        }
        return dicasList;
    }

    /**
     * Retorna as informações de uma dica específica pela sua posição.
     * 
     * @param posicao A posição da dica na lista (1-based).
     * @return Uma string com as informações da dica.
     */
    public String listaDicaPosicao(int posicao) {
        return pegaDica(posicao).listaDica();
    }

    /**
     * Retorna as informações detalhadas de uma dica específica pela sua posição.
     * 
     * @param posicao A posição da dica na lista (1-based).
     * @return Uma string com as informações detalhadas da dica.
     */
    public String listaDicaDetalhadaPosicao(int posicao) {
        return pegaDica(posicao).listaDicaDetalhada();
    }
}
