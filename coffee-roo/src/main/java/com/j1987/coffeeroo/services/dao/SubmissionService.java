package com.j1987.coffeeroo.services.dao;

import java.util.List;

import com.j1987.coffeeroo.domain.JFactory;
import com.j1987.coffeeroo.domain.JSubmissionForApproval;

public interface SubmissionService {

	public long countSubmissionForApprovals();

	public List<JSubmissionForApproval> findAllSubmissionForApprovals();

	public JSubmissionForApproval findSubmissionForApproval(Long id);

	public List<JSubmissionForApproval> findSubmissionForApprovalEntries(int firstResult, int maxResults);

	public void persist(JSubmissionForApproval submissionForApproval);

	public void remove(Long id);

	public void flush();

	public void clear();

	public JSubmissionForApproval merge(JSubmissionForApproval submissionForApproval);
	
	public void update(JSubmissionForApproval submissionForApproval);

	public List<JSubmissionForApproval> findSubmissionByFactoryForApprovalEntries(
			JFactory factory, int firstResult, int maxResults);

	public List<JSubmissionForApproval> findSubmissionForApprovalsByReferenceEquals(
			String reference);

}
