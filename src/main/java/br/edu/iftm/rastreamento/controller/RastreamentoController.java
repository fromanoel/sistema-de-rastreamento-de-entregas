package br.edu.iftm.rastreamento.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.iftm.rastreamento.model.Rastreamento;
import br.edu.iftm.rastreamento.service.RastreamentoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.util.List;

@RestController
@RequestMapping("/rastreamentos")
public class RastreamentoController {

	@Autowired
	private RastreamentoService rastreamentoService;

	@Operation(summary = "Retorna todos os rastreamentos de um pacote")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Lista de rastreamentos retornada com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Rastreamento.class))),
		@ApiResponse(responseCode = "204", description = "Nenhum rastreamento encontrado", content = @Content)
	})
	@GetMapping("/{id}")
	public List<Rastreamento> getRastreamentosPacote(@PathVariable Long id) {
		return rastreamentoService.getRastreamentos(id);
	}

}
