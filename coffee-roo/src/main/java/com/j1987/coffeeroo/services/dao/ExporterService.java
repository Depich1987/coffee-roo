package com.j1987.coffeeroo.services.dao;

import java.util.List;

import com.j1987.coffeeroo.domain.JExporter;

public interface ExporterService {

	public long countExporters();

	public List<JExporter> findAllExporters();

	public JExporter findExporter(Long id);
	
	public List<JExporter> findExportersByCodeEquals(String code);

	public List<JExporter> findExporterEntries(int firstResult, int maxResults);

	public void persist(JExporter factory);

	public void remove(Long id);

	public void flush();

	public void clear();

	public JExporter merge(JExporter factory);

	public void update(JExporter factory);

	

}
