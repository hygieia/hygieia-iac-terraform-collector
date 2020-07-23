package com.capitalone.dashboard.service;

import com.capitalone.dashboard.model.ComponentData;

 interface TerraformService {

	 ComponentData getTerraformDetails();

	 ComponentData getTerraformDetailCountRun(String workspaceId, String status, String timeline, Integer range);

	 ComponentData getTerraformDetailAggregateRun(String workspaceId, String status, String timeline,Integer range);

	 ComponentData getCardDetails();

	 ComponentData getTerraformDetailTrend();

}
