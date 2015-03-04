package org.voidsink.kussslib.impl;

import java.util.Date;

import org.jsoup.nodes.Element;
import org.voidsink.kussslib.Curricula;

public class CurriculaImpl implements Curricula {

	public CurriculaImpl(Element row) {

	}

	boolean isInitialized() {
		return false;
	}

	@Override
	public boolean isStandard() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getCid() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getTitle() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isSteopDone() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isActive() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getUniversity() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Date getDtStart() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Date getDtEnd() {
		// TODO Auto-generated method stub
		return null;
	}

}
