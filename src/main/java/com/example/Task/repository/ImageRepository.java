package com.example.Task.repository;

import com.example.Task.model.ImageModel;
import com.example.Task.model.UsersModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Repository

public interface ImageRepository extends JpaRepository<ImageModel,Integer> {
    @Transactional
    List<ImageModel> findAllByAcceptedIsNull();
    @Transactional
    List<ImageModel> findALlByAcceptedIsTrue();
    @Transactional
    ImageModel findByIdEquals(Integer id);
    @Modifying
    @Transactional
    @Query("update ImageModel set accepted = :status where id = :id ")
    public void updateImageStatus(@Param("id") Integer id ,@Param("status") Boolean status);
}
