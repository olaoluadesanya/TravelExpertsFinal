package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the clases database table.
 * Olaoluwa Adesanya SAIT 2018
 * 
 * had to change the name form class to clas as class is a reserved java keyword
 */
@Entity
@Table(name="clases")
@NamedQuery(name="Clas.findAll", query="SELECT c FROM Clas c")
public class Clas implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String classId;

	private String classDesc;

	private String className;

	public Clas() {
	}

	public String getClassId() {
		return this.classId;
	}

	public void setClassId(String classId) {
		this.classId = classId;
	}

	public String getClassDesc() {
		return this.classDesc;
	}

	public void setClassDesc(String classDesc) {
		this.classDesc = classDesc;
	}

	public String getClassName() {
		return this.className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

}