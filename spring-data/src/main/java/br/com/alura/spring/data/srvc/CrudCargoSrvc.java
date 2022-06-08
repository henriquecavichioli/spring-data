package br.com.alura.spring.data.srvc;

import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.com.alura.spring.data.orm.Cargo;
import br.com.alura.spring.data.repository.CargoRepository;

@Service
public class CrudCargoSrvc {

	private Boolean system = true;

	private final CargoRepository cargoRepository;

	public CrudCargoSrvc(CargoRepository cargoRepository) {
		this.cargoRepository = cargoRepository;
	}

	public void inicial(Scanner scanner) {

		while (system) {

			System.out.println("Qual acao de cargo deseja executar?");
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
			}

		}

	}

	private void salvar(Scanner scanner) {

		System.out.println("Entre com o nome do cargo: ");
		String nomeCargo = scanner.next();
		Cargo cargo = new Cargo();
		cargo.setDescricao(nomeCargo);
		cargoRepository.save(cargo);
		System.out.println("Salvo");

	}

	public void atualizar(Scanner scanner) {
		System.out.println("Entre com o id do cargo: ");
		int id = scanner.nextInt();
		System.out.println("Entre com a descricao do cargo: ");
		String descricao = scanner.next();
		Cargo cargo = new Cargo();
		cargo.setId(id);
		cargo.setDescricao(descricao);
		cargoRepository.save(cargo);
		System.out.println("Salvo");

	}

	public void visualizar() {
		Iterable<Cargo> cargos = cargoRepository.findAll();
		cargos.forEach(cargo -> System.out.println(cargo));
	}

	private void deletar(Scanner scanner) {
		System.out.println("Entre com o id do cargo que deseja deletar: ");
		int id = scanner.nextInt();
		cargoRepository.deleteById(id);
		System.out.println("Deletado");

	}

}
