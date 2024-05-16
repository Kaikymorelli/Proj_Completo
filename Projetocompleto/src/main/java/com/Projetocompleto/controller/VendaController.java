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

import com.Projetocompleto.entities.Venda;
import com.Projetocompleto.service.VendaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
@Tag (name= "Venda", description = " API REST DE GERENCIAMENTO DE VENDA")
@RestController
@RequestMapping("/venda")
public class VendaController {

	private final VendaService vendaService;
	@Autowired
	public VendaController(VendaService vendaService) {
		this.vendaService = vendaService;
	}
	@Operation(summary = "Localiza produto por ID")
	@GetMapping("/{id}")
	public ResponseEntity<Venda> getVendaById(@PathVariable Long id) {
		Venda Venda = vendaService.getVendaById(id);
		if (Venda != null) {
			return ResponseEntity.ok(Venda);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	@Operation(summary = "Apresenta todos os produtos")
	@GetMapping("/")
	public ResponseEntity<List<Venda>> getAllVenda() {
		List<Venda> Venda = vendaService.getAllVenda();
		return ResponseEntity.ok(Venda);
	}
	@GetMapping("/data/{data}")
	public ResponseEntity<List<Venda>> getVendasPorData(@PathVariable String data){
		List<Venda> Vendas = vendaService.getVendasPorData(data);
		return ResponseEntity.ok(Vendas);
	}
	
	
	
	
	@Operation(summary = "Cadastra um produto")
	@PostMapping("/")
	public ResponseEntity<Venda> criarVenda(@RequestBody Venda Venda) {
		Venda criarVenda = vendaService.salvarVenda(Venda);
		return ResponseEntity.status(HttpStatus.CREATED).body(criarVenda);
	}
	@Operation(summary = "Altera um produto")
	@PutMapping("/{id}")
	public ResponseEntity<Venda> updateVenda(@PathVariable Long id,@RequestBody Venda Venda) {
		Venda updatedVenda = vendaService.updateVenda(id, Venda);
		if (updatedVenda != null) {
			return ResponseEntity.ok(updatedVenda);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	@Operation(summary = "Exclui um produto")
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteVenda(@PathVariable Long id) {
		boolean deleted = vendaService.deleteVenda(id);
		if (deleted) {
			return ResponseEntity.ok().body("O Venda foi excluído com sucesso.");
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}
