package com.pwiii.agenda.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.pwiii.agenda.entity.ProjetoEntity;

@Repository
public interface ProjetoDao extends JpaRepository<ProjetoEntity, Integer> {}
