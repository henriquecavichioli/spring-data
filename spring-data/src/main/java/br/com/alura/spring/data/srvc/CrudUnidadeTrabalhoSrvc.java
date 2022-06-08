package br.com.alura.spring.data.srvc;

import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.com.alura.spring.data.orm.UnidadeTrabalho;
import br.com.alura.spring.data.repository.UnidadeTrabalhoRepository;

@Service
public class CrudUnidadeTrabalhoSrvc {

	private Boolean system = true;

	private final UnidadeTrabalhoRepository unidadeTrabalhoRepository;

	public CrudUnidadeTrabalhoSrvc(UnidadeTrabalhoRepository unidadeTrabalhoRepository) {
		this.unidadeTrabalhoRepository = unidadeTrabalhoRepository;
	}

	public void inicial(Scanner scanner) {

		while (system) {

			System.out.println("Qual acao de unidade de trabalho deseja executar?");
			System.out.println("0 - Sair");
			System.out.println("1 - Salvar");
			System.out.println("2 - Atualizar");
			System.out.println("3 - Visualizar");
			System.out.println("4 - Deletar");

			int action = scanner.nextInt();

			switch (action) {
			case 1:
				salvar(scanner);
				break;
			case 2:
				atualizar(scanner);
				break;
			case 3:
				visualizar();
				break;
			case 4:
				deletar(scanner);
				break;

			default:
				system = false;
				break;
			}

		}

	}

	private void salvar(Scanner scanner) {

		System.out.println("Entre com a descricao da unidade de trabalho: ");
		String descricao = scanner.next();

		System.out.println("Entre com o endereco da unidade de trabalho: ");
		String endereco = scanner.next();

		UnidadeTrabalho unidadeDeTrabalho = new UnidadeTrabalho();
		unidadeDeTrabalho.setDescricao(descricao);
		unidadeDeTrabalho.setEndereco(endereco);

		unidadeTrabalhoRepository.save(unidadeDeTrabalho);
		System.out.println("Salvo");

	}

	public void atualizar(Scanner scanner) {

		System.out.println("Entre com o id da unidade de trabalho: ");
		Integer id = scanner.nextInt();

		System.out.println("Entre com a descricao da unidade de trabalho: ");
		String nome = scanner.next();

		System.out.println("Entre com o endereco da unidade de trabalho: ");
		String endereco = scanner.next();

		UnidadeTrabalho unidadeTrabalho = new UnidadeTrabalho();
		unidadeTrabalho.setId(id);
		unidadeTrabalho.setDescricao(nome);
		unidadeTrabalho.setEndereco(endereco);

		unidadeTrabalhoRepository.save(unidadeTrabalho);
		System.out.println("Alterado");

	}

	private void visualizar() {
		Iterable<UnidadeTrabalho> unidades = unidadeTrabalhoRepository.findAll();
		unidades.forEach(unidade -> System.out.println(unidade));
	}

	private void deletar(Scanner scanner) {

		System.out.println("Entre com o id da unidade de trabalho que deseja deletar: ");
		int id = scanner.nextInt();
		unidadeTrabalhoRepository.deleteById(id);
		System.out.println("Deletado");

	}

}
