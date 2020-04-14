package com.capitalone.dashboard.rest;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bson.types.ObjectId;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.capitalone.dashboard.collector.DefaultTerraformClient;
import com.capitalone.dashboard.collector.TerraformSettings;
import com.capitalone.dashboard.model.Collector;
import com.capitalone.dashboard.model.CollectorType;
import com.capitalone.dashboard.model.ComponentData;
import com.capitalone.dashboard.model.Run;
import com.capitalone.dashboard.model.CollectorItem;
import com.capitalone.dashboard.model.Workspace;
import com.capitalone.dashboard.repository.BaseCollectorRepository;
import com.capitalone.dashboard.repository.ComponentRepository;
import com.capitalone.dashboard.repository.RunRepository;
import com.capitalone.dashboard.repository.CollectorItemRepository;
import com.capitalone.dashboard.repository.WorkspaceRepository;
import com.capitalone.dashboard.service.TerraformService;

@RestController
public class TerraformController {

	private final CollectorItemRepository terraformCollectoritemRepository;
	private final RunRepository<Run> runRepository;
	private final DefaultTerraformClient terraformClient;
	private final ComponentRepository dbComponentRepository;
	private final BaseCollectorRepository<Collector> baseCollectorRepository;
	private final WorkspaceRepository<Workspace> workspaceRepository;
	private final TerraformService terraformService;

	@Autowired
	public TerraformController(TaskScheduler taskScheduler,
			BaseCollectorRepository<Collector> baseCollectorRepository,
			CollectorItemRepository terraformCollectoritemRepository, RunRepository runRepository,
			DefaultTerraformClient terraformClient, TerraformSettings gitHubSettings,
			ComponentRepository dbComponentRepository,
			TerraformService terraformService,
			WorkspaceRepository workspaceRepository) {
		 
		this.baseCollectorRepository = baseCollectorRepository;
		this.terraformCollectoritemRepository = terraformCollectoritemRepository;
		this.runRepository = runRepository;
		this.terraformClient = terraformClient;
		this.dbComponentRepository = dbComponentRepository;
		this.workspaceRepository = workspaceRepository;
		this.terraformService = terraformService;
	}

    @RequestMapping(value = "/collector/terraform/{workspaceId}", method = GET, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Run>> getRunEventDetails(@PathVariable String workspaceId,
                                                                             @RequestParam(value = "type", required = false) String type) {
        return ResponseEntity.ok(runRepository.findByWorkspaceId(workspaceId));
    }
    
	/*
	 * @RequestMapping(value = "/collector/terraform/organization", method = GET,
	 * produces = APPLICATION_JSON_VALUE) public ResponseEntity<JSONObject>
	 * getOrganizationDetails() {
	 * 
	 * List<Collector> terraformCollectors =
	 * baseCollectorRepository.findByCollectorType(CollectorType.
	 * InfrastructureAsCode); ObjectId objId = ((Collector
	 * )terraformCollectors.get(0)).getId();
	 * 
	 * List<CollectorItem> items =
	 * terraformCollectoritemRepository.findByCollectorId(objId);
	 * 
	 * JSONObject organizationObject = new JSONObject();
	 * 
	 * 
	 * for(CollectorItem item : items) {
	 * 
	 * String organization =(String) item.getOptions().get("organization");
	 * 
	 * List<Workspace> workspaces =
	 * workspaceRepository.findByOrganization(organization);
	 * 
	 * Map<String, List<Run>> workSpaceMap = new HashMap<String, List<Run>>();
	 * 
	 * for(Workspace workspace : workspaces) { List<Run> runs =
	 * runRepository.findByWorkspaceId(workspace.getWorkspaceId());
	 * 
	 * workSpaceMap.put(workspace.getName(), runs);
	 * 
	 * }
	 * 
	 * organizationObject.put(organization, workSpaceMap); }
	 * 
	 * 
	 * 
	 * return ResponseEntity.ok(organizationObject); }
	 */
 
    
    
    @RequestMapping(value = "/collector/terraform/run", method = GET, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Run>> getRunDetails(@PathVariable String workspaceId, String millis) {
    	
		return ResponseEntity.ok(runRepository.findByWorkspaceId(workspaceId/* , millis */)); 
    	
    }
    
    @RequestMapping(value = "/collector/terraform", method = GET, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<ComponentData> getTerraformDetails() {
    	return ResponseEntity.ok(terraformService.getTerraformDetails()); 
    }
    
    
    @RequestMapping(value = "/collector/terraform/run/count", method = GET, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<ComponentData> getTerraformDetailCountRun(@RequestParam String workspace, @RequestParam String status,@RequestParam String timeline,@RequestParam Integer range) {
    	return ResponseEntity.ok(terraformService.getTerraformDetailCountRun(workspace, status, timeline, range)); 
    }
    
    
    @RequestMapping(value = "/collector/terraform/run/aggregate", method = GET, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<ComponentData> getTerraformDetailAggregateRun(@RequestParam String workspace, @RequestParam String status,@RequestParam String timeline,@RequestParam Integer range) {
    	return ResponseEntity.ok(terraformService.getTerraformDetailAggregateRun(workspace, status, timeline, range)); 
    }
    
   
  
    @RequestMapping(value = "/collector/terraform/card", method = GET, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<ComponentData> getCardDetails() {
    	
    	return ResponseEntity.ok(terraformService.getCardDetails()); 
    	
    }
}
