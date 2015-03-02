package org.voidsink.kussslib.impl;

import java.io.IOException;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.HttpCookie;
import java.net.SocketTimeoutException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import net.fortuna.ical4j.data.CalendarBuilder;
import net.fortuna.ical4j.model.Calendar;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.voidsink.kussslib.Assessment;
import org.voidsink.kussslib.Course;
import org.voidsink.kussslib.Curricula;
import org.voidsink.kussslib.EventType;
import org.voidsink.kussslib.Exam;
import org.voidsink.kussslib.KusssHandler;
import org.voidsink.kussslib.Term;

public class KusssHandlerImpl implements KusssHandler {

    private CookieManager mCookies;
    private String sessionId = "";
    private IExceptionListener exceptionListener = null;
    
    private static final String URL_MY_LVAS = "https://www.kusss.jku.at/kusss/assignment-results.action";
    private static final String URL_GET_TERMS = "https://www.kusss.jku.at/kusss/listmystudentlvas.action";
    private static final String URL_GET_ICAL = "https://www.kusss.jku.at/kusss/ical-multi-sz.action";
    private static final String URL_MY_GRADES = "https://www.kusss.jku.at/kusss/gradeinfo.action";
    private static final String URL_START_PAGE = "https://www.kusss.jku.at/kusss/studentwelcome.action";
    private static final String URL_LOGOUT = "https://www.kusss.jku.at/kusss/logout.action";
    private static final String URL_LOGIN = "https://www.kusss.jku.at/kusss/login.action";
    private static final String URL_GET_NEW_EXAMS = "https://www.kusss.jku.at/kusss/szsearchexam.action";
    private static final String URL_GET_EXAMS = "https://www.kusss.jku.at/kusss/szexaminationlist.action";
    private static final String URL_SELECT_TERM = "https://www.kusss.jku.at/kusss/select-term.action";
    private static final String URL_MY_STUDIES = "https://www.kusss.jku.at/kusss/studentsettings.action";
    
    private static final String SELECT_MY_LVAS = "body.intra > table > tbody > tr > td > table > tbody > tr > td.contentcell > div.contentcell > table > tbody > tr:has(td)";
    private static final String SELECT_MY_GRADES = "body.intra > table > tbody > tr > td > table > tbody > tr > td.contentcell > div.contentcell > *";
    private static final String SELECT_NOT_LOGGED_IN = "body > table > tbody > tr > td > table > tbody > tr > td.contentcell > div.contentcell > h4";
    // private static final String SELECT_ACTUAL_EXAMS =
    // "body.intra > table > tbody > tr > td > table > tbody > tr > td.contentcell > div.contentcell > div.tabcontainer > div.tabcontent > table > tbody > tr > td > form > table > tbody > tr:has(td)";
    private static final String SELECT_NEW_EXAMS = "body.intra > table > tbody > tr > td > table > tbody > tr > td.contentcell > div.contentcell > div.tabcontainer > div.tabcontent > div.sidetable > form > table > tbody > tr:has(td)";
    private static final String SELECT_EXAMS = "body.intra > table > tbody > tr > td > table > tbody > tr > td.contentcell > div.contentcell > div.tabcontainer > div.tabcontent > table > tbody > tr > td > form > table > tbody > tr:has(td)";
    private static final String SELECT_MY_STUDIES = "body.intra > table > tbody > tr > td > table > tbody > tr > td.contentcell > div.contentcell > div.tabcontainer > div.tabcontent > form > table > tbody > tr[class]:has(td)";

    private static final int TIMEOUT_LOGIN = 15 * 1000; // 15s
    private static final int TIMEOUT_SEARCH_EXAM_BY_LVA = 10 * 1000; //10s
   
    private void onHandleException(Exception e, boolean fatal) {
    	if (exceptionListener != null) {
    		exceptionListener.onExceptionOccured(e, fatal);
    	}
    }
    
	@Override
	public synchronized boolean login(String user, String password) {
		if (user == null || password == null) {
            return false;
        }
        try {
            if ((user.length() > 0) && (user.charAt(0) != 'k')) {
                user = "k" + user;
            }

            Document doc = Jsoup.connect(URL_LOGIN).timeout(TIMEOUT_LOGIN).data("j_username", user)
                    .data("j_password", password).post();

            //TODO: check document for successful login message

            sessionId = getSessionIDFromCookie();

            if (isLoggedIn()) {
            	return true;
            }
        	sessionId = null;
            return false;
        } catch (SocketTimeoutException e) {
            // bad connection, timeout
        	sessionId = null;
            return false;
        } catch (Exception e) {
        	onHandleException(e, true);
        	sessionId = null;
            return false;
        }
	}
	
	private String getSessionIDFromCookie() {
        try {
            List<HttpCookie> cookies = mCookies.getCookieStore().get(
                    new URI("https://www.kusss.jku.at/"));

            for (HttpCookie cookie : cookies) {
                if (cookie.getName().equals("JSESSIONID")) {
                    return cookie.getValue();
                }
            }
            return null;
        } catch (URISyntaxException e) {
            return null;
        }
    }
	

	@Override
	public boolean logout() {
        try {
            Connection.Response r = Jsoup.connect(URL_LOGOUT).method(Connection.Method.GET).execute();

            if (r == null) {
                return false;
            }

            return !isLoggedIn();
        } catch (Exception e) {
        	onHandleException(e, true);
            return true;
        }
	}

	@Override
	public boolean isLoggedIn() {
        try {
            String actSessionId = getSessionIDFromCookie();
            if (actSessionId == null || sessionId == null
                    || !sessionId.equals(actSessionId)) {
                return false;
            }

            Document doc = Jsoup.connect(URL_START_PAGE).timeout(TIMEOUT_LOGIN).get();

            Elements notLoggedIn = doc.select(SELECT_NOT_LOGGED_IN);
            if (notLoggedIn.size() > 0) {
                return false;
            }
        } catch (SocketTimeoutException e) {
            // bad connection, timeout
            return false;
        } catch (IOException e) {
        	onHandleException(e, true);
            return false;
        }
        return true;
	}

	@Override
	public Calendar getEvents(EventType eventType,
			CalendarBuilder calendarBuilder) {
		return null;
	}

	@Override
	public Calendar getEvents(EventType eventType) {
		CalendarBuilder calendarBuilder = new CalendarBuilder();
		return getEvents(eventType, calendarBuilder);
	}

	@Override
	public List<Course> getLvas(List<Term> terms) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Assessment> getAssessments() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Exam> getExams() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Exam> getExamsByCourse(List<Course> lvas) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Curricula> getCurricula() {
		// TODO Auto-generated method stub
		return null;
	}

	private KusssHandlerImpl() {
        this.mCookies = new CookieManager(null, CookiePolicy.ACCEPT_ALL);
        CookieHandler.setDefault(mCookies);
	}

	@Override
	public void setExceptionListener(KusssHandler.IExceptionListener listener) {
		this.exceptionListener = listener;
	}	
}
