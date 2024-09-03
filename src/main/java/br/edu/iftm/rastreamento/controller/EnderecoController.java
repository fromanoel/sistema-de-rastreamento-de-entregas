package br.edu.iftm.rastreamento.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.iftm.rastreamento.dto.EnderecoDTO;
import br.edu.iftm.rastreamento.service.EnderecoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/enderecos")
public class EnderecoController {

	@Autowired
	private EnderecoService enderecoService;

	@GetMapping
	@Operation(summary = "Retorna todos os endereços")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Lista de endereços retornada com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = EnderecoDTO.class))),
		@ApiResponse(responseCode = "204", description = "Nenhum endereço encontrado", content = @Content)
	})
	public List<EnderecoDTO> getAllEnderecos() {
		System.out.println("-----------------------------------------");
		return enderecoService.getAllEnderecos();
	}


	@Operation(summary = "Retorna um endereço pelo ID")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Endereço retornado com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = EnderecoDTO.class))),
		@ApiResponse(responseCode = "204", description = "Endereço não encontrado para o ID fornecido", content = @Content)
	})
	@GetMapping("/{id}")
	public ResponseEntity<EnderecoDTO> getById(@PathVariable Long id) {
		EnderecoDTO endereco = enderecoService.getEnderecoById(id);
		return ResponseEntity.status(HttpStatus.FOUND).body(endereco);
	}

	@Operation(summary = "Cria um novo endereço")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "201", description = "Endereço criado com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = EnderecoDTO.class))),
		@ApiResponse(responseCode = "400", description = "Erro ao criar endereço", content = @Content)
	})
	@PostMapping
	public ResponseEntity<EnderecoDTO> createEndereco(@RequestBody EnderecoDTO enderecoDTO) {
		EnderecoDTO novoRecursEnderecoDTO = enderecoService.createEndereco(enderecoDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(novoRecursEnderecoDTO);
	}
}
