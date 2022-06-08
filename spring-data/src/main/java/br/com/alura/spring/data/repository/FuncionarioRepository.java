package br.com.alura.spring.data.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import br.com.alura.spring.data.orm.Funcionario;
import br.com.alura.spring.data.orm.FuncionarioProjecao;

@Repository
public interface FuncionarioRepository extends PagingAndSortingRepository<Funcionario, Integer>,
JpaSpecificationExecutor<Funcionario>{
	
	// Derived Query
	List<Funcionario> findByNome(String nome);

	List<Funcionario> findByCargoDescricao(String descricao);

	List<Funcionario> findByUnidadeTrabalhos_Descricao(String descricao);

	// JPQL para enxutar nome de metodo
	@Query("SELECT f FROM Funcionario f WHERE f.nome = :nome "
			+ "AND f.salario >= :salario AND f.dataContratacao = :data")
	List<Funcionario> findNomeSalarioMaiorDataContratacao(String nome, Double salario, LocalDate data);

	@Query("SELECT f FROM Funcionario f JOIN f.cargo c WHERE c.descricao = :descricao")
	List<Funcionario> findByCargoPelaDescricao(String descricao);

	@Query("SELECT f FROM Funcionario f JOIN f.unidadeTrabalhos u WHERE u.descricao = :descricao")
	List<Funcionario> findUnidadeTrabalhos_Descricao(String descricao);

	@Query(value = "SELECT * FROM funcionarios f WHERE f.data_contratacao >= :data ", nativeQuery = true)
	List<Funcionario> findDataContratacaoMaior(LocalDate data);

	@Query(value = "SELECT f.id, f.nome, f.salario FROM Funcionarios f", nativeQuery = true)
	List<FuncionarioProjecao> findFuncionarioSalario();

}
