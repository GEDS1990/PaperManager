package org.service.imp;

import java.util.ArrayList;

import org.dao.IZhuanJiaDao;
import org.dao.imp.ZhuanJiaDao;
import org.model.ZJ;
import org.service.IZhuanJiaService;

public class ZhuanJiaService implements IZhuanJiaService {

	IZhuanJiaDao dao=new ZhuanJiaDao();
	@Override
	public Object[] getInfo(int i) {
		Object a[];
		ArrayList< ZJ>list=new ArrayList<ZJ>();
		String sql="select * from zhuanjia where Z_no="+i;
		list=dao.findSql(sql);
		a=new Object[3];
		for(ZJ zhuanjia:list){
			a[0]=zhuanjia.getZjName();
			a[1]=zhuanjia.getZjTel();
			a[2]=zhuanjia.getZjPassword();
		}
		return a;
	}
	
	@Override
	public boolean updata_password(ZJ zhuanjia) {
		return dao.updata_password(zhuanjia);
	}
}
