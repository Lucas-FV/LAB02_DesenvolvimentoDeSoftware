package com.labdev.labdev_spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.labdev.labdev_spring.models.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}