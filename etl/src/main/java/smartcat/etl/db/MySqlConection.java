package smartcat.etl.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import smartcat.etl.model.json.Allowance;
import smartcat.etl.model.json.AwardInterpretation;
import smartcat.etl.model.json.Break;
import smartcat.etl.model.json.Shift;
import smartcat.etl.util.Util;

public class MySqlConection implements IDbConnection {

	private Connection conn;

	public MySqlConection(Environment environment) throws SQLException, ClassNotFoundException {
		conn = MySqlConnectionSingleton.getInstance(environment);
	}

	public void insertToShifts(String loaded_at, Shift workShift) {

		String query = "insert into shifts( id, timesheet_id, user_id, " + "department_id, date, start, finish, "
				+ "break_start, break_finish, break_length, sub_cost_centre, "
				+ "tag, tag_id, status, metadata, leave_request_id, "
				+ "shift_feedback_id, approved_by, approved_at, cost, "
				+ "cost_breakdown, updated_at, record_id, last_costed_at, " + "loaded_at) "
				+ "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, " + "?, ?, ?, ?, ?, ?, ?, ?, ?)";

		Break b = workShift.getBreaks().get(0);
		String break_start = Util.getEstDateFromTimestamp(b.getStart());
		String break_finish = Util.getEstDateFromTimestamp(b.getFinish());

		PreparedStatement preparedStmt;

		try {

			preparedStmt = conn.prepareStatement(query);

			preparedStmt.setLong(1, workShift.getId());
			preparedStmt.setLong(2, workShift.getTimesheet_id());
			preparedStmt.setLong(3, workShift.getUser_id());
			preparedStmt.setInt(4, workShift.getDepartment_id());
			preparedStmt.setString(5, workShift.getDate());
			preparedStmt.setString(6, Util.getEstDateFromTimestamp(workShift.getStart()));
			preparedStmt.setString(7, Util.getEstDateFromTimestamp(workShift.getFinish()));
			preparedStmt.setString(8, break_start);
			preparedStmt.setString(9, break_finish);
			preparedStmt.setInt(10, b.getLength());
			preparedStmt.setFloat(11, workShift.getSub_cost_centre());
			preparedStmt.setString(12, workShift.getTag());
			preparedStmt.setInt(13, workShift.getTag_id());
			preparedStmt.setString(14, workShift.getStatus());
			preparedStmt.setString(15, workShift.getMetadata());
			preparedStmt.setInt(16, workShift.getLeave_request_id());
			preparedStmt.setInt(17, workShift.getShift_feedback_id());
			preparedStmt.setString(18, workShift.getApproved_by());
			preparedStmt.setString(19, workShift.getApproved_at());
			preparedStmt.setFloat(20, workShift.getCost());
			preparedStmt.setString(21, workShift.getCost_breakdown().toString());
			preparedStmt.setString(22, Util.getEstDateFromTimestamp(workShift.getUpdated_at()));
			preparedStmt.setInt(23, workShift.getLeave_request_id());
			preparedStmt.setString(24, Util.getEstDateFromTimestamp(workShift.getLast_costed_at()));
			preparedStmt.setString(25, loaded_at);

			preparedStmt.execute();

		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	public void insertToShift_breaks(long shift_id, long timesheet_id, String date, String loaded_at, Break break_) {

		String query = "insert into shift_breaks (id, shift_id, timesheet_id, date, start, finish, length, "
				+ "paid, updated_at, loaded_at) " + "values (?,?,?,?,?,?,?,?,?,?)";

		PreparedStatement preparedStmt;
		try {

			preparedStmt = conn.prepareStatement(query);

			preparedStmt.setLong(1, break_.getId());
			preparedStmt.setLong(2, shift_id);
			preparedStmt.setLong(3, timesheet_id);
			preparedStmt.setString(4, date);
			preparedStmt.setString(5, Util.getEstDateFromTimestamp(break_.getStart()));
			preparedStmt.setString(6, Util.getEstDateFromTimestamp(break_.getFinish()));
			preparedStmt.setInt(7, 20);
			preparedStmt.setFloat(8, 20);
			preparedStmt.setString(9, Util.getEstDateFromTimestamp(break_.getUpdated_at()));
			preparedStmt.setString(10, loaded_at);

			preparedStmt.execute();

		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	public void insertToShift_award_interpretation(long shift_id, long timesheet_id, String loaded_at,
			AwardInterpretation awardInterpretation) {
		String query = "insert into shift_award_interpretation (shift_id, timesheet_id, units, "
				+ "date, export_name, secondary_export_name, ordinary_hours, cost, `from`, `to`,loaded_at) "
				+ "values (?,?,?,?,?,?,?,?,?,?,?)";

		PreparedStatement preparedStmt;
		try {

			preparedStmt = conn.prepareStatement(query);
			preparedStmt.setLong(1, shift_id);		
			preparedStmt.setLong(2, timesheet_id);
			preparedStmt.setFloat(3, awardInterpretation.getUnits());
			preparedStmt.setString(4, awardInterpretation.getDate());
			preparedStmt.setString(5, awardInterpretation.getExport_name());
			preparedStmt.setString(6, awardInterpretation.getSecondary_export_name());
			preparedStmt.setBoolean(7, awardInterpretation.getOrdinary_hours());
			preparedStmt.setFloat(8, awardInterpretation.getCost());
			preparedStmt.setString(9, Util.getEstDateFromTimestamp(awardInterpretation.getFrom()));
			preparedStmt.setString(10, Util.getEstDateFromTimestamp(awardInterpretation.getTo()));
			preparedStmt.setString(11, loaded_at);

			preparedStmt.execute();

		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	public void insertToShift_allowances(long shift_id, long timesheet_id, String date, String loaded_at,
			Allowance allowance) {

		String query = "insert into shift_allowances (id, shift_id, timesheet_id, date, name, value, updated_at, cost, loaded_at)"
				+ "values (? ,? ,? ,? ,? ,? ,? ,? ,?)";

		PreparedStatement preparedStmt;
		try {

			preparedStmt = conn.prepareStatement(query);

			preparedStmt.setLong(1, allowance.getId());
			preparedStmt.setLong(2, shift_id);
			preparedStmt.setLong(3, timesheet_id);
			preparedStmt.setString(4, date);
			preparedStmt.setString(5, allowance.getName());
			preparedStmt.setFloat(6, allowance.getValue());
			preparedStmt.setString(7, Util.getEstDateFromTimestamp(allowance.getUpdated_at()));
			preparedStmt.setFloat(8, allowance.getCost());
			preparedStmt.setString(9, loaded_at);

			preparedStmt.execute();

		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

}
