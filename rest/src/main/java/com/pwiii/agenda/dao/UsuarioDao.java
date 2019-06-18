package com.pwiii.agenda.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.pwiii.agenda.entity.UsuarioEntity;

@Repository
public interface UsuarioDao extends JpaRepository<UsuarioEntity, Integer> {

	@Query("SELECT obj FROM UsuarioEntity obj WHERE obj.nome LIKE %:nome%")
	Page<UsuarioEntity> search(@Param("nome") String nome, Pageable pageable);

	UsuarioEntity findByEmail(String email);
}
