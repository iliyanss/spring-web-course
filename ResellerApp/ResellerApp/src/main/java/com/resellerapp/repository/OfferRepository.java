package com.resellerapp.repository;

import com.resellerapp.model.entity.OfferEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OfferRepository extends JpaRepository<OfferEntity, Long> {

    List<OfferEntity> findAllByAddedBy_Id(Long id);
    List<OfferEntity> findAllByAddedBy_Id_Not(Long id);
}
