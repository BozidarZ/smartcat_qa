package smartcat.etl.model.json;

public class CostBreakdown {
	float award_cost;
	float allowance_cost;

	public CostBreakdown() {

	}

	public CostBreakdown(float award_cost, float allowance_cost) {
		this.award_cost = award_cost;
		this.allowance_cost = allowance_cost;
	}

	public float getAward_cost() {
		return this.award_cost;
	}

	public float getAllowance_cost() {
		return this.allowance_cost;
	}

	@Override
	public String toString() {
		return String.format("{\"award_cost\": %f, \"allowance_cost\": %f}", award_cost, allowance_cost);
	}
}