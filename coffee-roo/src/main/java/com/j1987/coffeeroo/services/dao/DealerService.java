package com.j1987.coffeeroo.services.dao;

import java.util.List;

import com.j1987.coffeeroo.domain.JDealer;

public interface DealerService {

	public long countDealers();

	public List<JDealer> findAllDealers();

	public JDealer findDealer(Long id);
	
	public List<JDealer> findDealersByCodeEquals(String code);

	public List<JDealer> findDealerEntries(int firstResult, int maxResults);

	public void persist(JDealer factory);

	public void remove(Long id);

	public void flush();

	public void clear();

	public JDealer merge(JDealer factory);

	public void update(JDealer factory);

	

}
