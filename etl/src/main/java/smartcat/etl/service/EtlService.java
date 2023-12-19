package smartcat.etl.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.TimeZone;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import smartcat.etl.db.DbConnectionType;
import smartcat.etl.db.DbFactory;
import smartcat.etl.db.IDbConnection;
import smartcat.etl.model.json.Allowance;
import smartcat.etl.model.json.AwardInterpretation;
import smartcat.etl.model.json.Break;
import smartcat.etl.model.json.Shift;
import smartcat.etl.util.Util;

@Service
public class EtlService {

	public List<Shift> extractData(Environment environment, String request) throws IOException {

		String json = getJsonFromRestCall(environment, request);

		ObjectMapper objectMapper = new ObjectMapper();
		List<Shift> shifts = objectMapper.readValue(json, new TypeReference<List<Shift>>() {
		});

		return shifts;
	}

	public void loadData(Environment environment, String request) throws IOException, Exception {
		List<Shift> shifts = extractData(environment, request);

		TimeZone tz = TimeZone.getTimeZone("EST");
		DbFactory dbFactory = new DbFactory(environment);
		IDbConnection dbConnection = dbFactory.getDbConnection(DbConnectionType.MYSQL);

		for (Shift shift : shifts) {
			long shift_id = shift.getId();
			long timesheet_id = shift.getTimesheet_id();
			String date = shift.getDate();

			Calendar cal = new GregorianCalendar(tz);
			String loaded_at = Util.getEstDateFromTimestamp(cal.getTime().getTime() / 1000);

			dbConnection.insertToShifts(loaded_at, shift);

			for (Break break_ : shift.getBreaks()) {
				dbConnection.insertToShift_breaks(shift_id, timesheet_id, date, loaded_at, break_);
			}

			for (Allowance allowance : shift.getAllowance()) {
				dbConnection.insertToShift_allowances(shift_id, timesheet_id, date, loaded_at, allowance);
			}

			for (AwardInterpretation awardInterpretation : shift.getAward_interpretation()) {
				dbConnection.insertToShift_award_interpretation(shift_id, timesheet_id, loaded_at, awardInterpretation);
			}

		}
	}

	public String getJsonFromRestCall(Environment environment, String request) throws IOException {
		URL url = new URL(environment.getProperty("rest.url") + request);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Accept", "application/json");

		if (conn.getResponseCode() != 200) {
			throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
		}

		BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

		String json = "", outputTmp;

		while ((outputTmp = br.readLine()) != null) {
			json += outputTmp;
		}

		conn.disconnect();

		return json;
	}
}
