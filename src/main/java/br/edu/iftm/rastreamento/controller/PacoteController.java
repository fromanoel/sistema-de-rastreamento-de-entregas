package br.edu.iftm.rastreamento.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.iftm.rastreamento.model.Pacote;
import br.edu.iftm.rastreamento.service.PacoteService;
import br.edu.iftm.rastreamento.service.exceptions.NaoAcheiException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/pacotes")
public class PacoteController {

	@Autowired
	private PacoteService pacoteService;

	@Operation(summary = "Retorna todos os pacotes")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Lista de pacotes retornada com sucesso", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Pacote.class))}),
		@ApiResponse(responseCode = "204", description = "Nenhum pacote encontrado", content = @Content)
	})
	@GetMapping
	public List<Pacote> getAllPacotes() {
		return pacoteService.getAllPacotes();
	}


	@Operation(summary = "Cria um novo pacote")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "201", description = "Pacote criado com sucesso", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Pacote.class))}),
		@ApiResponse(responseCode = "400", description = "Erro ao criar pacote", content = @Content)
	})
	@PostMapping
	public Pacote createPacote(@RequestBody Pacote pacote) {
		return pacoteService.createPacote(pacote);
	}


	@Operation(summary = "Retorna um pacote pelo ID")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Pacote retornado com sucesso", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Pacote.class))}),
		@ApiResponse(responseCode = "204", description = "Pacote não encontrado para o ID fornecido", content = @Content)
	})
	@GetMapping("/{id}")
	public Pacote getPacoteById(@PathVariable Long id) {
		try{
			return pacoteService.getPacoteById(id);
	
		} catch (Exception e){
			throw new NaoAcheiException("Pacote não encontrado para o ID fornecido" + id);
		}
	}


	@Operation(summary = "Atualiza um pacote existente pelo ID")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Pacote atualizado com sucesso", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Pacote.class))}),
		@ApiResponse(responseCode = "204", description = "Nenhum pacote encontrado para o ID fornecido", content = @Content)
	})
	@PutMapping("/{id}")
	public Pacote updatePacote(@PathVariable Long id, @RequestBody Pacote pacoteDetails) {
		try{
			return pacoteService.updatePacote(id, pacoteDetails);
		} catch (Exception e){
			throw new NaoAcheiException("Pacote não encontrado para o ID fornecido" + id);
		}
	}

	@Operation(summary = "Deleta um pacote existente pelo ID")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Pacote deletado com sucesso"),
		@ApiResponse(responseCode = "204", description = "Nenhum pacote encontrado para o ID fornecido")
	})
	@DeleteMapping("/{id}")
	public void deletePacote(@PathVariable Long id) {
		pacoteService.deletePacote(id);
	}

	@Operation(summary = "Retorna pacotes por status")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Lista de pacotes por status retornada com sucesso", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Pacote.class))}),
		@ApiResponse(responseCode = "204", description = "Nenhum pacote encontrado", content = @Content)
	})
	@GetMapping("/status")
	public List<Pacote> getPacotePorStatus(@RequestParam String status) {
		return pacoteService.getPacotesPorStatus(status);
	}

	@Operation(summary = "Retorna pacotes por destinatário")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Lista de pacotes por destinatario retornada com sucesso", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Pacote.class))}),
		@ApiResponse(responseCode = "204", description = "Nenhum pacote encontrado", content = @Content)
	})
	@GetMapping("/destinatario")
	public List<Pacote> getPacotePorDestinatario(@RequestParam String destinatario) {
		return pacoteService.getPacotesPorDestinatario(destinatario);
	}
	
}