package com.LojaVirtual.repository;

import org.springframework.data.repository.CrudRepository;

import com.LojaVirtual.models.Produtos;

public interface ProdutoRepository extends CrudRepository<Produtos, String>{

	Produtos findByCodigo(long parseLong);

	

}
