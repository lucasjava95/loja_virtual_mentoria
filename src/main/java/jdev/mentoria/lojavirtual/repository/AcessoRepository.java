package jdev.mentoria.lojavirtual.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import jdev.mentoria.lojavirtual.model.Acesso;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
@Transactional
public interface AcessoRepository extends JpaRepository<Acesso,Long> {

}
