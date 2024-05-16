package com.Projetocompleto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Projetocompleto.entities.Equipe;
import com.Projetocompleto.service.EquipeService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/equipe")
@Tag (name= "Equipe", description = " API REST DE GERENCIAMENTO DE Equipe")
public class EquipeController {
	private final EquipeService equipeService;
	@Autowired
	public EquipeController(EquipeService equipeService) {
		this.equipeService = equipeService;
	}
	@Operation(summary = "Localiza produto por ID")
	@GetMapping("/{id}")
	public ResponseEntity<Equipe> getEquipeById(@PathVariable Long id) {
		Equipe Equipe = equipeService.getEquipeById(id);
		if (Equipe != null) {
			return ResponseEntity.ok(Equipe);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	@Operation(summary = "Apresenta todos os produtos")
	@GetMapping("/")
	public ResponseEntity<List<Equipe>> getAllEquipe() {
		List<Equipe> Equipe = equipeService.getAllEquipe();
		return ResponseEntity.ok(Equipe);
	}
	@GetMapping("/cidade/{cidade}")
	public ResponseEntity<List<Equipe>> getEquipesPorCidade(@PathVariable String cidade){
		List<Equipe> Equipes = equipeService.getEquipesPorCidade(cidade);
		return ResponseEntity.ok(Equipes);
	}
	
	@GetMapping("/nome/{nome}")
	public List<Equipe> findEquipesPorNome(@PathVariable String nome){
		 return equipeService.findByNome(nome);
		 
	}
	
	
	@Operation(summary = "Cadastra um produto")
	@PostMapping("/")
	public ResponseEntity<Equipe> criarEquipe(@RequestBody Equipe Equipe) {
		Equipe criarEquipe = equipeService.salvarEquipe(Equipe);
		return ResponseEntity.status(HttpStatus.CREATED).body(criarEquipe);
	}
	@Operation(summary = "Altera um produto")
	@PutMapping("/{id}")
	public ResponseEntity<Equipe> updateEquipe(@PathVariable Long id,@RequestBody Equipe Equipe) {
		Equipe updatedEquipe = equipeService.updateEquipe(id, Equipe);
		if (updatedEquipe != null) {
			return ResponseEntity.ok(updatedEquipe);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	@Operation(summary = "Exclui um produto")
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteEquipe(@PathVariable Long id) {
		boolean deleted = equipeService.deleteEquipe(id);
		if (deleted) {
			return ResponseEntity.ok().body("O Equipe foi excluído com sucesso.");
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}
