package com.example.rest.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.example.rest.model.Shift;
import com.example.rest.service.WorkShiftService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@RestController
public class WorkShiftController {

	@Autowired
	private WorkShiftService workShiftService;

	@GetMapping("/shifts")
    public ResponseEntity<ArrayList<Shift>> shifts(
		@RequestParam(value = "ids", defaultValue = "empty") String ids, 
		@RequestParam(value = "user_ids", defaultValue = "empty") String user_ids
	) {

		if(ids.equals("empty") || user_ids.equals("empty"))
		{
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		else
		{
			List<Integer> idList = new ArrayList<>();
			String strNumbers[] = ids.split(",");
			for (String strNum : strNumbers) {
				idList.add(Integer.parseInt(strNum));
			}
			
			List<Integer> user_idList = new ArrayList<>();
			strNumbers = user_ids.split(",");
			for (String strNum : strNumbers) {
				user_idList.add(Integer.parseInt(strNum));
			}
			
			return new ResponseEntity<>(workShiftService.GenerateShifts(idList, user_idList), HttpStatus.OK);
		}
			
	}

}
