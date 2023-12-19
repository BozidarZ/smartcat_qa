package smartcat.etl.model.json;
import java.util.ArrayList;
import java.lang.Integer;

public class Shift
{
	long id;
	long timesheet_id;
	long user_id;
    String date;
    long start;
    ArrayList<Break> breaks = new ArrayList<Break>();
    long finish;
    int department_id;
    Float sub_cost_centre;
    String tag;
    Integer tag_id;
    String status;
    String metadata;
    Integer leave_request_id;
    ArrayList<Allowance> allowance = new ArrayList<Allowance>();
    Integer shift_feedback_id;
    String approved_by;
    String approved_at;
    ArrayList<AwardInterpretation> award_interpretation = new ArrayList<AwardInterpretation>();
    float cost;
    public CostBreakdown cost_breakdown;
    long updated_at;
    long last_costed_at;
    
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getTimesheet_id() {
		return timesheet_id;
	}
	public void setTimesheet_id(long timesheet_id) {
		this.timesheet_id = timesheet_id;
	}
	public long getUser_id() {
		return user_id;
	}
	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public long getStart() {
		return start;
	}
	public void setStart(long start) {
		this.start = start;
	}
	public ArrayList<Break> getBreaks() {
		return breaks;
	}
	public void setBreaks(ArrayList<Break> breaks) {
		this.breaks = breaks;
	}
	public long getFinish() {
		return finish;
	}
	public void setFinish(long finish) {
		this.finish = finish;
	}
	public int getDepartment_id() {
		return department_id;
	}
	public void setDepartment_id(int department_id) {
		this.department_id = department_id;
	}
	public Float getSub_cost_centre() {
		return sub_cost_centre;
	}
	public void setSub_cost_centre(Float sub_cost_centre) {
		this.sub_cost_centre = sub_cost_centre;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public Integer getTag_id() {
		return tag_id;
	}
	public void setTag_id(Integer tag_id) {
		this.tag_id = tag_id;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMetadata() {
		return metadata;
	}
	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}
	public Integer getLeave_request_id() {
		return leave_request_id;
	}
	public void setLeave_request_id(Integer leave_request_id) {
		this.leave_request_id = leave_request_id;
	}
	public ArrayList<Allowance> getAllowance() {
		return allowance;
	}
	public void setAllowance(ArrayList<Allowance> allowance) {
		this.allowance = allowance;
	}
	public Integer getShift_feedback_id() {
		return shift_feedback_id;
	}
	public void setShift_feedback_id(Integer shift_feedback_id) {
		this.shift_feedback_id = shift_feedback_id;
	}
	public String getApproved_by() {
		return approved_by;
	}
	public void setApproved_by(String approved_by) {
		this.approved_by = approved_by;
	}
	public String getApproved_at() {
		return approved_at;
	}
	public void setApproved_at(String approved_at) {
		this.approved_at = approved_at;
	}
	public ArrayList<AwardInterpretation> getAward_interpretation() {
		return award_interpretation;
	}
	public void setAward_interpretation(ArrayList<AwardInterpretation> award_interpretation) {
		this.award_interpretation = award_interpretation;
	}
	public float getCost() {
		return cost;
	}
	public void setCost(float cost) {
		this.cost = cost;
	}
	public CostBreakdown getCost_breakdown() {
		return cost_breakdown;
	}
	public void setCost_breakdown(CostBreakdown cost_breakdown) {
		this.cost_breakdown = cost_breakdown;
	}
	public long getUpdated_at() {
		return updated_at;
	}
	public void setUpdated_at(long updated_at) {
		this.updated_at = updated_at;
	}
	public long getLast_costed_at() {
		return last_costed_at;
	}
	public void setLast_costed_at(long last_costed_at) {
		this.last_costed_at = last_costed_at;
	}
    
    
}