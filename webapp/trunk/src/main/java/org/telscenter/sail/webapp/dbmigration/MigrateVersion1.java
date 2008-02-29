package org.telscenter.sail.webapp.dbmigration;

import java.sql.Connection;
import java.sql.SQLException;

import net.sf.sail.webapp.dbmigration.AbstractMigrationClass;

/**
 * This migration is to add three new tables used for the PremadeComments functionality
 * 
 * @author Hiroki Terashima
 * @author Rokham Sadeghnezhadfard
 * @author David Leung
 * @author Geoffrey Kwan
 *
 * @version $Id:$
 */
public class MigrateVersion1 extends AbstractMigrationClass {
	public MigrateVersion1() {
		this.statements = new String[] {
				"create table premadecommentlists (" +
				"id bigint not null auto_increment," +
				"label varchar(255) not null," +
				"owner longtext," +
				"run longtext," +
				"primary key (id)" +
				") type=InnoDB;",
				"create table premadecomments (" +
				"id bigint not null auto_increment," +
				"comment varchar(255) not null," +
				"label varchar(255) not null," +
				"owner longtext," +
				"run longtext," +
				"primary key (id)" +
				") type=InnoDB;",
			    "create table premadecomments_related_to_premadecommentlists (" +
			    "premadecommentslist_fk bigint not null," +
			    "premadecomments_fk bigint not null," +
			    "primary key (premadecommentslist_fk, premadecomments_fk)" +
			    ") type=InnoDB;",
			    "alter table premadecomments_related_to_premadecommentlists " +
			    "add index FK6958FC11C8153CF5 (premadecomments_fk), " +
			    "add constraint FK6958FC11C8153CF5 " +
			    "foreign key (premadecomments_fk) " +
			    "references premadecomments (id);",
			    "alter table premadecomments_related_to_premadecommentlists " +
			    "add index FK6958FC112FC6E4D5 (premadecommentslist_fk), " +
			    "add constraint FK6958FC112FC6E4D5 " +
			    "foreign key (premadecommentslist_fk) " +
			    "references premadecommentlists (id);"
			};
	}

    public int doMigration(Connection conn) throws SQLException {
    	super.doMigration(conn);
    	
    	// call the class that we are going to run
    	
    	return 0;
	}

}