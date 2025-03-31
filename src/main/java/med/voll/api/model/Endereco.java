package med.voll.api.model;

import jakarta.persistence.Embeddable;
import lombok.*;
import med.voll.api.dto.EnderedoDTO;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Embeddable
public class Endereco {
    private String logradouro;
    private String bairro;
    private String cep;
    private String cidade;
    private String uf;
    private String numero;
    private String complemento;

    public Endereco(EnderedoDTO dto) {
        this.logradouro = dto.logradouro();
        this.bairro = dto.bairro();
        this.cep = dto.cep();
        this.cidade = dto.cidade();
        this.uf = dto.uf();
        this.numero = dto.numero();
        this.complemento = dto.complemento();
    }

    public void updateInfo(EnderedoDTO enderedoDTO) {
        if (enderedoDTO.logradouro() != null) this.logradouro = enderedoDTO.logradouro();
        if (enderedoDTO.bairro() != null) this.bairro = enderedoDTO.bairro();
        if (enderedoDTO.cep() != null) this.cep = enderedoDTO.cep();
        if (enderedoDTO.cidade() != null) this.cidade = enderedoDTO.cidade();
        if (enderedoDTO.uf() != null) this.uf = enderedoDTO.uf();
        if (enderedoDTO.numero() != null) this.numero = enderedoDTO.numero();
        if (enderedoDTO.complemento() != null) this.complemento = enderedoDTO.complemento();
    }
}
