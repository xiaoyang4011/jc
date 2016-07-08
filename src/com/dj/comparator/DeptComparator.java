package com.dj.comparator;

import java.util.Comparator;

import com.dj.model.sys.Tdept;

/**
 * 部门排序
 * 
 * @author lxy
 * 
 */
public class DeptComparator implements Comparator<Tdept> {

	public int compare(Tdept o1, Tdept o2) {
		int i1 = o1.getCseq() != null ? o1.getCseq().intValue() : -1;
		int i2 = o2.getCseq() != null ? o2.getCseq().intValue() : -1;
		return i1 - i2;
	}

}
