package com.tool.ToolManagement.serviceImpl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.tool.ToolManagement.Repository.ItemRepository;
import com.tool.ToolManagement.Repository.ItemToolMappingRepository;
import com.tool.ToolManagement.Repository.ToolProductivityRepository;
import com.tool.ToolManagement.Repository.ToolRepository;
import com.tool.ToolManagement.model.GetResponseDto;
import com.tool.ToolManagement.model.Item;
import com.tool.ToolManagement.model.ItemDto;
import com.tool.ToolManagement.model.ItemToolMapping;
import com.tool.ToolManagement.model.ItemToolMappingDto;
import com.tool.ToolManagement.model.ResponseDto;
import com.tool.ToolManagement.model.ToolDto;
import com.tool.ToolManagement.model.ToolProductivity;
import com.tool.ToolManagement.model.ToolProductivityDto;
import com.tool.ToolManagement.model.Tools;
import com.tool.ToolManagement.model.ToolsDto;
import com.tool.ToolManagement.service.ToolMasterService;

/**
 * 
 * @author pc
 *
 */
@Service
public class ToolMasterServiceImpl implements ToolMasterService {

	@Resource
	ToolRepository toolMasterRepository;

	@Resource
	ToolProductivityRepository toolProductivityRepository;

	@Resource
	ItemRepository itemRepository;

	@Resource
	ItemToolMappingRepository itemToolMappingRepository;

	/**
	 *
	 */
	public ResponseDto getCountOfToolsRequired(String sap_code, Integer partsToProduce) {

		ResponseDto responseDto = new ResponseDto();

		LinkedHashMap<String, Object> entity = new LinkedHashMap<String, Object>();
		try {

			Tools tool = toolMasterRepository.findOneBySapcode(sap_code);

			if (tool != null) {

				ToolProductivity toolProductivitie = toolProductivityRepository.findByToolsId(tool.getId());
				if (toolProductivitie != null) {

					int toolLimit = toolProductivitie.getNew_life()
							+ (toolProductivitie.getNo_of_res() * toolProductivitie.getTool_after_res());
					int noOfTools = partsToProduce / toolLimit;

					responseDto.setStatus(true);
					entity.put("NoOfTools for the make" + tool.getMake() + ": ", noOfTools);
					responseDto.setData(entity);
//					return entity;
				} else {
					responseDto.setStatus(false);
					responseDto.setErrorCode("400");
					responseDto.setErrorMessage("No Productivity Data Found!");
				}
			} else {
				responseDto.setStatus(false);
				responseDto.setErrorCode("400");
				responseDto.setErrorMessage("Please Check Required Filter Data");
			}

		} catch (Exception e) {
			responseDto.setStatus(false);
			responseDto.setErrorCode("400");
			responseDto.setErrorMessage("Internal Error");
		}
		return responseDto;
	}

	// ** To Save Or Update Tool**

	@Override
	public ResponseDto saveOrUpdateTool(ToolsDto toolsDto) {

		ResponseDto responseDto = new ResponseDto();

		try {
			if (toolsDto.getId() == null) {
				Tools tool = new Tools();
				BeanUtils.copyProperties(toolsDto, tool);
				toolsDto.setDate(toolsDto.getDate().trim().concat(" 00:01:00"));
				DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
				DateTimeFormatter formatter3 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

				LocalDateTime date = LocalDateTime
						.parse(formatter3.format(formatter2.parse(toolsDto.getDate())).toString(), formatter3);
				tool.setDate(date);
				
				toolMasterRepository.save(tool);
				responseDto.setStatus(true);
				responseDto.setMessage("New Tool saved Successfully");
			} else {
				Tools tools = toolMasterRepository.findOneById(toolsDto.getId());
				if (tools != null) {
					toolsDto.setDate(toolsDto.getDate().trim().concat(" 00:00:00"));
					DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
					DateTimeFormatter formatter3 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

					LocalDateTime date = LocalDateTime
							.parse(formatter3.format(formatter2.parse(toolsDto.getDate())).toString(), formatter3);
					tools.setDate(date);
					BeanUtils.copyProperties(toolsDto, tools);
					toolMasterRepository.save(tools);
					ToolProductivity toolp = toolProductivityRepository.findByToolsId(tools.getId());
					toolp.setTools(tools);
					toolProductivityRepository.save(toolp);

					responseDto.setStatus(true);
					responseDto.setMessage("Tool Details Updated Successfully");
				} else {
					responseDto.setStatus(false);
					responseDto.setErrorCode("400");
					responseDto.setErrorMessage("No Data Found");
				}
			}
		} catch (Exception e) {
			responseDto.setStatus(false);
			responseDto.setErrorCode("400");
			responseDto.setErrorMessage("Internal Error");
		}

		return responseDto;
	}

	// ** To Save or Update ToolProductivity **

	@Override
	public ResponseDto saveOrUpdateToolProductivity(ToolProductivityDto toolProductivityDto) {
		ResponseDto responseDto = new ResponseDto();
		try {
			if (toolProductivityDto.getId() == null) {
				Tools tool = toolMasterRepository.findOneById(toolProductivityDto.getTools());
				if (tool != null) {
					ToolProductivity toolp = new ToolProductivity();
					BeanUtils.copyProperties(toolProductivityDto, toolp);
					toolp.setTools(tool);
					toolProductivityRepository.save(toolp);
					responseDto.setStatus(true);
					responseDto.setMessage("New Tool Productivity Details Saved Successfully");
				} else {
					responseDto.setStatus(false);
					responseDto.setErrorCode("400");
					responseDto.setErrorMessage("Invalid Tool Code");
				}
			} else {
				ToolProductivity toolp = toolProductivityRepository.findOneById(toolProductivityDto.getId());
				if (toolp != null) {
					BeanUtils.copyProperties(toolProductivityDto, toolp);
					toolProductivityRepository.save(toolp);
					responseDto.setStatus(true);
					responseDto.setMessage("Tool Productivity Details Updated");
				} else {
					responseDto.setStatus(false);
					responseDto.setErrorCode("400");
					responseDto.setErrorMessage("No Data Found");
				}
			}
		} catch (Exception e) {
			responseDto.setStatus(false);
			responseDto.setErrorCode("400");
			responseDto.setErrorMessage("Internal Error");
		}
		return responseDto;
	}

	// ** To Fetch one Tool Data **

	@Override
	public Optional<ToolProductivity> getToolById(String id) {
		ResponseDto responseDto = new ResponseDto();
		Optional<ToolProductivity> tool = toolProductivityRepository.findById(id);
		if (tool.isPresent()) {
			responseDto.setStatus(true);
			return tool;
		} else {
			return null;
		}
	}

	// ** To Fetch All Tools Data **

	@Override
	public List<ToolProductivity> getAllProductivityTools() {
		List<ToolProductivity> toolp = toolProductivityRepository.findAll();
		return toolp;
	}

	// ** To Delete Tool **

	@Override
	public ResponseDto deleteToolById(String id) {
		ResponseDto responseDto = new ResponseDto();

		try {
			Optional<Tools> tool = toolMasterRepository.findById(id);

			if (tool.isPresent()) {

				ToolProductivity toolp = toolProductivityRepository.findByToolsId(id);
				if (toolp != null) {
					toolProductivityRepository.delete(toolp);
					toolMasterRepository.deleteById(id);
					responseDto.setStatus(true);
					responseDto.setMessage("Tool and Productivity Details Deleted Successfully");
				} else {
					responseDto.setStatus(false);
					responseDto.setErrorCode("400");
					responseDto.setErrorMessage("Invalid Tool Code");
				}
			} else {
				responseDto.setStatus(false);
				responseDto.setErrorCode("400");
				responseDto.setErrorMessage("Please Check Required Filter Data");
			}
		} catch (Exception e) {
			responseDto.setStatus(false);
			responseDto.setErrorCode("400");
			responseDto.setErrorMessage("Internal Error");
		}
		return responseDto;
	}

	// ** save or update item with tools **

	@Override
	public ResponseDto saveOrUpdateItem(ItemDto itemDto) {
		ResponseDto responseDto = new ResponseDto();

		try {
			if (itemDto.getId() == null) {
				Item item = new Item();
				BeanUtils.copyProperties(itemDto, item);
				itemRepository.save(item);
				responseDto.setStatus(true);
				responseDto.setMessage("New Item saved Successfully");
			} else {
				Item item = itemRepository.findOneById(itemDto.getId());
				if (item != null) {
					BeanUtils.copyProperties(itemDto, item);
					itemRepository.save(item);
					ItemToolMapping itemmap = itemToolMappingRepository.findByItemId(item.getId());
					itemmap.setItem(item);
					itemToolMappingRepository.save(itemmap);
					responseDto.setStatus(true);
					responseDto.setMessage("Item Details Updated Successfully");
				} else {
					responseDto.setStatus(false);
					responseDto.setErrorCode("400");
					responseDto.setErrorMessage("No Data Found");
				}
			}
		} catch (Exception e) {
			responseDto.setStatus(false);
			responseDto.setErrorCode("400");
			responseDto.setErrorMessage("Internal Error");
		}
		return responseDto;
	}

	@Override
	public ResponseDto saveOrUpdateItemToolMapping(ItemToolMappingDto itemToolMappingDto) {
		ResponseDto responseDto = new ResponseDto();

		try {
			if (itemToolMappingDto.getId() == null) {
				ItemToolMapping itemToolMapping = new ItemToolMapping();
				Item item = itemRepository.findOneById(itemToolMappingDto.getItem());
				if (item != null) {
					itemToolMapping.setItem(item);
					List<Tools> tools = toolMasterRepository.findToolsByIdIn(itemToolMappingDto.getTools());
					if (tools.size() > 0) {
						itemToolMapping.setTools(tools);
						itemToolMappingRepository.save(itemToolMapping);
						responseDto.setStatus(true);
						responseDto.setMessage("Item Tool Mapped Successfully");
					} else {
						responseDto.setStatus(false);
						responseDto.setErrorCode("400");
						responseDto.setErrorMessage("No Tools Found");
					}
				} else {
					responseDto.setStatus(false);
					responseDto.setErrorCode("400");
					responseDto.setErrorMessage("No Item Found");
				}
			} else {
				ItemToolMapping itemToolMapping = itemToolMappingRepository.findOneById(itemToolMappingDto.getId());
				if (itemToolMapping != null) {
					Item item = itemRepository.findOneById(itemToolMappingDto.getItem());
					if (item != null) {
						itemToolMapping.setItem(item);
						List<Tools> tools = toolMasterRepository.findToolsByIdIn(itemToolMappingDto.getTools());
						if (tools.size() > 0) {
							itemToolMapping.setTools(tools);
							itemToolMappingRepository.save(itemToolMapping);
							responseDto.setStatus(true);
							responseDto.setMessage("Item Tool Details Updated Successfully");
						} else {
							responseDto.setStatus(false);
							responseDto.setErrorCode("400");
							responseDto.setErrorMessage("No Tools Found");
						}
					} else {
						responseDto.setStatus(false);
						responseDto.setErrorCode("400");
						responseDto.setErrorMessage("No Item Found");
					}
				} else {
					responseDto.setStatus(false);
					responseDto.setErrorCode("400");
					responseDto.setErrorMessage("No Data Found");
				}
			}
		} catch (Exception e) {
			responseDto.setStatus(false);
			responseDto.setErrorCode("400");
			responseDto.setErrorMessage("Internal Error");
		}
		return responseDto;
	}

	@Override
	public GetResponseDto getTotalPriceOfTool(String id, Integer quantity) {
		GetResponseDto responseDto = new GetResponseDto();

		List<ToolDto> toolDtos = new ArrayList<ToolDto>();
		try {

			ItemToolMapping itemToolMapping = itemToolMappingRepository.findByItemId(id);

			if (itemToolMapping != null) {
				List<String> toolpIds = new ArrayList<String>();
				List<Tools> tools = itemToolMapping.getTools();

				for (Tools tool : tools) {
					String toolpId = tool.getId();
					toolpIds.add(toolpId);
				}

				List<ToolProductivity> toolp = toolProductivityRepository.findByToolsIdIn(toolpIds);

				for (ToolProductivity tool : toolp) {

					Integer toolslimit = tool.getNew_life() + (tool.getNo_of_res() * tool.getTool_after_res());
					Integer toolsrequired = quantity / toolslimit;
					ToolDto toolDto = new ToolDto();
					toolDto.setTool_id(tool.getTools().getId());
					toolDto.setNo_of_tools_required(toolsrequired);

					Integer toolprice = tool.getNew_life_price() + (tool.getNo_of_res() * tool.getPrice_after_res());
					Integer totalprice = toolsrequired * toolprice;

					toolDto.setTotal_cost(totalprice);

					toolDtos.add(toolDto);
				}
				responseDto.setStatus(true);
				responseDto.setMessage("Success");
				responseDto.setData(toolDtos);

			} else {
				responseDto.setStatus(false);
				responseDto.setErrorCode("400");
				responseDto.setErrorMessage("No Item Present");
			}

		} catch (Exception e) {
			responseDto.setStatus(false);
			responseDto.setErrorCode("400");
			responseDto.setErrorMessage("Internal Error");
		}
		return responseDto;
	}

	public GetResponseDto findToolsByDate(String startdate, String enddate) {

		GetResponseDto responseDto = new GetResponseDto();
		try {
			startdate = startdate.trim().concat(" 00:00:00");
			enddate = enddate.trim().concat(" 00:00:00");

			DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
			DateTimeFormatter formatter3 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

			LocalDateTime start_date = LocalDateTime.parse(formatter3.format(formatter2.parse(startdate)).toString(),
					formatter3);
			LocalDateTime end_date = LocalDateTime.parse(formatter3.format(formatter2.parse(enddate)).toString(),
					formatter3);

			List<ToolsDto> toolsDtos = new ArrayList<ToolsDto>();
			if (start_date.isBefore(end_date)) {
				List<Tools> tools = toolMasterRepository.findByDateBetween(start_date, end_date.plusDays(1));

				if (tools.size() > 0) {
					for (Tools tool : tools) {
						ToolsDto toolsDto = new ToolsDto();
						BeanUtils.copyProperties(tool, toolsDto);
						toolsDto.setId(tool.getId());
						DateTimeFormatter formatter4 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
						DateTimeFormatter formatter5 = DateTimeFormatter.ofPattern("dd-MM-yyyy");
						toolsDto.setDate(
								formatter5.format(formatter4.parse(tool.getDate().toString().substring(0, 10))));
						toolsDtos.add(toolsDto);

					}
					responseDto.setStatus(true);
					responseDto.setMessage("Data Found");

					responseDto.setData(toolsDtos);
				} else {
					responseDto.setStatus(true);
					responseDto.setMessage("No Data Found");
					responseDto.setData(tools);
				}
			} else {
				responseDto.setStatus(false);
				responseDto.setErrorCode("400");
				responseDto.setErrorMessage("Check Date");
			}
		} catch (Exception e) {
			responseDto.setStatus(false);
			responseDto.setErrorCode("400");
			responseDto.setErrorMessage(e.toString());
		}
		return responseDto;
	}
}
