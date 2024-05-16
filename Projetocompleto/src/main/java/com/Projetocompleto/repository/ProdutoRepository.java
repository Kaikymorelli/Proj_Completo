package com.Projetocompleto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.Projetocompleto.entities.Produto;

public interface ProdutoRepository extends JpaRepository <Produto,Long>  {

	//List<Produto> findByNome(String nome);
	//List<Produto> findByDescricao(String descricao);
	
	@Query("SELECT a FROM Produto a WHERE a.nome = :nome")
	List<Produto> findByNome(@Param ("nome")String nome);
	
	@Query("SELECT a FROM Produto a WHERE a.nome = :descricao")
	List<Produto> findByDescricao(@Param ("descricao")String descricao);
}
