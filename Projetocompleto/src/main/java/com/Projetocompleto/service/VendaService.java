package com.Projetocompleto.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Projetocompleto.entities.Venda;
import com.Projetocompleto.repository.VendaRepository;

@Service
public class VendaService {

	private final VendaRepository vendaRepository;

	@Autowired
	public VendaService(VendaRepository vendaRepository) {
		this.vendaRepository = vendaRepository;
	}
	
	public List<Venda> getAllVenda() {
		return vendaRepository.findAll();
	}

	public Venda getVendaById(Long id) {
		Optional<Venda> Venda = vendaRepository.findById(id);
		return Venda.orElse(null);
	}
	//Query Method
	public List<Venda> getVendasPorData(String data){
		return vendaRepository.findByData(data);
	}


	public Venda salvarVenda(Venda Venda) {
		return vendaRepository.save(Venda);
	}

	public Venda updateVenda(Long id, Venda updatedVenda) {
		Optional<Venda> existingVenda = vendaRepository.findById(id);
		if (existingVenda.isPresent()) {
			updatedVenda.setId(id);
			return vendaRepository.save(updatedVenda);
		}
		return null;
	}

	public boolean deleteVenda(Long id) {
		Optional<Venda> existingVenda = vendaRepository.findById(id);
		if (existingVenda.isPresent()) {
			vendaRepository.deleteById(id);
			return true;
		}
		return false;
	}
}
