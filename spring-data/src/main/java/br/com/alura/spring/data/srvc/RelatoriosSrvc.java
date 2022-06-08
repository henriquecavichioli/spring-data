package br.com.alura.spring.data.srvc;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import org.springframework.stereotype.Service;
import br.com.alura.spring.data.orm.Funcionario;
import br.com.alura.spring.data.orm.FuncionarioProjecao;
import br.com.alura.spring.data.repository.FuncionarioRepository;

@Service
public class RelatoriosSrvc {

	private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	private Boolean system = true;

	private final FuncionarioRepository funcionarioRepository;

	public RelatoriosSrvc(FuncionarioRepository funcionarioRepository) {

		this.funcionarioRepository = funcionarioRepository;

	}

	public void inicial(Scanner scanner) {

		while (system) {

			System.out.println("Qual acao de relatorios service deseja executar?");
			System.out.println("0 - Sair");
			System.out.println("1 - Procurar funcionario por nome: ");
			System.out.println("2 - Busca funcionario nome, data contratacao e salario maior que: ");
			System.out.println("3 - Procurar funcionarios com data acima de: ");
			System.out.println("4 - Procurar funcionarios, salario e id: ");

			int action = scanner.nextInt();

			switch (action) {
			case 1:
				procurarPorNome(scanner);
				break;
			case 2:
				buscaFuncionarioNomeSalarioMaiorData(scanner);
				break;
			case 3:
				buscaFuncPorDataMaior(scanner);
				break;
			case 4:
				buscaFuncionarioSalario();
				break;
			default:
				system = false;
			}

		}

	}

	private void procurarPorNome(Scanner scanner) {
		System.out.println("Qual nome deseja procurar?");
		String nome = scanner.next();
		List<Funcionario> list = funcionarioRepository.findByNome(nome);
		list.forEach(System.out::println);

	}

	private void buscaFuncionarioNomeSalarioMaiorData(Scanner scanner) {
		System.out.println("Qual nome deseja procurar?");
		String nome = scanner.next();

		System.out.println("Qual data de contratacao deseja procurar?");
		String data = scanner.next();

		LocalDate localDate = LocalDate.parse(data, formatter);

		System.out.println("Qual salario deseja procurar? (a partir de)");
		Double salario = scanner.nextDouble();

		List<Funcionario> list = funcionarioRepository.findNomeSalarioMaiorDataContratacao(nome, salario, localDate);
		list.forEach(System.out::println);

	}

	private void buscaFuncPorDataMaior(Scanner scanner) {

		System.out.println("Qual data de contratacao deseja procurar? (a partir de)");
		String data = scanner.next();
		LocalDate localDate = LocalDate.parse(data, formatter);
		List<Funcionario> list = funcionarioRepository.findDataContratacaoMaior(localDate);
		list.forEach(System.out::println);

	}

	private void buscaFuncionarioSalario() {
		List<FuncionarioProjecao> list = funcionarioRepository.findFuncionarioSalario();
		list.forEach(f -> System.out.println("Funcionario: id: " + f.getId() + " | nome: " 
		+ f.getNome() + " | salario: " + f.getSalario()));
	}

}