import org.junit.Test;

public class CadDAOTest {


	@Test
	public void createCadastroSucesso() throws Exception{
		CadastroDAO cadastroDAO = new CadastroDAO();
		Residencial residencial = new Residencial();
		residencial.setsenha("333333333333");
		
		cadastroDAO.createCadastro(residencial);
		
		for(Cadastro cadastro: cadastroDAO.listCadastros()){			
			System.out.print(cadastro.getsenha());
		}
	}

	@Test
	public void deleteCadastroSucesso() throws Exception{
		
		String senha="333333333333";

		CadastroDAO cadastroDAO = new CadastroDAO();
		Residencial residencial = new Residencial();
		residencial.setsenha(senha);
		
		cadastroDAO.createCadastro(residencial);
		
		for(Cadastro cadastro: cadastroDAO.listCadastros()){			
			System.out.print(cadastro.getsenha());
		}
		
		cadastroDAO.deleteCadastro(cnpj);

		System.out.print(cadastroDAO.listCadastros().size());
	}

	@Test
	public void listCadastrosSucesso() throws Exception{

		String senha1="333333333333";

		CadastroDAO cadastroDAO = new CadastroDAO();
		Residencial residencial1 = new Residencial();
		residencial1.setsenha(senha1);
		
		cadastroDAO.createCadastro(residencial1);

		String senha2="444444444444444";
		
		Residencial residencial2 = new Residencial();
		residencial2.setsenha(senha2);
		
		cadastroDAO.createCadastro(residencial2);
		
		for(Cadastro cadastro: cadastroDAO.listCadastros()){			
			System.out.print(cadastro.getsenha());
		}
	}

	@Test
	public void updateCadastroSucesso() throws Exception{

		String senha1="333333333333";

		CadastroDAO cadastroDAO = new CadastroDAO();
		Residencial residencial1 = new Residencial();
		residencial1.setsenha(senha1);
		
		cadastroDAO.createCadastro(residencial1);

		Residencial residencial2 = new Residencial();
		residencial2.setsenha(senha1);
		residencial2.setNome("nome");
		
		cadastroDAO.updateCadastro(residencial2);

		for(Cadastro cadastro: cadastroDAO.listCadastros()){			
			System.out.print(cadastro.getNome());
		}
	}

}
