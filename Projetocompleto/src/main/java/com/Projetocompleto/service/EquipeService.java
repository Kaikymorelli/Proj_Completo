package com.Projetocompleto.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Projetocompleto.entities.Equipe;
import com.Projetocompleto.repository.EquipeRepository;

@Service
public class EquipeService {

	private final EquipeRepository equipeRepository;

	@Autowired
	public EquipeService(EquipeRepository equipeRepository) {
		this.equipeRepository = equipeRepository;
	}
	
	public List<Equipe> getAllEquipe() {
		return equipeRepository.findAll();
	}

	public Equipe getEquipeById(Long id) {
		Optional<Equipe> Equipe = equipeRepository.findById(id);
		return Equipe.orElse(null);
	}
	//Query Method
	public List<Equipe> getEquipesPorCidade(String cidade){
		return equipeRepository.findByCidade(cidade);
	}
	//Query Method
	public List<Equipe> findByNome(String nome){
	return equipeRepository.findByNome(nome);
		}


	public Equipe salvarEquipe(Equipe Equipe) {
		return equipeRepository.save(Equipe);
	}

	public Equipe updateEquipe(Long id, Equipe updatedEquipe) {
		Optional<Equipe> existingEquipe = equipeRepository.findById(id);
		if (existingEquipe.isPresent()) {
			updatedEquipe.setId(id);
			return equipeRepository.save(updatedEquipe);
		}
		return null;
	}

	public boolean deleteEquipe(Long id) {
		Optional<Equipe> existingEquipe = equipeRepository.findById(id);
		if (existingEquipe.isPresent()) {
			equipeRepository.deleteById(id);
			return true;
		}
		return false;
	}
}
