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

import com.Projetocompleto.entities.Vendedor;
import com.Projetocompleto.service.VendedorService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
@Tag (name= "Vendedor", description = " API REST DE GERENCIAMENTO DE VERDEDOR")
@RestController
@RequestMapping("/vendedor")
public class VendedorController {

	private final VendedorService vendedorService;
	@Autowired
	public VendedorController(VendedorService vendedorService) {
		this.vendedorService = vendedorService;
	}
	@Operation(summary = "Localiza produto por ID")
	@GetMapping("/{id}")
	public ResponseEntity<Vendedor> getVendedorById(@PathVariable Long id) {
		Vendedor Vendedor = vendedorService.getVendedorById(id);
		if (Vendedor != null) {
			return ResponseEntity.ok(Vendedor);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	@Operation(summary = "Apresenta todos os produtos")

	@GetMapping("/")
	public ResponseEntity<List<Vendedor>> getAllVendedor() {
		List<Vendedor> Vendedor = vendedorService.getAllVendedor();
		return ResponseEntity.ok(Vendedor);
	}
	@GetMapping("/setor/{setor}")
	public ResponseEntity<List<Vendedor>> getVendedorsPorSetor(@PathVariable String setor){
		List<Vendedor> Vendedors = vendedorService.getVendedorsPorsetor(setor);
		return ResponseEntity.ok(Vendedors);
	}
	
	@GetMapping("/nome/{nome}")
	public List<Vendedor> findVendedorsPorNome(@PathVariable String nome){
		 return vendedorService.findByNome(nome);
		 
	}
	
	
	@Operation(summary = "Cadastra um produto")
	@PostMapping("/")
	public ResponseEntity<Vendedor> criarVendedor(@RequestBody Vendedor Vendedor) {
		Vendedor criarVendedor = vendedorService.salvarVendedor(Vendedor);
		return ResponseEntity.status(HttpStatus.CREATED).body(criarVendedor);
	}
	@Operation(summary = "Altera um produto")
	@PutMapping("/{id}")
	public ResponseEntity<Vendedor> updateVendedor(@PathVariable Long id,@RequestBody Vendedor Vendedor) {
		Vendedor updatedVendedor = vendedorService.updateVendedor(id, Vendedor);
		if (updatedVendedor != null) {
			return ResponseEntity.ok(updatedVendedor);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	@Operation(summary = "Exclui um produto")
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteVendedor(@PathVariable Long id) {
		boolean deleted = vendedorService.deleteVendedor(id);
		if (deleted) {
			return ResponseEntity.ok().body("O Vendedor foi excluído com sucesso.");
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}
