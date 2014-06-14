package com.j1987.coffeeroo.services.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TemporalType;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.j1987.coffeeroo.domain.JAnalysis;
import com.j1987.coffeeroo.domain.JBridge;
import com.j1987.coffeeroo.framework.JUtils;
import com.j1987.coffeeroo.web.form.FilterAnalysisForm;

public class AnalysisServiceImpl implements AnalysisService{
	
	@PersistenceContext(name=JUtils.DB_PERSISTENCE_UNIT)
	private EntityManager entityManager;

	public AnalysisServiceImpl() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	/* (non-Javadoc)
	 * @see com.j1987.coffeeroo.services.dao.AnalysisService#countAnalyses(java.lang.String)
	 */
	@Override
	public long countAnalyses(String productType) {
		
		return entityManager.createQuery("SELECT COUNT(o) FROM JAnalysis o WHERE o.productType = :productType", Long.class)
				.setParameter("productType", productType)
				.getSingleResult();
	}

	@Override
	public List<JAnalysis> countAnalysisByFilterForm(FilterAnalysisForm filterAnalysisForm) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<JAnalysis> findAnalysisByFilterForm(FilterAnalysisForm filterAnalysisForm) {
		
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<JAnalysis> criteriaQuery = criteriaBuilder.createQuery(JAnalysis.class);
		Root<JAnalysis> rootAnalysis = criteriaQuery.from(JAnalysis.class);
		
		criteriaQuery.select(rootAnalysis);
		
		Predicate predicate = criteriaBuilder.conjunction();
		
		if(filterAnalysisForm.getAreaFilter() != 0){
			predicate=criteriaBuilder.and(predicate,criteriaBuilder.equal(rootAnalysis.get("provenance").get("id"), filterAnalysisForm.getAreaFilter()));
		}
		
		if(filterAnalysisForm.getFactoryFilter() != null && !filterAnalysisForm.getFactoryFilter().isEmpty() ){
			predicate=criteriaBuilder.and(predicate,criteriaBuilder.equal(rootAnalysis.get("factoryCode"), filterAnalysisForm.getFactoryFilter()));
		}
		
		if(filterAnalysisForm.getProductTypeFilter() != null && !filterAnalysisForm.getProductTypeFilter().isEmpty()){
			predicate=criteriaBuilder.and(predicate,criteriaBuilder.equal(rootAnalysis.get("productType"), filterAnalysisForm.getProductTypeFilter()));
		}
		
		if(filterAnalysisForm.getStatusFilter() != 0){
			predicate=criteriaBuilder.and(predicate,criteriaBuilder.equal(rootAnalysis.get("status"), filterAnalysisForm.getStatusFilter()));
		}
		
		if(filterAnalysisForm.getSd() != null ){
			predicate = criteriaBuilder.and(predicate,criteriaBuilder.greaterThanOrEqualTo(rootAnalysis.<Date>get("dateOfAnalysis"), filterAnalysisForm.getSd()));
		}
		
		if(filterAnalysisForm.getEd() != null ){
			predicate = criteriaBuilder.and(predicate,criteriaBuilder.lessThanOrEqualTo(rootAnalysis.<Date>get("dateOfAnalysis"), filterAnalysisForm.getEd()));
		}
		
		criteriaQuery.where(predicate);
		criteriaQuery.orderBy(criteriaBuilder.desc(rootAnalysis.get("dateOfAnalysis")));
		
		if(filterAnalysisForm.getFirstResult() != 0){
			entityManager.createQuery(criteriaQuery).setFirstResult(filterAnalysisForm.getFirstResult());
		}
		
		if(filterAnalysisForm.getMaxResult() != 0){
			entityManager.createQuery(criteriaQuery).setMaxResults(filterAnalysisForm.getMaxResult());
			}
		
		return entityManager.createQuery(criteriaQuery).getResultList();
	}


	/* (non-Javadoc)
	 * @see com.j1987.coffeeroo.services.dao.AnalysisService#countAnalysesInFactory(java.lang.String, java.lang.String)
	 */
	@Override
	public long countAnalysesInFactory(String factoryCode, String productType) {
		
		return entityManager.createQuery("SELECT COUNT(o) FROM JAnalysis o WHERE o.productType = :productType AND o.factoryCode = :factoryCode", Long.class)
				.setParameter("productType", productType)
				.setParameter("factoryCode", factoryCode)
				.getSingleResult();
	}



	/* (non-Javadoc)
	 * @see com.j1987.coffeeroo.services.dao.AnalysisService#countAnalysesInBridge(com.j1987.coffeeroo.domain.JBridge, java.lang.String)
	 */
	@Override
	public long countAnalysesInBridge(JBridge bridge, String productType) {
		
		return entityManager.createQuery("SELECT COUNT(o) FROM JAnalysis o WHERE o.productType = :productType AND o.bridge = :bridge", Long.class)
				.setParameter("productType", productType)
				.setParameter("bridge", bridge)
				.getSingleResult();
	}



	/* (non-Javadoc)
	 * @see com.j1987.coffeeroo.services.dao.AnalysisService#findAllAnalyses(java.lang.String)
	 */
	@Override
	public List<JAnalysis> findAllAnalyses(String productType) {
		// TODO Auto-generated method stub
		return null;
	}



	/* (non-Javadoc)
	 * @see com.j1987.coffeeroo.services.dao.AnalysisService#findAnalysis(java.lang.Long, java.lang.String)
	 */
	@Override
	public JAnalysis findAnalysis(Long id, String productType) {
        if (id == null) return null;
        return entityManager.find(JAnalysis.class, id);
	}



	/* (non-Javadoc)
	 * @see com.j1987.coffeeroo.services.dao.AnalysisService#findAnalysisEntries(int, int, java.lang.String)
	 */
	@Override
	public List<JAnalysis> findAnalysisEntries(int firstResult, int maxResults,String productType) {
		
		return entityManager.createQuery("SELECT o FROM JAnalysis o WHERE o.productType = :productType ORDER BY o.creationDate DESC", JAnalysis.class)
				.setParameter("productType", productType)
				.setFirstResult(firstResult)
				.setMaxResults(maxResults)
				.getResultList();
	}



	/* (non-Javadoc)
	 * @see com.j1987.coffeeroo.services.dao.AnalysisService#persist(com.j1987.coffeeroo.domain.JAnalysis)
	 */
	@Override
	@Transactional
	public void persist(JAnalysis analysis) {
		// TODO Auto-generated method stub
		this.entityManager.persist(analysis);
	}



	/* (non-Javadoc)
	 * @see com.j1987.coffeeroo.services.dao.AnalysisService#remove(java.lang.Long)
	 */
	@Override
	public void remove(Long id) {
		// TODO Auto-generated method stub
		
	}



	/* (non-Javadoc)
	 * @see com.j1987.coffeeroo.services.dao.AnalysisService#flush()
	 */
	@Override
	public void flush() {
		this.entityManager.flush();		
	}



	/* (non-Javadoc)
	 * @see com.j1987.coffeeroo.services.dao.AnalysisService#clear()
	 */
	@Override
	public void clear() {
		this.entityManager.clear();		
	}



	/* (non-Javadoc)
	 * @see com.j1987.coffeeroo.services.dao.AnalysisService#merge(com.j1987.coffeeroo.domain.JAnalysis)
	 */
	@Override
	@Transactional
	public JAnalysis merge(JAnalysis analysis) {
        
		JAnalysis merged = this.entityManager.merge(analysis);
        this.entityManager.flush();
        return merged;
	}
	
	/*********************************************
	 * 				  UPDATE COFFEE 			 *
	 * *******************************************/
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void updateCoffee(JAnalysis analysis){
		String updateSQLText = "UPDATE JAnalysis d SET d.reference = :reference,"+
								"d.numberLading = :numberLading,"+
								"d.status = :status,"+
								"d.dateOfAnalysis = :dateOfAnalysis,"+
								"d.truckNumber = :truckNumber,"+
								"d.netWeightOfProductAccepted = :netWeightOfProductAccepted,"+
								"d.startTime = :startTime,"+
								"d.endTime = :endTime,"+
								"d.totalOfBagPushed = :totalOfBagPushed,"+
								"d.totalOfReportedBags = :totalOfReportedBags,"+
								"d.numberOfBagAllowed = :numberOfBagAllowed,"+
								"d.sampleCode = :sampleCode,"+
								"d.numberSAIGIC = :numberSAIGIC,"+
								"d.provenance = :provenance,"+
								"d.tour = :tour,"+
								"d.provenanceName = :provenanceName,"+
								"d.dealerEntry = :dealerEntry,"+
								"d.dealerName = :dealerName,"+
								"d.factoryCode = :factoryCode,"+
								"d.factoryName = :factoryName,"+
								"d.bridge = :bridge,"+
								"d.exporterEntry = :exporterEntry,"+
								"d.exporterName = :exporterName,"+
								"d.supplierEntry = :supplierEntry,"+
								"d.supplierName = :supplierName,"+
								"d.creationDate = :creationDate,"+
								"d.createdBy = :createdBy,"+
								"d.modifiedBy = :modifiedBy,"+
								"d.modificationDate = :modificationDate,"+
								"d.productType = :productType,"+
								"d.acceptation = :acceptation,"+
								"d.conformity = :conformity,"+
								"d.tauxHumidite1 = :tauxHumidite1,"+
								"d.tauxHumidite2 = :tauxHumidite2,"+
								"d.tauxHumidite3 = :tauxHumidite3,"+
								"d.moyenneTauxHumidite = :moyenneTauxHumidite,"+
								
								"d.poidsMatieresEtrangeres = :poidsMatieresEtrangeres,"+
								"d.pourcentageMatieresEtrangeres = :pourcentageMatieresEtrangeres,"+
								"d.poidsDechetsParches = :poidsDechetsParches,"+
								"d.pourcentageDechetsParches = :pourcentageDechetsParches,"+
								"d.poidsDechetsCerise = :poidsDechetsCerise,"+
								"d.pourcentageDechetsCerise = :pourcentageDechetsCerise,"+
								"d.poidsDechetsDemiCerises = :poidsDechetsDemiCerises,"+
								"d.pourcentageDechetsDemiCerises = :pourcentageDechetsDemiCerises,"+
								"d.poidsDechetsCoques = :poidsDechetsCoques,"+
								"d.pourcentageDechetsCoques = :pourcentageDechetsCoques,"+
								"d.poidsDechetsPeaux = :poidsDechetsPeaux,"+
								"d.pourcentageDechetsPeaux = :pourcentageDechetsPeaux,"+
								"d.poidsSousTotalDechets = :poidsSousTotalDechets,"+
								"d.pourcentageSousTotalDechets = :pourcentageSousTotalDechets,"+
								"d.poidsHorsNormesGrainsNoirs = :poidsHorsNormesGrainsNoirs,"+
								"d.pourcentageHorsNormesGrainsNoirs = :pourcentageHorsNormesGrainsNoirs,"+
								"d.poidsHorsNormesGrainsDemiNoirs = :poidsHorsNormesGrainsDemiNoirs,"+
								"d.pourcentageHorsNormesGrainsDemiNoirs = :pourcentageHorsNormesGrainsDemiNoirs,"+
								"d.poidsHorsNormesBrisures = :poidsHorsNormesBrisures,"+
								"d.pourcentageHorsNormesBrisures = :pourcentageHorsNormesBrisures,"+
								"d.poidsSousTotalHorsNormes = :poidsSousTotalHorsNormes,"+
								"d.pourcentageSousTotalHorsNormes = :pourcentageSousTotalHorsNormes,"+
								"d.poidsGrainsAcceptablesVert = :poidsGrainsAcceptablesVert,"+
								"d.pourcentageGrainsAcceptablesVert = :pourcentageGrainsAcceptablesVert,"+
								"d.poidsGrainsAcceptablesSpongieux = :poidsGrainsAcceptablesSpongieux,"+
								"d.pourcentageGrainsAcceptablesSpongieux = :pourcentageGrainsAcceptablesSpongieux,"+
								"d.poidsGrainsAcceptablesDemiSombre = :poidsGrainsAcceptablesDemiSombre,"+
								"d.pourcentageGrainsAcceptablesDemiSombre = :pourcentageGrainsAcceptablesDemiSombre,"+
								"d.poidsGrainsAcceptablesScolytes = :poidsGrainsAcceptablesScolytes,"+
								"d.pourcentageGrainsAcceptablesScolytes = :pourcentageGrainsAcceptablesScolytes,"+
								"d.poidsGrainsAcceptablesIndesirables = :poidsGrainsAcceptablesIndesirables,"+
								"d.pourcentageGrainsAcceptablesIndesirables = :pourcentageGrainsAcceptablesIndesirables,"+
								"d.poidsGrainsAcceptablesImmature = :poidsGrainsAcceptablesImmature,"+
								"d.pourcentageGrainsAcceptablesImmature = :pourcentageGrainsAcceptablesImmature,"+
								"d.calibrageTamis18 = :calibrageTamis18,"+
								"d.calibrageTamis16 = :calibrageTamis16,"+
								"d.calibrageTamis14 = :calibrageTamis14,"+
								"d.calibrageTamis12 = :calibrageTamis12,"+
								"d.calibrageTamis10 = :calibrageTamis10,"+
								"d.calibrageBase = :calibrageBase,"+
								"d.pourcentageGradeG0 = :pourcentageGradeG0,"+
								"d.pourcentageGradeG1 = :pourcentageGradeG1,"+
								"d.pourcentageGradeG2 = :pourcentageGradeG2,"+
								"d.pourcentageGradeG3 = :pourcentageGradeG3,"+
								"d.pourcentageGradeG4 = :pourcentageGradeG4,"+
								"d.pourcentageGradeHN = :pourcentageGradeHN "+
								
								" WHERE d.id= :id";
		
		this.entityManager.createQuery(updateSQLText)
		.setParameter("id", analysis.getId())
		.setParameter("reference", analysis.getReference())
		.setParameter("status", analysis.getStatus())	
		.setParameter("dateOfAnalysis", analysis.getDateOfAnalysis(),TemporalType.DATE)
		.setParameter("truckNumber", analysis.getTruckNumber())
		.setParameter("netWeightOfProductAccepted", analysis.getNetWeightOfProductAccepted())
		.setParameter("startTime", analysis.getStartTime(),TemporalType.TIME)
		.setParameter("endTime", analysis.getEndTime(),TemporalType.TIME)
		.setParameter("totalOfBagPushed", analysis.getTotalOfBagPushed())
		.setParameter("totalOfReportedBags", analysis.getTotalOfReportedBags())
		.setParameter("numberOfBagAllowed", analysis.getNumberOfBagAllowed())
		.setParameter("sampleCode", analysis.getSampleCode())
		.setParameter("numberSAIGIC", analysis.getNumberSAIGIC())
		.setParameter("provenance", analysis.getProvenance())
		.setParameter("tour", analysis.getTour())
		.setParameter("provenanceName", analysis.getProvenanceName())
		.setParameter("dealerEntry", analysis.getDealerEntry())
		.setParameter("dealerName", analysis.getDealerName())
		.setParameter("factoryCode", analysis.getFactoryCode())
		.setParameter("factoryName", analysis.getFactoryName())
		.setParameter("bridge", analysis.getBridge())
		.setParameter("exporterEntry", analysis.getExporterEntry())
		.setParameter("exporterName", analysis.getExporterName())
		.setParameter("supplierEntry", analysis.getSupplierEntry())
		.setParameter("supplierName", analysis.getSupplierName())
		.setParameter("creationDate", analysis.getCreationDate(),TemporalType.DATE)
		.setParameter("createdBy", analysis.getCreatedBy())
		.setParameter("modifiedBy", analysis.getModifiedBy())
		.setParameter("modificationDate", analysis.getModificationDate(),TemporalType.DATE)
		.setParameter("productType", analysis.getProductType())
		.setParameter("acceptation", analysis.getAcceptation())
		.setParameter("conformity", analysis.getConformity())
		.setParameter("tauxHumidite1", analysis.getTauxHumidite1())
		.setParameter("tauxHumidite2", analysis.getTauxHumidite2())
		.setParameter("tauxHumidite3", analysis.getTauxHumidite3())
		.setParameter("moyenneTauxHumidite", analysis.getMoyenneTauxHumidite())
		
		.setParameter("poidsMatieresEtrangeres", analysis.getPoidsMatieresEtrangeres())
		.setParameter("pourcentageMatieresEtrangeres", analysis.getPourcentageMatieresEtrangeres())
		.setParameter("poidsDechetsParches", analysis.getPoidsDechetsParches())
		.setParameter("poidsDechetsCerise", analysis.getPoidsDechetsCerise())
		.setParameter("pourcentageDechetsCerise", analysis.getPourcentageDechetsCerise())
		.setParameter("poidsDechetsDemiCerise", analysis.getPoidsDechetsDemiCerises())
		.setParameter("poidsDechetsCoques", analysis.getPoidsDechetsCoques())
		.setParameter("pourcentageDechetsCoques", analysis.getPourcentageDechetsCoques())
		.setParameter("poidsDechetsPeaux", analysis.getPoidsDechetsPeaux())
		.setParameter("poidsSousTotalDechets", analysis.getPoidsSousTotalDechets())
		.setParameter("pourcentageSousTotalDechets", analysis.getPourcentageSousTotalDechets())
		.setParameter("poidsHorsNormesGrainsNoirs", analysis.getPoidsHorsNormesGrainsNoirs())
		.setParameter("pourcentageHorsNormesGrainsNoirs", analysis.getPourcentageHorsNormesGrainsNoirs())
		.setParameter("poidsHorsNormesGrainsDemiNoirs", analysis.getPoidsHorsNormesGrainsDemiNoirs())
		.setParameter("pourcentageHorsNormesGrainsDemiNoirs", analysis.getPourcentageHorsNormesGrainsDemiNoirs())
		.setParameter("poidsHorsNormesBrisures", analysis.getPoidsHorsNormesBrisures())
		.setParameter("pourcentageHorsNormesBrisures", analysis.getPourcentageHorsNormesBrisures())
		.setParameter("poidsSousTotalHorsNormes", analysis.getPoidsSousTotalHorsNormes())
		.setParameter("pourcentageSousTotalHorsNormes", analysis.getPourcentageSousTotalHorsNormes())
		.setParameter("poidsGrainsAcceptablesVert", analysis.getPoidsGrainsAcceptablesVert())
		.setParameter("pourcentageGrainsAcceptablesVert", analysis.getPourcentageGrainsAcceptablesVert())
		.setParameter("poidsGrainsAcceptablesSpongieux", analysis.getPoidsGrainsAcceptablesSpongieux())
		.setParameter("pourcentageGrainsAcceptablesSpongieux", analysis.getPourcentageGrainsAcceptablesSpongieux())
		.setParameter("poidsGrainsAcceptablesDemiSombre", analysis.getPoidsGrainsAcceptablesDemiSombre())
		.setParameter("pourcentageGrainsAcceptablesDemiSombre", analysis.getPourcentageGrainsAcceptablesDemiSombre())
		.setParameter("poidsGrainsAcceptablesScolytes", analysis.getPoidsGrainsAcceptablesScolytes())
		.setParameter("pourcentageGrainsAcceptablesScolytes", analysis.getPourcentageGrainsAcceptablesScolytes())
		.setParameter("poidsGrainsAcceptablesIndesirables", analysis.getPoidsGrainsAcceptablesIndesirables())
		.setParameter("pourcentageGrainsAcceptablesIndesirables", analysis.getPourcentageGrainsAcceptablesIndesirables())
		.setParameter("poidsGrainsAcceptablesImmature", analysis.getPoidsGrainsAcceptablesImmature())
		.setParameter("pourcentageGrainsAcceptablesImmature", analysis.getPourcentageGrainsAcceptablesImmature())
		.setParameter("calibrageTamis18", analysis.getCalibrageTamis18())
		.setParameter("calibrageTamis16", analysis.getCalibrageTamis16())
		.setParameter("calibrageTamis14", analysis.getCalibrageTamis14())
		.setParameter("calibrageTamis12", analysis.getCalibrageTamis12())
		.setParameter("calibrageTamis10", analysis.getCalibrageTamis10())
		.setParameter("calibrageBase", analysis.getCalibrageBase())
		.setParameter("pourcentageGradeG0", analysis.getPourcentageGradeG0())
		.setParameter("pourcentageGradeG1", analysis.getPourcentageGradeG1())
		.setParameter("pourcentageGradeG2", analysis.getPourcentageGradeG2())
		.setParameter("pourcentageGradeG3", analysis.getPourcentageGradeG3())
		.setParameter("pourcentageGradeG4", analysis.getPourcentageGradeG4())
		.setParameter("pourcentageGradeHN", analysis.getPourcentageGradeHN())
		.executeUpdate();
	}
		

	/*********************************************
	 * 				  UPDATE COCOA 			 *
	 * *******************************************/		
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void updateCocoa(JAnalysis analysis){
		String updateSQLText = "UPDATE JAnalysis d SET d.reference = :reference ,"+
								" d.numberLading = :numberLading ,"+
								" d.status = :status ,"+
								" d.dateOfAnalysis = :dateOfAnalysis ,"+
								" d.truckNumber = :truckNumber ,"+
								" d.netWeightOfProductAccepted = :netWeightOfProductAccepted ,"+
								" d.startTime = :startTime ,"+
								" d.endTime = :endTime ,"+
								" d.totalOfBagPushed = :totalOfBagPushed ,"+
								" d.totalOfReportedBags = :totalOfReportedBags ,"+
								" d.numberOfBagAllowed = :numberOfBagAllowed ,"+
								" d.sampleCode = :sampleCode ,"+
								" d.numberSAIGIC = :numberSAIGIC ,"+
								" d.provenance = :provenance ,"+
								" d.tour = :tour,"+
								" d.provenanceName = :provenanceName ,"+
								" d.dealerEntry = :dealerEntry ,"+
								" d.dealerName = :dealerName ,"+
								" d.factoryCode = :factoryCode ,"+
								" d.factoryName = :factoryName ,"+
								" d.bridge = :bridge ,"+
								" d.exporterEntry = :exporterEntry ,"+
								" d.exporterName = :exporterName ,"+
								" d.supplierEntry = :supplierEntry ,"+
								" d.supplierName = :supplierName ,"+
								" d.creationDate = :creationDate ,"+
								" d.createdBy = :createdBy ,"+
								" d.modifiedBy = :modifiedBy ,"+
								" d.modificationDate = :modificationDate ,"+
								" d.productType = :productType ,"+
								" d.acceptation = :acceptation ,"+
								" d.conformity = :conformity ,"+
								" d.tauxHumidite1 = :tauxHumidite1 ,"+
								" d.tauxHumidite2 = :tauxHumidite2 ,"+
								" d.tauxHumidite3 = :tauxHumidite3 ,"+
								" d.moyenneTauxHumidite = :moyenneTauxHumidite ,"+
								
								
								" d.nombreFeves = :nombreFeves ,"+
								" d.fevesPar100g = :fevesPar100g ,"+
								" d.poidsBrisures = :poidsBrisures ,"+
								" d.pourcentageBrisures = :pourcentageBrisures ,"+
								" d.fevesMoisiesPlateau1 = :fevesMoisiesPlateau1 ,"+
								" d.fevesMoisiesPlateau2 = :fevesMoisiesPlateau2 ,"+
								" d.fevesMoisiesPlateau3 = :fevesMoisiesPlateau3 ,"+
								" d.totalFevesMoisies = :totalFevesMoisies ,"+
								" d.pourcentageFevesMoisies = :pourcentageFevesMoisies ,"+
								" d.fevesArdoiseesPlateau1 = :fevesArdoiseesPlateau1 ,"+
								" d.fevesArdoiseesPlateau2 = :fevesArdoiseesPlateau2 ,"+
								" d.fevesArdoiseesPlateau3 = :fevesArdoiseesPlateau3 ,"+
								" d.totalFevesArdoisees = :totalFevesArdoisees ,"+
								" d.pourcentageFevesArdoisees = :pourcentageFevesArdoisees ,"+
								" d.fevesMiteesPlateau1 = :fevesMiteesPlateau1 ,"+
								" d.fevesMiteesPlateau2 = :fevesMiteesPlateau2 ,"+
								" d.fevesMiteesPlateau3 = :fevesMiteesPlateau3 ,"+
								" d.totalFevesMitees = :totalFevesMitees ,"+
								" d.pourcentageFevesMitees = :pourcentageFevesMitees ,"+
								" d.fevesGermeesPlateau1 = :fevesGermeesPlateau1 ,"+
								" d.fevesGermeesPlateau2 = :fevesGermeesPlateau2 ,"+
								" d.fevesGermeesPlateau3 = :fevesGermeesPlateau3 ,"+
								" d.totalFevesGermees = :totalFevesGermees ,"+
								" d.pourcentageFevesGermees = :pourcentageFevesGermees ,"+
								" d.fevesPlatesPlateau1 = :fevesPlatesPlateau1 ,"+
								" d.fevesPlatesPlateau2 = :fevesPlatesPlateau2 ,"+
								" d.fevesPlatesPlateau3 = :fevesPlatesPlateau3 ,"+
								" d.totalFevesPlates = :totalFevesPlates ,"+
								" d.pourcentageFevesPlates = :pourcentageFevesPlates ,"+
								" d.totalFevesDefectueusesPlateau1 = :totalFevesDefectueusesPlateau1 ,"+
								" d.totalFevesDefectueusesPlateau2 = :totalFevesDefectueusesPlateau2 ,"+
								" d.totalFevesDefectueusesPlateau3 = :totalFevesDefectueusesPlateau3 ,"+
								" d.sommeTotalFevesDefectueuses = :sommeTotalFevesDefectueuses ,"+
								" d.pourcentageTotalFevesDefectueuses = :pourcentageTotalFevesDefectueuses ,"+
								" d.fevesViolettePlateau1 = :fevesViolettePlateau1 ,"+
								" d.fevesViolettePlateau2 = :fevesViolettePlateau2 ,"+
								" d.fevesViolettePlateau3 = :fevesViolettePlateau3 ,"+
								" d.totalFevesViolette = :totalFevesViolette ,"+
								" d.pourcentageFevesViolette = :pourcentageFevesViolette ,"+
								" d.classification = :classification "+
								
								" WHERE d.id= :id";
		
		this.entityManager.createQuery(updateSQLText)
						.setParameter("id", analysis.getId())
						.setParameter("reference", analysis.getReference())
						.setParameter("status", analysis.getStatus())	
						.setParameter("dateOfAnalysis", analysis.getDateOfAnalysis(),TemporalType.DATE)
						.setParameter("truckNumber", analysis.getTruckNumber())
						.setParameter("netWeightOfProductAccepted", analysis.getNetWeightOfProductAccepted())
						.setParameter("startTime", analysis.getStartTime(),TemporalType.TIME)
						.setParameter("endTime", analysis.getEndTime(),TemporalType.TIME)
						.setParameter("totalOfBagPushed", analysis.getTotalOfBagPushed())
						.setParameter("totalOfReportedBags", analysis.getTotalOfReportedBags())
						.setParameter("numberOfBagAllowed", analysis.getNumberOfBagAllowed())
						.setParameter("sampleCode", analysis.getSampleCode())
						.setParameter("numberSAIGIC", analysis.getNumberSAIGIC())
						.setParameter("provenance", analysis.getProvenance())
						.setParameter("tour", analysis.getTour())
						.setParameter("provenanceName", analysis.getProvenanceName())
						.setParameter("dealerEntry", analysis.getDealerEntry())
						.setParameter("dealerName", analysis.getDealerName())
						.setParameter("factoryCode", analysis.getFactoryCode())
						.setParameter("factoryName", analysis.getFactoryName())
						.setParameter("bridge", analysis.getBridge())
						.setParameter("exporterEntry", analysis.getExporterEntry())
						.setParameter("exporterName", analysis.getExporterName())
						.setParameter("supplierEntry", analysis.getSupplierEntry())
						.setParameter("supplierName", analysis.getSupplierName())
						.setParameter("creationDate", analysis.getCreationDate(),TemporalType.DATE)
						.setParameter("createdBy", analysis.getCreatedBy())
						.setParameter("modifiedBy", analysis.getModifiedBy())
						.setParameter("modificationDate", analysis.getModificationDate(),TemporalType.DATE)
						.setParameter("productType", analysis.getProductType())
						.setParameter("acceptation", analysis.getAcceptation())
						.setParameter("conformity", analysis.getConformity())
						.setParameter("tauxHumidite1", analysis.getTauxHumidite1())
						.setParameter("tauxHumidite2", analysis.getTauxHumidite2())
						.setParameter("tauxHumidite3", analysis.getTauxHumidite3())
						.setParameter("moyenneTauxHumidite", analysis.getMoyenneTauxHumidite())
						
						.setParameter("nombreFeves", analysis.getNombreFeves())
						.setParameter("fevesPar100g", analysis.getFevesPar100g())
						.setParameter("poidsBrisures", analysis.getPoidsBrisures())
						.setParameter("pourcentageBrisures", analysis.getPourcentageBrisures())
						.setParameter("fevesMoisiesPlateau1", analysis.getFevesMoisiesPlateau1())
						.setParameter("fevesMoisiesPlateau2", analysis.getFevesMoisiesPlateau2())
						.setParameter("fevesMoisiesPlateau3", analysis.getFevesMoisiesPlateau3())
						.setParameter("TotalfevesArdoisees", analysis.getTotalFevesMoisies())
						.setParameter("pourcentageFevesMoisies", analysis.getPourcentageFevesMoisies())
						.setParameter("fevesArdoiseesPlateau1", analysis.getFevesArdoiseesPlateau1())
						.setParameter("fevesArdoiseesPlateau2", analysis.getFevesArdoiseesPlateau2())
						.setParameter("fevesArdoiseesPlateau3", analysis.getFevesArdoiseesPlateau3())
						.setParameter("totalFevesArdoisees", analysis.getTotalFevesArdoisees())
						.setParameter("pourcentageFevesArdoisees", analysis.getPourcentageFevesArdoisees())
						.setParameter("fevesMiteesPlateau1", analysis.getFevesMiteesPlateau1())
						.setParameter("fevesMiteesPlateau2", analysis.getFevesMiteesPlateau2())
						.setParameter("fevesMiteesPlateau3", analysis.getFevesMiteesPlateau3())
						.setParameter("totalFevesMitees", analysis.getTotalFevesMitees())
						.setParameter("pourcentageFevesMitees", analysis.getPourcentageFevesMitees())
						.setParameter("fevesGermeesPlateau1", analysis.getFevesGermeesPlateau1())
						.setParameter("fevesGermeesPlateau2", analysis.getFevesGermeesPlateau2())
						.setParameter("fevesGermeesPlateau3", analysis.getFevesGermeesPlateau3())
						.setParameter("totalFevesGermees", analysis.getTotalFevesGermees())
						.setParameter("pourcentageFevesGermees", analysis.getPourcentageFevesGermees())
						.setParameter("fevesPlatesPlateau1", analysis.getFevesPlatesPlateau1())
						.setParameter("fevesPlatesPlateau2", analysis.getFevesPlatesPlateau2())
						.setParameter("fevesPlatesPlateau3", analysis.getFevesPlatesPlateau3())
						.setParameter("totalFevesPlates", analysis.getTotalFevesPlates())
						.setParameter("pourcentageFevesPlates", analysis.getPourcentageFevesPlates())
						.setParameter("totalFevesDefectueusesPlateau1", analysis.getTotalFevesDefectueusesPlateau1())
						.setParameter("totalFevesDefectueusesPlateau2", analysis.getTotalFevesDefectueusesPlateau2())
						.setParameter("totalFevesDefectueusesPlateau3", analysis.getTotalFevesDefectueusesPlateau3())
						.setParameter("pourcentageTotalFevesDefectueuses", analysis.getPourcentageTotalFevesDefectueuses())
						.setParameter("fevesViolettePlateau1", analysis.getFevesViolettePlateau1())
						.setParameter("fevesViolettePlateau2", analysis.getFevesViolettePlateau2())				
						.setParameter("fevesViolettePlateau3", analysis.getFevesViolettePlateau3())				
						.setParameter("totalFevesViolette", analysis.getTotalFevesViolette())				
						.setParameter("pourcentageFevesViolette", analysis.getPourcentageFevesViolette())				
						.setParameter("classification", analysis.getClassification())										
						.executeUpdate();
	}


	/* (non-Javadoc)
	 * @see com.j1987.coffeeroo.services.dao.AnalysisService#findAnalysisByFactoryEntries(com.j1987.coffeeroo.domain.JFactory, int, int)
	 */
	@Override
	public List<JAnalysis> findAnalysisByFactoryCodeEntries(String factoryCode, String productType,	int firstResult, int maxResults) {
		return entityManager.createQuery("SELECT o FROM JAnalysis o WHERE o.factoryCode = :factoryCode AND o.productType = :productType ORDER BY o.creationDate DESC", JAnalysis.class)
				.setParameter("factoryCode", factoryCode)
				.setParameter("productType", productType)
				.setFirstResult(firstResult)
				.setMaxResults(maxResults)
				.getResultList();
	}



	/* (non-Javadoc)
	 * @see com.j1987.coffeeroo.services.dao.AnalysisService#findAnalysisByFactory(com.j1987.coffeeroo.domain.JFactory)
	 */
	@Override
	public List<JAnalysis> findAnalysisByFactoryCode(String factoryCode, String productType) {
		return entityManager.createQuery("SELECT o FROM JAnalysis o WHERE o.factoryCode = :factoryCode AND o.productType = :productType ORDER BY o.creationDate DESC", JAnalysis.class)
				.setParameter("factoryCode", factoryCode)
				.setParameter("productType", productType)
				.getResultList();
	}



	/* (non-Javadoc)
	 * @see com.j1987.coffeeroo.services.dao.AnalysisService#findAnalysisByFactoryCodeListAndAnalysisDateBetween(java.util.List, java.util.Date, java.util.Date)
	 */
	@Override
	public List<JAnalysis> findAnalysisByFactoryCodeListAndAnalysisDateBetween(
			List<String> factoriesCode , String productType, Date startDate, Date endDate) {
		// TODO Auto-generated method stub
		return null;
	}



	/* (non-Javadoc)
	 * @see com.j1987.coffeeroo.services.dao.AnalysisService#findAnalysisByFactoryCodeListDateBetweenEntries(java.util.List, java.util.Date, java.util.Date, int, int)
	 */
	@Override
	public List<JAnalysis> findAnalysisByFactoryCodeListDateBetweenEntries(
			List<String> factoriesCode , String productType, Date startDate, Date endDate,
			int firstResult, int maxResults) {
		// TODO Auto-generated method stub
		return null;
	}



	/* (non-Javadoc)
	 * @see com.j1987.coffeeroo.services.dao.AnalysisService#findAnalysisByReferenceEquals(java.lang.String)
	 */
	@Override
	public List<JAnalysis> findAnalysisByReferenceEquals(String reference) {
    	return entityManager.createQuery("SELECT o FROM JAnalysis o WHERE o.reference = :reference", JAnalysis.class)
    			.setParameter("reference", reference)
    			.getResultList();
	}
	
	/* (non-Javadoc)
	 * @see com.j1987.coffeeroo.services.dao.AnalysisService#findAnalysisByReferenceEquals(java.lang.String)
	 */
	@Override
	public List<JAnalysis> findAnalysisByReferenceEqualsAndProductType(String reference,String productType) {
    	return entityManager.createQuery("SELECT o FROM JAnalysis o WHERE o.reference = :reference AND o.productType = :productType", JAnalysis.class)
    			.setParameter("reference", reference)
    			.setParameter("productType", productType)
    			.getResultList();
	}



	/* (non-Javadoc)
	 * @see com.j1987.coffeeroo.services.dao.AnalysisService#findNotSentAnalysisByReferenceList(java.util.List)
	 */
	@Override
	public List<JAnalysis> findRefusedAnalysisByReferenceList(List<String> referenceList) {
    	return entityManager.createQuery("SELECT o FROM JAnalysis o WHERE (o.reference IN :referenceList) AND o.status = :status", JAnalysis.class)
    			.setParameter("referenceList", referenceList)
    			.setParameter("status", false)
    			.getResultList();
	}



	/* (non-Javadoc)
	 * @see com.j1987.coffeeroo.services.dao.AnalysisService#findStandByAnalysisByReferenceList(java.util.List)
	 */
	@Override
	public List<JAnalysis> findStandByAnalysisByReferenceList(
			List<String> referenceList) {
		// TODO Auto-generated method stub
		return null;
	}



	/* (non-Javadoc)
	 * @see com.j1987.coffeeroo.services.dao.AnalysisService#findValidatedAnalysisByReferenceList(java.util.List)
	 */
	@Override
	public List<JAnalysis> findValidatedAnalysisByReferenceList(List<String> referenceList) {
    	return entityManager.createQuery("SELECT o FROM JAnalysis o WHERE (o.reference IN :referenceList) AND o.status = :status", JAnalysis.class)
    			.setParameter("referenceList", referenceList)
    			.setParameter("status", true)
    			.getResultList();
	}



	/* (non-Javadoc)
	 * @see com.j1987.coffeeroo.services.dao.AnalysisService#findStandByAnalysisByFactoryCodeListAndAnalysisDateBetween(java.util.List, java.util.Date, java.util.Date)
	 */
	@Override
	public List<JAnalysis> findRefusedAnalysisByFactoryCodeListAndAnalysisDateBetween(
			List<String> factoriesCode, String productType, Date startDate, Date endDate) {
		
	   	if (startDate == null) throw new IllegalArgumentException("The startDate argument is required");
        if (endDate == null) throw new IllegalArgumentException("The endDate argument is required");
        
        Date endDateTemp = DateUtils.addDays(endDate, 1);
        
        return entityManager.createQuery("SELECT p FROM JAnalysis AS p WHERE (p.factoryCode IN :factoriesCode) AND (p.status = :status) AND p.dateOfAnalysis BETWEEN :startDate AND :endDate ORDER BY   p.creationDate DESC", JAnalysis.class)
		.setParameter("factoriesCode", factoriesCode)
		.setParameter("startDate", startDate, TemporalType.DATE)
        .setParameter("endDate", endDateTemp, TemporalType.DATE)
        .setParameter("status", false)
        .getResultList();
	}



	/* (non-Javadoc)
	 * @see com.j1987.coffeeroo.services.dao.AnalysisService#findNotSentAnalysisByFactoryCodeListAndAnalysisDateBetween(java.util.List, java.util.Date, java.util.Date)
	 */
	@Override
	public List<JAnalysis> findAllowedAnalysisByFactoryCodeListAndAnalysisDateBetween(
			List<String> factoriesCode, String productType, Date startDate, Date endDate) {
	   	if (startDate == null) throw new IllegalArgumentException("The startDate argument is required");
        if (endDate == null) throw new IllegalArgumentException("The endDate argument is required");
        
        Date endDateTemp = DateUtils.addDays(endDate, 1);
        
        return entityManager.createQuery("SELECT p FROM JAnalysis AS p WHERE (p.factoryCode IN :factoriesCode) AND (p.status = :status) AND p.dateOfAnalysis BETWEEN :startDate AND :endDate ORDER BY   p.creationDate DESC", JAnalysis.class)
		.setParameter("factoriesCode", factoriesCode)
		.setParameter("startDate", startDate, TemporalType.DATE)
        .setParameter("endDate", endDateTemp, TemporalType.DATE)
        .setParameter("status", true)
        .getResultList();
	}



	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

}
