package org.telscenter.sail.webapp.presentation.web.controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.sail.webapp.domain.User;
import net.sf.sail.webapp.presentation.web.controllers.ControllerUtil;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import org.telscenter.sail.webapp.domain.project.Project;
import org.telscenter.sail.webapp.domain.project.ProjectMetadata;
import org.telscenter.sail.webapp.presentation.util.json.JSONArray;
import org.telscenter.sail.webapp.presentation.util.json.JSONException;
import org.telscenter.sail.webapp.presentation.util.json.JSONObject;
import org.telscenter.sail.webapp.service.project.ProjectService;

public class ProjectMetadataController extends AbstractController {
	
	private ProjectService projectService;
	
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		//get the command
		String command = request.getParameter("command");
		
		//get the project id
		String projectId = request.getParameter("projectId");
		
		if(projectId != null) {
			//get the project
			Project project = projectService.getById(Long.parseLong(projectId));
			
			if(project != null) {
				//get the metadata
				ProjectMetadata metadata = project.getMetadata();
				
				if(command.equals("getProjectMetaData")) {
					if(metadata != null) {
						//metadata exists so we will get the metadata as a JSON string
						String metadataJSON = metadata.toJSONString();
						response.getWriter().write(metadataJSON);
					} else {
						//metadata does not exist so we will just return an empty JSON object string
						response.getWriter().write("{}");
					}
				} else if(command.equals("postMaxScore")) {
					//request is to post a max score
					handlePostMaxScore(request, response);
				}		
			}			
		}
		
		return null;
	}

	/**
	 * Handles the saving of max score POSTs
	 * @param request
	 * @param response
	 * @return
	 */
	private ModelAndView handlePostMaxScore(HttpServletRequest request, HttpServletResponse response) {
		try {
			//get the project id
			String projectIdStr = request.getParameter("projectId");
			
			//get the project
			Project project = projectService.getById(new Long(projectIdStr));
			
			//get the signed in user
			User user = ControllerUtil.getSignedInUser();
			
			//check if the user can author the project
			if(this.projectService.canAuthorProject(project, user)) {
				//the user has permission to author the project
				
				//get the nodeId
				String nodeId = request.getParameter("nodeId");
				
				//get the new max score value
				String maxScoreValue = request.getParameter("maxScoreValue");
				
				int maxScore = 0;
				
				//check if a max score value was provided
				if(maxScoreValue != null && !maxScoreValue.equals("")) {
					//parse the new max score value
					maxScore = Integer.parseInt(maxScoreValue);	
				}
				
				/*
				 * the string that we will use to return the new max score JSON object
				 * once we have successfully updated it on the server. this is so
				 * that the client can retrieve confirmation that the new max
				 * score has been saved and that it can then update its local copy.
				 */
				String maxScoreReturnJSON = "";
				
				if(project != null) {
					ProjectMetadata projectMetadata = project.getMetadata();
					
					if(projectMetadata != null) {
						String maxScoresString = projectMetadata.getMaxScores();
						JSONArray maxScoresJSONArray = null;
						
						if(maxScoresString == null || maxScoresString.equals("")) {
							maxScoresJSONArray = new JSONArray();
						} else {
							maxScoresJSONArray = new JSONArray(maxScoresString);
						}
						
						boolean maxScoreUpdated = false;
						
						for(int x=0; x<maxScoresJSONArray.length(); x++) {
							//get a max score entry
							JSONObject maxScoreObj = (JSONObject) maxScoresJSONArray.get(x);
							
							//get the node id
							String maxScoreObjNodeId = (String) maxScoreObj.get("nodeId");
							
							//check if the node id matches the one new one we need to save
							if(nodeId.equals(maxScoreObjNodeId)) {
								//it matches so we will update the score
								maxScoreObj.put("maxScoreValue", maxScore);
								
								/*
								 * generate the json string for the updated max score entry
								 * so we can send it back in the response
								 */
								maxScoreReturnJSON = maxScoreObj.toString();
								
								maxScoreUpdated = true;
							}
						}
						
						//check if we were able to find an existing entry to update it
						if(!maxScoreUpdated) {
							/*
							 * we did not find an existing entry to update so we will
							 * create a new entry
							 */
							JSONObject newMaxScore = new JSONObject();
							
							//set the values
							newMaxScore.put("nodeId", nodeId);
							
							//set the max score
							newMaxScore.put("maxScoreValue", maxScore);	
							
							/*
							 * generate the json string for the updated max score entry
							 * so we can send it back in the response
							 */
							maxScoreReturnJSON = newMaxScore.toString();
							
							//put the new entry back into the maxScores JSON object
							maxScoresJSONArray.put(newMaxScore);
						}

						//save the run extras back
						//runService.setExtras(run, jsonExtras.toString());
						projectMetadata.setMaxScores(maxScoresJSONArray.toString());
						projectService.updateProject(project, user);
						
						//send the new max score entry back to the client
						response.getWriter().print(maxScoreReturnJSON);
					}
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	public ProjectService getProjectService() {
		return projectService;
	}

	public void setProjectService(ProjectService projectService) {
		this.projectService = projectService;
	}
}
