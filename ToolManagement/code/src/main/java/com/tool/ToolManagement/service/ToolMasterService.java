package com.tool.ToolManagement.service;

import java.util.List;
import java.util.Optional;

import com.tool.ToolManagement.model.GetResponseDto;
import com.tool.ToolManagement.model.ItemDto;
import com.tool.ToolManagement.model.ItemToolMappingDto;
import com.tool.ToolManagement.model.ResponseDto;
import com.tool.ToolManagement.model.ToolProductivity;
import com.tool.ToolManagement.model.ToolProductivityDto;
import com.tool.ToolManagement.model.ToolsDto;

/**
 * @author pc
 *
 */
public interface ToolMasterService {

	public ResponseDto getCountOfToolsRequired(String sap_code, Integer partsToProduce);
	
	public ResponseDto deleteToolById(String id);
	
	public Optional<ToolProductivity> getToolById(String id);
	
	public List<ToolProductivity> getAllProductivityTools();
	
	public ResponseDto saveOrUpdateTool(ToolsDto toolsDto);
	
	public ResponseDto saveOrUpdateToolProductivity(ToolProductivityDto toolProductivityDto);
	
	public ResponseDto saveOrUpdateItem(ItemDto itemDto);
	
	public ResponseDto saveOrUpdateItemToolMapping(ItemToolMappingDto itemToolMappingDto);
	
	public GetResponseDto getTotalPriceOfTool(String id , Integer quantity);
	
	public GetResponseDto findToolsByDate(String startdate, String enddate);
}
