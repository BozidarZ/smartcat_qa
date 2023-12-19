package smartcat.etl.model.json;

import java.lang.Integer;

public class AwardInterpretation
{
    float units;
    String date;
    String export_name;
    String secondary_export_name ;
    Boolean ordinary_hours;
    float cost;
    Integer from;
    Integer to;
    
	public float getUnits() {
		return units;
	}
	public void setUnits(float units) {
		this.units = units;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getExport_name() {
		return export_name;
	}
	public void setExport_name(String export_name) {
		this.export_name = export_name;
	}
	public String getSecondary_export_name() {
		return secondary_export_name;
	}
	public void setSecondary_export_name(String secondary_export_name) {
		this.secondary_export_name = secondary_export_name;
	}
	public Boolean getOrdinary_hours() {
		return ordinary_hours;
	}
	public void setOrdinary_hours(Boolean ordinary_hours) {
		this.ordinary_hours = ordinary_hours;
	}
	public float getCost() {
		return cost;
	}
	public void setCost(float cost) {
		this.cost = cost;
	}
	public Integer getFrom() {
		return from;
	}
	public void setFrom(Integer from) {
		this.from = from;
	}
	public Integer getTo() {
		return to;
	}
	public void setTo(Integer to) {
		this.to = to;
	}

    
}