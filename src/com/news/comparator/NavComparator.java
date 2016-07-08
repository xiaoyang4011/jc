package com.news.comparator;

import java.util.Comparator;
import com.news.model.sys.Tnav;

/**
 * 菜单排序
 * 
 * @author lxy
 * 
 */
public class NavComparator implements Comparator<Tnav> {

	public int compare(Tnav o1, Tnav o2) {
		int i1 = o1.getCseq() != null ? o1.getCseq().intValue() : -1;
		int i2 = o2.getCseq() != null ? o2.getCseq().intValue() : -1;
		return i1 - i2;
	}

}
