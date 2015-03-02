package org.voidsink.kussslib;
import java.util.Date;

public interface Curricula {

    public boolean isStandard();
    public int getSkz();
    public String getTitle();
    public boolean isSteopDone();
    public boolean isActive();
    public String getUniversity();
    public Date getDtStart();
    public Date getDtEnd( );
}
