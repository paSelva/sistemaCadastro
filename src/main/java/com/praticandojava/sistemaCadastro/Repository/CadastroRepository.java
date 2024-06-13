package com.praticandojava.sistemaCadastro.Repository;

import com.praticandojava.sistemaCadastro.Model.Cadastro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CadastroRepository extends JpaRepository<Cadastro, Long> {
}
