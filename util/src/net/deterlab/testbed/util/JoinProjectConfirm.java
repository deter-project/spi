package net.deterlab.testbed.util;

import java.util.Arrays;

import net.deterlab.testbed.api.DeterFault;

import net.deterlab.testbed.client.ProjectsDeterFault;
import net.deterlab.testbed.client.ProjectsStub;

import org.apache.axis2.AxisFault;

/**
 * Add the users to the project with the given permissions.  Print the results.
 * @author DETER Team
 * @version 1.0
 */
public class JoinProjectConfirm extends Utility {

    static public void usage() {
	fatal("Usage: JoinProjectConfirm challenge perms");
    }

    /**
     * Collect the users, call add and display results.
     * @param args the projectid permissions and users
     */
    static public void main(String[] args) {
	try {

	    // Set trusted certificates.
	    loadTrust();
	    loadID();


	    if ( args.length < 2) 
		usage();

	    String[] tags = args[1].split(",");

	    // Pass the parame to addUsersNoConfirm directly and print results
	    ProjectsStub stub = new ProjectsStub(getServiceUrl("Projects"));
	    ProjectsStub.JoinProjectConfirm req = 
		new ProjectsStub.JoinProjectConfirm();

	    req.setChallengeId(Long.parseLong(args[0]));
	    req.setPerms(tags);

	    stub.joinProjectConfirm(req);
	    System.out.println("User added");

	} catch (ProjectsDeterFault e) {
	    DeterFault df = getDeterFault(e);
	    fatal(df.getErrorMessage() + ": " + df.getDetailMessage());
	} catch (AxisFault e) {
	    handleAxisFault(e);
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }
}
