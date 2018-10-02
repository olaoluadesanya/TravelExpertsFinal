package model;

import java.util.Date;



public class Package
{
	private int packageId;

	private double pkgAgencyCommission;

	private double pkgBasePrice;

	private String pkgDesc;

	private Date pkgEndDate;

	private String pkgName;

	private Date pkgStartDate;

	public Package(int packageId, double pkgAgencyCommission, double pkgBasePrice, String pkgDesc, Date pkgEndDate,
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

	public double getPkgAgencyCommission()
	{
		return pkgAgencyCommission;
	}

	public void setPkgAgencyCommission(double pkgAgencyCommission)
	{
		this.pkgAgencyCommission = pkgAgencyCommission;
	}

	public double getPkgBasePrice()
	{
		return pkgBasePrice;
	}

	public void setPkgBasePrice(double pkgBasePrice)
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
	
	
	
}
