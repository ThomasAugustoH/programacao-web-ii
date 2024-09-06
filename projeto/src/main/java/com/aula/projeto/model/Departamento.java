package com.aula.projeto.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter // Automaticamente cria getters
@Setter // Automaticamente cria setters
@AllArgsConstructor // Cria um construtor com todos os argumentos
@NoArgsConstructor // Cria um construtor sem argumentos
@EqualsAndHashCode
//@Entity(name = "TBL_2120") 
@Entity // Declarar como entidade para usar de tabela
public class Departamento implements Serializable {

    @Id // PK
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto Increment
    // @Column(name="CD_DEPTO")
    private Integer idDepto;

    @NotNull(message = "Nome do departamento não pode ser nulo!")
    @NotBlank(message = "Nome do departamento não pode ser branco!")
    @Length(min = 5, max = 255, message = "Nome do departamento deve ter entre 5 e 255 caracteres!") // Tamanho do VarChar
    private String nmDepto;

    @Email // Validar o campo como email
    private String email;

    @OneToMany(mappedBy = "departamentoFunc")
    private List<Funcionario> funcionarios = new ArrayList<>();
}
