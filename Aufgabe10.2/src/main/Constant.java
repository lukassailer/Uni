package main;

import java.util.Map;

public class Constant implements ComputationalNode
{
	private double value;

	public Constant(double value)
	{
		this.value = value;
	}
	
	public String toString()
	{
		return Double.toString(value);
	}

	@Override
	public double evaluate(String unknowns)
	{
		return value;
	}

	@Override
	public double evaluate(Map<String, Double> unknowns)
	{
		return 0;
	}

	@Override
	public ComputationalNode derivative(String unknown)
	{
		return new Constant(0);
	}

	@Override
	public ComputationalNode cleanUp()
	{
		return new Constant(value);
	}

	@Override
	public boolean isZero()
	{
		return (value == 0);
	}

	@Override
	public boolean isOne()
	{
		return (value == 1);
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(value);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Constant other = (Constant) obj;
		if (Double.doubleToLongBits(value) != Double.doubleToLongBits(other.value))
			return false;
		return true;
	}
}
