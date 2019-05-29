package com.ntgclarity.smartcompound.dataaccess.dao;

import java.util.List;
import java.util.Map;

import com.ntgclarity.smartcompound.common.entity.Compound;

/**Author: Heba**/

public interface CompoundDAO {

	List<Compound> getAllCompounds();

	Compound getCompound(Long id);

	Compound insertCompound(Compound compound);

	Compound updateCompound(Compound compound);

	List<Compound> loadCompounds(int first, int pageSize, String sortField,
			boolean ascending, Map<String, Object> filters);

	int getNumOfCompoundsRows(Map<String, Object> filters);

	Compound getCompoundByName(String compoundName);
	Compound getCompoundByDomain(String compoundDomain);
}
