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

import com.Projetocompleto.entities.Produto;
import com.Projetocompleto.service.ProdutoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
@Tag (name= "Produto", description = " API REST DE GERENCIAMENTO DE PRODUTO")
@RestController
@RequestMapping("/produto")
public class ProdutoController {

	private final ProdutoService produtoService;
	@Autowired
	public ProdutoController(ProdutoService produtoService) {
		this.produtoService = produtoService;
	}

	@GetMapping("/{id}")
	@Operation(summary = "Localiza produto por ID")
	public ResponseEntity<Produto> getProdutoById(@PathVariable Long id) {
		Produto Produto = produtoService.getProdutoById(id);
		if (Produto != null) {
			return ResponseEntity.ok(Produto);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/")
	@Operation(summary = "Apresenta todos os produtos")
	public ResponseEntity<List<Produto>> getAllProduto() {
		List<Produto> Produto = produtoService.getAllProduto();
		return ResponseEntity.ok(Produto);
	}
	@GetMapping("/descricao/{descricao}")
	public ResponseEntity<List<Produto>> getProdutosPorDescricao(@PathVariable String descricao){
		List<Produto> Produtos = produtoService.getProdutosPorDescricao(descricao);
		return ResponseEntity.ok(Produtos);
	}
	
	@GetMapping("/nome/{nome}")
	public List<Produto> findProdutosPorNome(@PathVariable String nome){
		 return produtoService.findByNome(nome);
		 
	}
	
	
	
	@PostMapping("/")
	@Operation(summary = "Cadastra um produto")
	public ResponseEntity<Produto> criarProduto(@RequestBody Produto Produto) {
		Produto criarProduto = produtoService.salvarProduto(Produto);
		return ResponseEntity.status(HttpStatus.CREATED).body(criarProduto);
	}

	@PutMapping("/{id}")
	@Operation(summary = "Altera um produto")
	public ResponseEntity<Produto> updateProduto(@PathVariable Long id,@RequestBody Produto Produto) {
		Produto updatedProduto = produtoService.updateProduto(id, Produto);
		if (updatedProduto != null) {
			return ResponseEntity.ok(updatedProduto);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "Exclui um produto")
	public ResponseEntity<String> deleteProduto(@PathVariable Long id) {
		boolean deleted = produtoService.deleteProduto(id);
		if (deleted) {
			return ResponseEntity.ok().body("O Produto foi excluído com sucesso.");
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}
