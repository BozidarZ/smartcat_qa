package smartcat.etl.db;

import smartcat.etl.model.json.Allowance;
import smartcat.etl.model.json.AwardInterpretation;
import smartcat.etl.model.json.Break;
import smartcat.etl.model.json.Shift;

public interface IDbConnection {
	public void insertToShifts(String loaded_at, Shift workShift);

	public void insertToShift_breaks(long shift_id, long timesheet_id, String date, String loaded_at, Break break_);

	public void insertToShift_award_interpretation(long shift_id, long timesheet_id, String loaded_at,
			AwardInterpretation awardInterpretation);

	public void insertToShift_allowances(long shift_id, long timesheet_id, String date, String loaded_at,
			Allowance allowance);
}
