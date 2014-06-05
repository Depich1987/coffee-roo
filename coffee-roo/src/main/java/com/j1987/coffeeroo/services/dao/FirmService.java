package com.j1987.coffeeroo.services.dao;

import java.util.List;

import com.j1987.coffeeroo.domain.JFirm;

public interface FirmService {

	long countFirms();

	List<JFirm> findAllFirms();

	JFirm findFirm(Long id);

	List<JFirm> findFirmsByCodeEquals(String code);

	List<JFirm> findFirmEntries(int firstResult, int maxResults);

	void persist(JFirm firm);

	void remove(Long id);

	void flush();

	void clear();

	JFirm merge(JFirm firm);

	void update(JFirm firm);

}
