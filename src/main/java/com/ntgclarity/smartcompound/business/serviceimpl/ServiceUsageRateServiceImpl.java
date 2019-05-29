package com.ntgclarity.smartcompound.business.serviceimpl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import com.ntgclarity.smartcompound.business.service.ServiceUsageRateService;
import com.ntgclarity.smartcompound.common.entity.Compound;
import com.ntgclarity.smartcompound.common.entity.Service;
import com.ntgclarity.smartcompound.common.entity.ServiceUsageRate;
import com.ntgclarity.smartcompound.dataaccess.dao.ServiceUsageRateDAO;

@org.springframework.stereotype.Service
public class ServiceUsageRateServiceImpl implements ServiceUsageRateService {

	@Autowired
	private ServiceUsageRateDAO serviceUsageRateDAO;

	@Override
	public Double getAllServiceUsageRate(Compound compound, Service service,
			Double usageAmount) {

		Double usageValue = 0.0;

		List<ServiceUsageRate> serviceUsageRates = serviceUsageRateDAO
				.getAllServiceUsageRate(compound, service);

		for (ServiceUsageRate s : serviceUsageRates) {

			Double from = s.getUsageAmountFrom();
			Double to = s.getUsageAmountTo();

			int lastIndex = serviceUsageRates.size() - 1;
			Double toLastIndex = serviceUsageRates.get(lastIndex)
					.getUsageAmountTo();

			if (usageAmount >= toLastIndex) {
				Double lastIndexUnitPrice = serviceUsageRates.get(lastIndex)
						.getUnitPrice();
				usageValue = lastIndexUnitPrice * usageAmount;
				break;
			}

			if (usageAmount > from && usageAmount <= to) {
				usageValue = s.getUnitPrice() * usageAmount;
				break;
			}

		}
		return usageValue;
	}

}
