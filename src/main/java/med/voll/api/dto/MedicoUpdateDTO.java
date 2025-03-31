package med.voll.api.dto;

import jakarta.validation.constraints.NotNull;

public record MedicoUpdateDTO(
        @NotNull
        Long id,
        String nome,
        String telefone,
        EnderedoDTO enderedoDTO) {
}
