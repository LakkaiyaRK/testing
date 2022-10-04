package com.tool.ToolManagement.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tool.ToolManagement.model.GetResponseDto;
import com.tool.ToolManagement.model.ItemDto;
import com.tool.ToolManagement.model.ItemToolMappingDto;
import com.tool.ToolManagement.model.ResponseDto;
import com.tool.ToolManagement.model.ToolProductivity;
import com.tool.ToolManagement.model.ToolProductivityDto;
import com.tool.ToolManagement.model.ToolsDto;
import com.tool.ToolManagement.service.ToolMasterService;

/**
 * @author T ToolMasterController is a controller that consists of all the
 *         service related to Tools management includes create, update, retrive
 *         and delete operation
 *
 */
@RestController
@RequestMapping("/tool")
public class ToolMasterController {

	@Autowired
	ToolMasterService toolMasterService;

	/**
	 * To retrive the tools count based on filter parameters
	 * 
	 * @param sap_code       String
	 * @param partsToProduce Integer
	 * @return
	 */
	@PostMapping("/get_tool_count")
	public ResponseEntity<ResponseDto> getCountOfTools(@RequestParam String sap_code,
			@RequestParam Integer partsToProduce) {
		return new ResponseEntity<>(toolMasterService.getCountOfToolsRequired(sap_code, partsToProduce), HttpStatus.OK);
	}

	@PostMapping("/save_or_update_tool")
	public ResponseEntity<ResponseDto> saveOrUpdateTools(@RequestBody ToolsDto toolsDto) {
		return new ResponseEntity<>(toolMasterService.saveOrUpdateTool(toolsDto), HttpStatus.OK);
	} 

	@PostMapping("/save_or_update_tool_productivity")
	public ResponseEntity<ResponseDto> saveOrUpdateToolProductivity(@RequestBody ToolProductivityDto toolProductivityDto) {
		return new ResponseEntity<>(toolMasterService.saveOrUpdateToolProductivity(toolProductivityDto), HttpStatus.OK);
	}

	@PostMapping("/get_tool")
	public Optional<ToolProductivity> fetchToolById(String id) {
		return toolMasterService.getToolById(id);
	}

	@PostMapping("/get_all")
	public List<ToolProductivity> fetchAllProducticityTools() {
		return toolMasterService.getAllProductivityTools();
	}

	@PostMapping("/delete_tool")
	public ResponseEntity<ResponseDto> deleteToolById(@RequestParam String id) {
		return new ResponseEntity<>(toolMasterService.deleteToolById(id), HttpStatus.OK);
	}
	
	@PostMapping("/save_item")
	public ResponseEntity<ResponseDto> saveItem(ItemDto itemDto) {
		return new ResponseEntity<>(toolMasterService.saveOrUpdateItem(itemDto),HttpStatus.OK);
	}
	
	@PostMapping("/item_tool_map")
	public ResponseEntity<ResponseDto> saveItemToolMapping(@RequestBody ItemToolMappingDto itemToolMappingDto) {
		return new ResponseEntity<>(toolMasterService.saveOrUpdateItemToolMapping(itemToolMappingDto),HttpStatus.OK);
	}
	
	@PostMapping("/total_price")
	public ResponseEntity<GetResponseDto> getTotalPrice(@RequestParam String id , @RequestParam Integer quantity) {
		return new ResponseEntity<>(toolMasterService.getTotalPriceOfTool(id, quantity),HttpStatus.OK);
	}
	
	@GetMapping("/findtoolsbydate")
	public ResponseEntity<GetResponseDto> findToolsByDate(@RequestParam String startdate , @RequestParam String enddate) {
		return new ResponseEntity<>(toolMasterService.findToolsByDate(startdate, enddate),HttpStatus.OK);
	}
}
