package net.javaguides.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.javaguides.springboot.model.Shoe;


@Repository
public interface ShoeRepository extends JpaRepository<Shoe, Long> {

}
