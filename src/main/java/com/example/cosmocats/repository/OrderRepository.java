package com.example.cosmocats.repository;

import com.example.cosmocats.domain.Order;
import com.example.cosmocats.domain.Product;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
