package com.example.rest.model;

public class CostBreakdown
{
    float award_cost;
    float allowance_cost;

    public CostBreakdown()
    {
        
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
}