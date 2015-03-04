package org.voidsink.kussslib.impl;

import java.util.Date;

import org.jsoup.nodes.Element;
import org.voidsink.kussslib.Exam;
import org.voidsink.kussslib.Term;

public class ExamImpl implements Exam {

	public ExamImpl(Element row, boolean b) {
		// TODO Auto-generated constructor stub
	}

	public boolean isInitialized() {
		// TODO Auto-generated method stub
		return false;
	}

	public void addAdditionalInfo(Element element) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getCourseId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Term getTerm() {
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

	@Override
	public String getLocation() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getTitle() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getCid() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isRegistered() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getMaxParticipants() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getParticipants() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Date getRegistrationDtStart() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Date getRegistrationDtEnd() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Date getUnRegistrationDt() {
		// TODO Auto-generated method stub
		return null;
	}

}
