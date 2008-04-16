package net.sf.sail.cms.curnit.impl;

import javax.jcr.Node;
import javax.jcr.Session;
import org.jcrom.Jcrom;
import org.jcrom.dao.AbstractJcrDAO;

public class CurnitDao extends AbstractJcrDAO<CurnitOtmlImpl> {
        
        public CurnitDao( Session session, Jcrom jcrom ) {
                super(CurnitOtmlImpl.class, session, jcrom);
        }
}