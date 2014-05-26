package com.j1987.coffeeroo.services.dao;

import java.util.List;

import com.j1987.coffeeroo.domain.JSupplier;

public interface SupplierService {

	public long countSuppliers();

	public List<JSupplier> findAllSuppliers();

	public JSupplier findSupplier(Long id);
	
	public List<JSupplier> findSuppliersByCodeEquals(String code);

	public List<JSupplier> findSupplierEntries(int firstResult, int maxResults);

	public void persist(JSupplier factory);

	public void remove(Long id);

	public void flush();

	public void clear();

	public JSupplier merge(JSupplier factory);

	public void update(JSupplier factory);

	

}
