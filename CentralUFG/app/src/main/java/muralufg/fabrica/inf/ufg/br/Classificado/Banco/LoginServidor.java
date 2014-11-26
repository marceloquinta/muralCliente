package muralufg.fabrica.inf.ufg.br.Classificado.Banco;

import java.util.ArrayList;
import muralufg.fabrica.inf.ufg.br.Classificado.Notificacao;

/**
 * Interface para operações de registro de usuários e recebimento de notificações no servidor.
 * 
 * @author eric
 */
public interface LoginServidor {

	public boolean loginNoServidor(String usuario, String senha);
	
	public boolean registraUsuarioNoServidor(String usuario, String senha, String email, String tipoUsuario, String disciplinas);
	
	public boolean updateCadastro(String usuario, String senha, String email, String tipoUsuario, String disciplinas);
	
	public boolean pesquisaNomeNoServidor(String usuario);
	
	public ArrayList<Notificacao> receberNotificacoesDoServidor();
}
