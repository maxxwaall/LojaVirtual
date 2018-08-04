package com.LojaVirtual.repository;

import org.springframework.data.repository.CrudRepository;

import com.LojaVirtual.models.Clientes;

public interface ClienteRepository extends CrudRepository<Clientes, String>{

	Clientes findByCodigo(long codigoCliente);

	
}
