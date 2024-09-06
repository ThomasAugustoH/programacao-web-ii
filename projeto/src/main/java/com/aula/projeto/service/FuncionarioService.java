package com.aula.projeto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aula.projeto.model.Departamento;
import com.aula.projeto.model.Funcionario;
import com.aula.projeto.repository.DepartamentoRepository;
import com.aula.projeto.repository.FuncionarioRepository;

@Service
public class FuncionarioService {
    
    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private DepartamentoRepository departamentoRepository;

    public List<Funcionario> findAll() {
        return funcionarioRepository.findAll();
    }

    public Funcionario findById(Integer pIdFuncionario) {
        Funcionario vFuncionario = funcionarioRepository
            .findById(pIdFuncionario)
            .orElseThrow();

        return vFuncionario;
    }

    public Funcionario insFuncionario(Funcionario pFuncionario, Integer pIdDepartamento) {
        Departamento vDepartamento = departamentoRepository
            .findById(pIdDepartamento)
            .orElseThrow();

        pFuncionario.setIdFuncionario(null);
        pFuncionario.setDepartamentoFunc(vDepartamento);

        return funcionarioRepository.save(pFuncionario);
    }

    public Funcionario updFuncionario(Funcionario pFuncionario, Integer pIdDepartamento) {
        Funcionario vFuncAtual = funcionarioRepository
            .findById(pFuncionario.getIdFuncionario())
            .orElseThrow();

        Departamento vDepartamento = departamentoRepository
            .findById(pIdDepartamento)
            .orElseThrow();

        if (pFuncionario.getNmFuncionario() != null) {
            vFuncAtual.setNmFuncionario(pFuncionario.getNmFuncionario());
        }
        if (pFuncionario.getEmail() != null) {
            vFuncAtual.setEmail(pFuncionario.getEmail());
        }
        vFuncAtual.setDepartamentoFunc(vDepartamento);

        return funcionarioRepository.save(vFuncAtual);
    }

    public void delFuncionario(Integer pIdFuncionario) {
        funcionarioRepository
            .findById(pIdFuncionario)
            .orElseThrow();

        funcionarioRepository.deleteById(pIdFuncionario);
    }

    public List<Funcionario> searchByDepto(Integer pIdDepartamento) {
        return funcionarioRepository.searchByDepto(pIdDepartamento);
    }

    public List<Funcionario> searchByNome(String pNome) {
        return funcionarioRepository.searchByNome(pNome);
    }
}
