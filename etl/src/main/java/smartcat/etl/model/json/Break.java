package smartcat.etl.model.json;

public class Break
{
	long id;
	long shift_id;
    long start;
    long finish;
    int length;
    boolean paid;
    long updated_at;
    
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getShift_id() {
		return shift_id;
	}
	public void setShift_id(long shift_id) {
		this.shift_id = shift_id;
	}
	public long getStart() {
		return start;
	}
	public void setStart(long start) {
		this.start = start;
	}
	public long getFinish() {
		return finish;
	}
	public void setFinish(long finish) {
		this.finish = finish;
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public boolean isPaid() {
		return paid;
	}
	public void setPaid(boolean paid) {
		this.paid = paid;
	}
	public long getUpdated_at() {
		return updated_at;
	}
	public void setUpdated_at(long updated_at) {
		this.updated_at = updated_at;
	}

    
}