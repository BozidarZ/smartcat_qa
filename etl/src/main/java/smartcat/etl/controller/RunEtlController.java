package smartcat.etl.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import smartcat.etl.db.DbConnectionType;
import smartcat.etl.db.DbFactory;
import smartcat.etl.db.IDbConnection;
import smartcat.etl.model.json.Allowance;
import smartcat.etl.model.json.AwardInterpretation;
import smartcat.etl.model.json.Break;
import smartcat.etl.model.json.Shift;
import smartcat.etl.service.EtlService;
import smartcat.etl.util.Util;;

@RestController
public class RunEtlController {

	@Autowired
	EtlService etlService;

	@Autowired
	private Environment environment;

	@GetMapping("/run")
	public ResponseEntity<String> run(@RequestParam(value = "ids", defaultValue = "empty") String ids,
			@RequestParam(value = "user_ids", defaultValue = "empty") String user_ids) throws Exception {
		try {

			etlService.loadData(environment, "/shifts?ids=" + ids + "&user_ids=" + user_ids);
			return new ResponseEntity<>("Successful", HttpStatus.OK);

		} catch (IOException e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.OK);
		}
	}
}
