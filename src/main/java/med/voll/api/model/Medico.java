package med.voll.api.model;

import jakarta.persistence.*;
import lombok.*;
import med.voll.api.dto.MedicoDTO;
import med.voll.api.dto.MedicoUpdateDTO;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "medicos")
public class Medico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String crm;
    private String telefone;
    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;
    @Embedded
    private Endereco endereco;
    private boolean ativo;

    public Medico(MedicoDTO dto) {
        this.ativo = true;
        this.nome = dto.nome();
        this.email = dto.email();
        this.crm = dto.crm();
        this.telefone = dto.telefone();
        this.especialidade = dto.especialidade();
        this.endereco = new Endereco(dto.endereco());
    }

    public void update(MedicoUpdateDTO data) {
        if (data.nome() != null) this.nome = data.nome();
        if (data.telefone() != null) this.telefone = data.telefone();
        if (data.enderedoDTO() != null) this.endereco.updateInfo(data.enderedoDTO());
    }

    public void disable() {
        this.ativo = false;
    }
}
