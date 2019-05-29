package com.ntgclarity.smartcompound.business.serviceimpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntgclarity.smartcompound.business.service.CompoundService;
import com.ntgclarity.smartcompound.common.entity.Compound;
import com.ntgclarity.smartcompound.common.exception.SmartCompoundException;
import com.ntgclarity.smartcompound.dataaccess.dao.CompoundDAO;

/** Author: Heba **/

@Service
public class CompoundServiceImpl implements CompoundService {

	@Autowired
	private CompoundDAO compoundDAO;

	@Override
	public List<Compound> getAllCompounds() {
		return compoundDAO.getAllCompounds();
	}

	@Override
	public Compound getCompound(Long id) {
		if (id != null) {
			return compoundDAO.getCompound(id);
		}
		return null;
	}

	/**
	 * methodCreater:nessma create Compound this metohd will call
	 * CompoundDAO.insertCompound
	 * 
	 * @throws SmartCompoundException
	 **/
	@Override
	public Compound insertCompound(Compound compound)
			throws SmartCompoundException {

		Compound c1 = compoundDAO.getCompoundByName(compound.getCompoundName());
		Compound c2 = compoundDAO.getCompoundByDomain(compound.getDomain());

		if (c1 != null) {
			throw new SmartCompoundException(
					"SMART_COMPOUND_CHECK_EXISTENT_OF_COMPOUND_NAME");
		} else if (c2 != null) {

			throw new SmartCompoundException(
					"SMART_COMPOUND_CHECK_EXISTENT_OF_DOMAIN_NAME");
		} else {
			return compoundDAO.insertCompound(compound);
		}

	}

	@Override
	public Compound updateCompound(Compound compound)
			throws SmartCompoundException {

		Compound checkedCompound = compoundDAO.getCompound(compound.getId());
		// the compoundName not changed .. only the domainName changed
		// or the both not changed
		if (checkedCompound.getCompoundName()
				.equals(compound.getCompoundName())) {
			if (checkedCompound.getDomain().equals(compound.getDomain())) {
				return compoundDAO.updateCompound(compound);
			} else {

				Compound c2 = compoundDAO.getCompoundByDomain(compound
						.getDomain());

				if (c2 != null) {
					// there is acompound with this domainName
					throw new SmartCompoundException(
							"SMART_COMPOUND_CHECK_EXISTENT_OF_DOMAIN_NAME");
				} else {
					// there is no compound with this domainName
					return compoundDAO.updateCompound(compound);
				}
			}

		} else {
			// only compoundName changed
			// or both
			Compound c1 = compoundDAO.getCompoundByName(compound
					.getCompoundName());
			Compound c2 = compoundDAO.getCompoundByDomain(compound.getDomain());
			if (c1 != null) {
				throw new SmartCompoundException(
						"SMART_COMPOUND_CHECK_EXISTENT_OF_COMPOUND_NAME");
			}

			else {
				if (checkedCompound.getDomain().equals(compound.getDomain())) {
					return compoundDAO.updateCompound(compound);
				} else {
					if (c2 != null) {

						throw new SmartCompoundException(
								"SMART_COMPOUND_CHECK_EXISTENT_OF_DOMAIN_NAME");
					} else {
						return compoundDAO.updateCompound(compound);
					}
				}
			}
		}

	}

	@Override
	public List<Compound> loadCompounds(int first, int pageSize,
			String sortField, boolean ascending, Map<String, Object> filters) {
		return compoundDAO.loadCompounds(first, pageSize, sortField, ascending,
				filters);
	}

	@Override
	public int getNumOfCompoundsRows(Map<String, Object> filters) {

		return compoundDAO.getNumOfCompoundsRows(filters);
	}

}
