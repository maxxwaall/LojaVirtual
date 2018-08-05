package com.LojaVirtual.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.LojaVirtual.models.Produtos;

public interface ProdutoRepository extends CrudRepository<Produtos, String>{

	Produtos findByCodigo(long codigoProduto);

	Iterable<Produtos> findByCodigoIn(List<Long> list);

	

}
