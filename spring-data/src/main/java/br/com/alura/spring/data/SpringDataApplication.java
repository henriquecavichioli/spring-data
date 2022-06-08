package br.com.alura.spring.data;

import java.util.Scanner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.alura.spring.data.srvc.CrudCargoSrvc;
import br.com.alura.spring.data.srvc.CrudFuncionarioSrvc;
import br.com.alura.spring.data.srvc.CrudUnidadeTrabalhoSrvc;
import br.com.alura.spring.data.srvc.RelatorioFuncionarioDinamico;
import br.com.alura.spring.data.srvc.RelatoriosSrvc;

//@EnableJpaRepositories(basePackages = "br.com.alura.spring.data.srvc.CrudCargoSrvc") // Não tive mais o erro com essa anotação
//@EntityScan(basePackages = "br.com.alura.spring.data.orm.Cargo") // Outra tipo de anotação, para indicar ao Spring onde encontrar as entidades que estajam em outro pacote
@SpringBootApplication
public class SpringDataApplication implements CommandLineRunner {

	private final CrudCargoSrvc cargoService;
	private final CrudFuncionarioSrvc funcionarioService;
	private final CrudUnidadeTrabalhoSrvc unidadeTrabalhoSrvc;
	private final RelatoriosSrvc relatoriosSrvc;
	private final RelatorioFuncionarioDinamico relatorioFuncionarioDinamico;
	


	private Boolean system = true;

	public SpringDataApplication(CrudCargoSrvc cargoService, CrudFuncionarioSrvc funcionarioService,
			CrudUnidadeTrabalhoSrvc unidadeTrabalhoSrvc, RelatoriosSrvc relatoriosSrvc, RelatorioFuncionarioDinamico relatorioFuncionarioDinamico) {
		this.cargoService = cargoService;
		this.funcionarioService = funcionarioService;
		this.unidadeTrabalhoSrvc = unidadeTrabalhoSrvc;
		this.relatoriosSrvc = relatoriosSrvc;
		this.relatorioFuncionarioDinamico = relatorioFuncionarioDinamico;
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringDataApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Scanner scanner = new Scanner(System.in);

		while (system) {

			System.out.println("Qual acao voce quer executar? ");
			System.out.println("0 - Sair");
			System.out.println("1 - Cargo");
			System.out.println("2 - Funcionario");
			System.out.println("3 - Unidade de trabalho");
			System.out.println("4 - Relatorios Service");
			System.out.println("5 - Relatorios dinamico");

			int action = scanner.nextInt();

			if (action == 1) {
				cargoService.inicial(scanner);
			}

			if (action == 2) {
				funcionarioService.inicial(scanner);
			}

			if (action == 3) {
				unidadeTrabalhoSrvc.inicial(scanner);
			}

			if (action == 4) {
				relatoriosSrvc.inicial(scanner);
			}
			
			if (action == 5) {
				relatorioFuncionarioDinamico.inicial(scanner);
			}

			else if (action == 0) {
				system = false;
			}

		}

	}

}
