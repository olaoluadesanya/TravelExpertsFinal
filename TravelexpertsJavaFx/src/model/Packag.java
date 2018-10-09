package model;

import java.util.Date;
import java.util.List;
import java.math.BigDecimal;



public class Packag
{
	private int packageId;

	private BigDecimal pkgAgencyCommission;

	private BigDecimal pkgBasePrice;

	private String pkgDesc;

	private Date pkgEndDate;

	private String pkgName;

	private Date pkgStartDate;

	public Packag(int packageId, BigDecimal pkgAgencyCommission, BigDecimal pkgBasePrice, String pkgDesc, Date pkgEndDate,
			String pkgName, Date pkgStartDate)
	{
		super();
		this.packageId = packageId;
		this.pkgAgencyCommission = pkgAgencyCommission;
		this.pkgBasePrice = pkgBasePrice;
		this.pkgDesc = pkgDesc;
		this.pkgEndDate = pkgEndDate;
		this.pkgName = pkgName;
		this.pkgStartDate = pkgStartDate;
	}

	public int getPackageId()
	{
		return packageId;
	}

	public void setPackageId(int packageId)
	{
		this.packageId = packageId;
	}

	public BigDecimal getPkgAgencyCommission()
	{
		return pkgAgencyCommission;
	}

	public void setPkgAgencyCommission(BigDecimal pkgAgencyCommission)
	{
		this.pkgAgencyCommission = pkgAgencyCommission;
	}

	public BigDecimal getPkgBasePrice()
	{
		return pkgBasePrice;
	}

	public void setPkgBasePrice(BigDecimal pkgBasePrice)
	{
		this.pkgBasePrice = pkgBasePrice;
	}

	public String getPkgDesc()
	{
		return pkgDesc;
	}

	public void setPkgDesc(String pkgDesc)
	{
		this.pkgDesc = pkgDesc;
	}

	public Date getPkgEndDate()
	{
		return pkgEndDate;
	}

	public void setPkgEndDate(Date pkgEndDate)
	{
		this.pkgEndDate = pkgEndDate;
	}

	public String getPkgName()
	{
		return pkgName;
	}

	public void setPkgName(String pkgName)
	{
		this.pkgName = pkgName;
	}

	public Date getPkgStartDate()
	{
		return pkgStartDate;
	}

	public void setPkgStartDate(Date pkgStartDate)
	{
		this.pkgStartDate = pkgStartDate;
	}
	
	

	

	@Override
	public String toString()
	{
		return  pkgName;
	}
	
	
	
}
