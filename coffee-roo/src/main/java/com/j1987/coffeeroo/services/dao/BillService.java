package com.j1987.coffeeroo.services.dao;

import java.util.List;

import com.j1987.coffeeroo.domain.JBill;

public interface BillService {

	long countBills();

	long countBillsByFactoryCode(String factoryCode);

	List<JBill> findAllBills();

	JBill findBill(Long id);

	List<JBill> findBillEntries(int firstResult, int maxResults);

	List<JBill> findBillByFactoryCodeEntries(String factoryCode,
			int firstResult, int maxResults);

	List<JBill> findBillByFactoryCode(String factoryCode);

	void persist(JBill bill);

	void remove(Long id);

	void flush();

	void clear();

	JBill merge(JBill bill);

	List<JBill> findBillByFactoryCodeAndTourEntries(String factoryCode,
			Long tourId, int firstResult, int maxResults);

	List<JBill> findBillByReference(String reference);

}
