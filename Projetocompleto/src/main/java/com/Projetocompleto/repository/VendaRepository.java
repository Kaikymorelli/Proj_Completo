package com.Projetocompleto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Projetocompleto.entities.Venda;

public interface VendaRepository extends JpaRepository <Venda,Long> {

	
	List<Venda>findByData(String data);
	
	
	//@Query("SELECT a FROM Venda a WHERE a.nome = :data")
	//List<Venda> findByData(@Param ("data")String data);
	

	
}
