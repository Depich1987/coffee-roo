package com.j1987.coffeeroo.services.dao;

import java.util.List;

import com.j1987.coffeeroo.domain.JBridge;
import com.j1987.coffeeroo.domain.JFactory;

public interface BridgeService {

	long countBridges();
	
	long countBridgesInFactory(JFactory factory);

	List<JBridge> findAllBridges();

	List<JBridge> findBridgeEntries(int firstResult, int maxResults);
	
	JBridge findBridge(Long id);

	List<JBridge> findBridgesByCodeEquals(String code);
	
	List<JBridge> findAllBridgesInFactory(JFactory factory);
	
	List<JBridge> findBridgeInFactoryEntries(JFactory factory, int firstResult, int maxResults);

	void persist(JBridge firm);

	void remove(Long id);

	void flush();

	void clear();

	JBridge merge(JBridge firm);

	void update(JBridge firm);

}
