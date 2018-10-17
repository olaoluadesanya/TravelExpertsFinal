/*
 * Author: Sunghyun Lee
 * Created: 2018-10-01
 */

package model;

import java.util.Date;
import java.util.List;
import java.math.BigDecimal;
import java.time.LocalDate;



public class Packag
{
	private int packageId;

	private double pkgAgencyCommission;

	private double pkgBasePrice;

	private String pkgDesc;

	private LocalDate pkgEndDate;

	private String pkgName;

	private LocalDate pkgStartDate;
	
	private String pkgImageFile;

	public Packag(int packageId, double pkgAgencyCommission, double pkgBasePrice, String pkgDesc, LocalDate pkgEndDate,
			String pkgName, LocalDate pkgStartDate, String pkgImageFile)
	{
		super();
		this.packageId = packageId;
		this.pkgAgencyCommission = pkgAgencyCommission;
		this.pkgBasePrice = pkgBasePrice;
		this.pkgDesc = pkgDesc;
		this.pkgEndDate = pkgEndDate;
		this.pkgName = pkgName;
		this.pkgStartDate = pkgStartDate;
		this.pkgImageFile = pkgImageFile;
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

	public LocalDate getPkgEndDate()
	{
		return pkgEndDate;
	}

	public void setPkgEndDate(LocalDate pkgEndDate)
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

	public LocalDate getPkgStartDate()
	{
		return pkgStartDate;
	}

	public void setPkgStartDate(LocalDate pkgStartDate)
	{
		this.pkgStartDate = pkgStartDate;
	}

	public String getPkgImageFile()
	{
		return pkgImageFile;
	}

	public void setPkgImageFile(String pkgImageFile)
	{
		this.pkgImageFile = pkgImageFile;
	}

	@Override
	public String toString()
	{
		return  pkgName;
	}
	
	
	
}
