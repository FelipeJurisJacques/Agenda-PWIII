package com.pwiii.agenda.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.pwiii.agenda.entity.AtividadeEntity;

@Repository
public interface AtividadeDao extends JpaRepository<AtividadeEntity, Integer> {}
