package com.example.rest.service;

import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import com.example.rest.model.*;

@Service
public class WorkShiftService {

	public ArrayList<Shift> GenerateShifts(List<Integer> ids, List<Integer> userIds) {
		ArrayList<Shift> shifts = new ArrayList<Shift>();

		for (int id : ids) {
			for (int userId : userIds) {
				shifts.add(GenerateShift(id, userId));
			}
		}

		return shifts;
	}

	public Shift GenerateShift(int id, int userId) {

		Shift workShift = new Shift();

		Random r = new Random();

		long shift_id = r.nextInt(10000000);

		long start = GetTimestamp_StartOfPreviousWeek();
		long end = GetTimestamp_EndOfPreviousWeek();

		workShift.setId(r.nextInt(10000000));
		workShift.setTimesheet_id(r.nextInt(10000000));
		workShift.setUser_id(r.nextInt(10000000));
		workShift.setStart(start);
		workShift.setFinish(end);
		workShift.setDate("2021-03-19");
		workShift.setDepartment_id(r.nextInt(10000000));
		workShift.setSub_cost_centre(r.nextFloat() * 100);
		workShift.setTag("tag");
		workShift.setTag_id(r.nextInt());
		workShift.setStatus("status");
		workShift.setMetadata("metadata");
		workShift.setLeave_request_id(r.nextInt(10000000));
		workShift.setShift_feedback_id(r.nextInt(10000000));
		workShift.setApproved_by("approved_by");
		workShift.setApproved_at("2021-03-19");
		workShift.setCost(r.nextFloat());
		workShift.setCost_breakdown(new CostBreakdown(r.nextFloat(), r.nextFloat()));
		workShift.setUpdated_at(end);
		workShift.setLast_costed_at(end);

		workShift.getBreaks().add(GenerateBreak(shift_id, end));
		workShift.getAllowance().add(GenerateAllowance(end));
		workShift.getAllowance().add(GenerateAllowance(end));
		workShift.getAllowance().add(GenerateAllowance(end));
		workShift.getAward_interpretation().add(GenerateAwardInterpretation());
		workShift.setCost_breakdown(GenerateCostBreakdown());

		return workShift;
	}

	public Break GenerateBreak(long shift_id, long updated_at) {
		Random r = new Random();

		Break _break = new Break();

		_break.setId(r.nextInt(10000000));
		_break.setShift_id(shift_id);
		_break.setStart(r.nextInt(10000000));
		_break.setFinish(r.nextInt(10000000));
		_break.setLength(r.nextInt(10000000));
		_break.setPaid(r.nextInt() % 2 == 0 ? true : false);
		_break.setUpdated_at(updated_at);

		return _break;
	}

	public Allowance GenerateAllowance(long updated_at) {
		Allowance allowances = new Allowance();

		Random r = new Random();

		allowances.setId(r.nextInt(10000000));
		allowances.setName("name");
		allowances.setValue(r.nextFloat());
		allowances.setUpdated_at(updated_at);
		allowances.setCost(r.nextFloat());

		return allowances;
	}

	public AwardInterpretation GenerateAwardInterpretation() {
		AwardInterpretation awardInterpretation = new AwardInterpretation();
		
		Random r = new Random();
		
		awardInterpretation.setUnits(r.nextFloat());
		awardInterpretation.setDate("2021-03-09");
		awardInterpretation.setExport_name("export name");
		awardInterpretation.setSecondary_export_name("secondary_export_name");
		awardInterpretation.setOrdinary_hours(r.nextInt() % 2 == 0 ? true : false);
		awardInterpretation.setCost(r.nextFloat());
		awardInterpretation.setFrom(r.nextInt(10000000));
		awardInterpretation.setTo(r.nextInt(10000000));

		return awardInterpretation;
	}

	public CostBreakdown GenerateCostBreakdown() {
		CostBreakdown costBreakdown = new CostBreakdown();

		return costBreakdown;
	}

	long GetTimestamp_StartOfPreviousWeek() {
		Date date = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int i = c.get(Calendar.DAY_OF_WEEK) - c.getFirstDayOfWeek();
		c.add(Calendar.DATE, -i - 7);
		Date start = c.getTime();

		return start.getTime() / 1000L;

	}

	long GetTimestamp_EndOfPreviousWeek() {
		Date date = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int i = c.get(Calendar.DAY_OF_WEEK) - c.getFirstDayOfWeek();
		c.add(Calendar.DATE, -i - 7);
		c.add(Calendar.DATE, 6);
		Date end = c.getTime();

		return end.getTime() / 1000L;

	}

}