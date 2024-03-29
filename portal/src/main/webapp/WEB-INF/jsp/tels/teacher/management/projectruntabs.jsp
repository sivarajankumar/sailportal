<link href="<spring:theme code="teacherrunstylesheet"/>" media="screen" rel="stylesheet"  type="text/css" />
<link href="<spring:theme code="jquerydatatables.css"/>" media="screen" rel="stylesheet"  type="text/css" />
<link href="<spring:theme code="facetedfilter.css"/>" media="screen" rel="stylesheet"  type="text/css" />
<script type="text/javascript" src="<spring:theme code="jquerydatatables.js"/>"></script>
<script type="text/javascript" src="<spring:theme code="facetedfilter.js"/>"></script>

<script type="text/javascript">
	$(document).ready(function() {
		var oTable = $('.runTable').dataTable({
			"sPaginationType": "full_numbers",
			"iDisplayLength": 5,
			"aLengthMenu": [[5, 10, 25, -1], [5, 10, 25, "All"]],
			"bSort": false,
			"oLanguage": {
				"sInfo": "<spring:message code="teacher.run.myprojectruns.datatables.1"/> _START_-_END_ <spring:message code="teacher.run.myprojectruns.datatables.2"/> _TOTAL_",
				"sInfoEmpty": "<spring:message code="teacher.run.myprojectruns.datatables.3"/>",
				"sInfoFiltered": "<spring:message code="teacher.run.myprojectruns.datatables.4"/>", // (from _MAX_ total)
				"sLengthMenu": "<spring:message code="teacher.run.myprojectruns.datatables.5"/> _MENU_ <spring:message code="teacher.run.myprojectruns.datatables.6"/>",
				"sProcessing": "<spring:message code="teacher.run.myprojectruns.datatables.7"/>",
				"sZeroRecords": "<spring:message code="teacher.run.myprojectruns.datatables.8"/>",
				"sInfoPostFix":  "<spring:message code="teacher.run.myprojectruns.datatables.9"/>",
				"sSearch": "<spring:message code="teacher.run.myprojectruns.datatables.10"/>",
				"sUrl": "<spring:message code="teacher.run.myprojectruns.datatables.11"/>",
				"oPaginate": {
					"sFirst":    "<spring:message code="teacher.run.myprojectruns.datatables.12"/>",
					"sPrevious": "<spring:message code="teacher.run.myprojectruns.datatables.13"/>",
					"sNext":     "<spring:message code="teacher.run.myprojectruns.datatables.14"/>",
					"sLast":     "<spring:message code="teacher.run.myprojectruns.datatables.15"/>"
				}
			},
			"fnDrawCallback": function( oSettings ){
				// automatically scroll to top on page change
				var targetOffset = $('.runTable').offset().top - 10;
				if ($(window).scrollTop() > targetOffset){
					$('html,body').scrollTop(targetOffset);
				}
			},
			"sDom":'<"top"lip<"clear">>rt<"bottom"ip><"clear">'
		});
		
		// define sort options
		var sortParams = {
			"items": [
				{"label": "<spring:message code="teacher.run.myprojectruns.sort.1a"/>", "column": 3, "direction": "desc" },
				{"label": "<spring:message code="teacher.run.myprojectruns.sort.1b"/>", "column": 3, "direction": "asc" },
				{"label": "<spring:message code="teacher.run.myprojectruns.sort.1c"/>", "column": 0, "direction": "asc" },
				{"label": "<spring:message code="teacher.run.myprojectruns.sort.1d"/>", "column": 0, "direction": "desc" }
			]
		}
		
		var i;
		for(i=0; i<oTable.length; i++){
			oTable.dataTableExt.iApiIndex = i;
			var wrapper = oTable.fnSettings().nTableWrapper;
			var table = oTable.fnSettings();
			var id = $(table.oInstance).attr('id');
			
			// Define FacetedFilter options
			var facets = new FacetedFilter( table, {
				"aSearchOpts": [
					{
						"identifier": "<spring:message code="teacher.run.myprojectruns.search.1a"/>", "label": "<spring:message code="teacher.run.myprojectruns.search.1b"/> ", "column": 0, "maxlength": 50
					},
					{
						"identifier": "<spring:message code="teacher.run.myprojectruns.search.2a"/>", "label": "<spring:message code="teacher.run.myprojectruns.search.2b"/> ", "column": 7, "maxlength": 30,
						"regexreplace": {"match": "/,\s*/gi", "replacement": " "},
						"instructions": "<spring:message code="teacher.run.myprojectruns.search.2e"/>"
					}
				 ],
				"aFilterOpts": [
					{
						"identifier": "<spring:message code="teacher.run.myprojectruns.58D"/>", "label": "<spring:message code="teacher.run.myprojectruns.filter.1a"/>", "column": 6,
						"options": [
							{"query": "owned", "display": "<spring:message code="teacher.run.myprojectruns.filter.1b"/>"},
							{"query": "shared", "display": "<spring:message code="teacher.run.myprojectruns.filter.1c"/>"}
						]
					}
					/*{
						"identifier": "<spring:message code="teacher.run.myprojectruns.58C"/>", "label": "<spring:message code="teacher.run.myprojectruns.filter.2a"/>", "column": 5,
						"options": [
							{"query": "custom", "display": "<spring:message code="teacher.run.myprojectruns.filter.2b"/>"},
							{"query": "library", "display": "<spring:message code="teacher.run.myprojectruns.filter.2c"/>"}
						]
					}*/
				],
				"bScroll": false
			});
			
			// add sort logic
			setSort(i,sortParams,wrapper);
		}
		
		// setup tabs
		$( "#runTabs" ).tabs({ selected: 0 });
		
		// setup sorting
		function setSort(index,sortParams,wrapper) {
			if(sortParams.items.length){
				// insert sort options into DOM
				var sortHtml = '<div class="dataTables_sort">Sort by <select id="' + 'sort_' + index + '"  size="1">';
				$.each(sortParams.items,function(){
					sortHtml += '<option>' + this.label + '</option>';
				});
				sortHtml +=	'</select></div>';
				$(wrapper).children('.top').prepend(sortHtml);
				
				$('#sort_' + index).change(function(){
					$.fn.dataTableExt.iApiIndex = index;
					var i = $('option:selected', '#sort_' + index).index();
					oTable.fnSort( [ [sortParams.items[i].column,sortParams.items[i].direction] ] );
				});
			}
		};
		
		// Make top header scroll with page
		/*var $stickyEl = $('.dataTables_wrapper .top'),
			elTop = $stickyEl.offset().top,
			width = $stickyEl.width();
		$(window).scroll(function() {
	        var windowTop = $(window).scrollTop();
	        if (windowTop > elTop) {
	            $stickyEl.addClass('sticky');
	        	$stickyEl.css('width',width);
	        } else {
	            $stickyEl.removeClass('sticky');
	        	$stickyEl.css('width','auto');
	        }
	    });*/
	});
	
	function popup(URL, title) {
		window.open(URL, title, 'toolbar=0,scrollbars=0,location=0,statusbar=0,menubar=0,resizable=1,width=640,height=480,left = 320,top = 240');
	};
	
	// setup grading dialogs
	$('.grading, .researchTools, .classroomMonitor').live('click',function(){
		var settings = $(this).attr('id');
		var title = $(this).attr('title');
		var path = "/webapp/teacher/grading/gradework.html?" + settings;
		var div = $('#gradingDialog').html('<iframe id="gradingIfrm" width="100%" height="100%" style="overflow-y:hidden; min-width:1000px;"></iframe>');
		$('body').css('overflow-y','hidden');
		div.dialog({
			modal: true,
			width: $(window).width() - 32,
			height: $(window).height() - 32,
			position: 'center',
			title: title,
			close: function (e, ui) { $(this).html(''); $('body').css('overflow-y','auto'); },
			buttons: {
				Exit: function(){
					$(this).dialog('close');
				}
			}
		});
		$("#gradingDialog > #gradingIfrm").attr('src',path);
	});
	
	// setup share project run dialog
	$('.shareRun').live('click',function(){
		var title = $(this).attr('title');
		var runId = $(this).attr('id').replace('shareRun_','');
		var path = "/webapp/teacher/run/shareprojectrun.html?runId=" + runId;
		var div = $('#shareDialog').html('<iframe id="shareIfrm" width="100%" height="100%"></iframe>');
		div.dialog({
			modal: true,
			width: '650',
			height: $(window).height() - 100,
			title: title,
			position: 'center',
			close: function(){ $(this).html(''); },
			buttons: {
				Close: function(){$(this).dialog('close');}
			}
		});
		$("#shareDialog > #shareIfrm").attr('src',path);
	});
	
	// setup edit run settings dialog
	$('.editRun').live('click',function(){
		var title = $(this).attr('title');
		var runId = $(this).attr('id').replace('editRun_','');
		var path = "/webapp/teacher/run/editrun.html?runId=" + runId;
		var div = $('#editRunDialog').html('<iframe id="editIfrm" width="100%" height="100%"></iframe>');
		div.dialog({
			modal: true,
			width: '600',
			height: '400',
			title: title,
			position: 'center',
			close: function(){ $(this).html(''); },
			buttons: {
				Close: function(){
					if(document.getElementById('editIfrm').contentWindow['runUpdated']){
						window.location.reload();
					}
					$(this).dialog('close');
				}
			}
		});
		$("#editRunDialog > #editIfrm").attr('src',path);
	});
	
	// setup edit manage announcements dialog
	$('.editAnnouncements').live('click',function(){
		var title = $(this).attr('title');
		var runId = $(this).attr('id').replace('editAnnouncements_','');
		var path = "/webapp/teacher/run/announcement/manageannouncement.html?runId=" + runId;
		var div = $('#editAnnouncementsDialog').html('<iframe id="announceIfrm" width="100%" height="100%"></iframe>');
		div.dialog({
			modal: true,
			width: '600',
			height: '400',
			title: title,
			position: 'center',
			close: function(){ $(this).html(''); },
			buttons: {
				Close: function(){
					$(this).dialog('close');
				}
			}
		});
		$("#editAnnouncementsDialog > #announceIfrm").attr('src',path);
	});
	
	// setup manage students dialog
	$('.manageStudents').live('click',function(){
		var title = $(this).attr('title');
		var params = $(this).attr('id').replace('manageStudents_','');
		var path = "/webapp/teacher/management/viewmystudents.html?" + params;
		var div = $('#manageStudentsDialog').html('<iframe id="manageStudentsIfrm" width="100%" height="100%"></iframe>');
		$('body').css('overflow-y','hidden');
		div.dialog({
			modal: true,
			width: $(window).width() - 32,
			height: $(window).height() - 32,
			title: title,
			position: 'center',
			close: function(){ $(this).html(''); $('body').css('overflow-y','auto'); },
			buttons: {
				Close: function(){
					$(this).dialog('close');
				}
			}
		});
		$("#manageStudentsDialog > #manageStudentsIfrm").attr('src',path);
	});
	
	// Set up view project details click action for each project id link
	$('a.projectDetail, a.projectInfo').live('click',function(){
		var title = $(this).attr('title');
		if($(this).hasClass('projectDetail')){
			var projectId = $(this).attr('id').replace('projectDetail_','');
		} else if($(this).hasClass('projectInfo')){
			var projectId = $(this).attr('id').replace('projectInfo_','');
		}
		var path = "/webapp/teacher/projects/projectinfo.html?projectId=" + projectId;
		var div = $('#projectDetailDialog').html('<iframe id="projectIfrm" width="100%" height="100%"></iframe>');
		div.dialog({
			modal: true,
			width: '800',
			height: '400',
			title: title,
			position: 'center',
			close: function(){ $(this).html(''); },
			buttons: {
				Close: function(){
					$(this).dialog('close');
				}
			}
		});
		$("#projectDetailDialog > #projectIfrm").attr('src',path);
	});
	
 </script>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div id="runTabs" class="panelTabs">
    <ul>
    	<li><a href="#currentRuns"><spring:message code="teacher.run.myprojectruns.1A"/>  (${fn:length(current_run_list)})</a></li>
    	<li><a href="#archivedRuns"><spring:message code="teacher.run.myprojectruns.1B"/>  (${fn:length(ended_run_list)})</a></li>
    </ul>       
    <!-- <div class="yui-content" id="currentrunWrapper"> -->
    <div id="currentRuns">
		<p class="info"><spring:message code="teacher.run.myprojectruns.2" /></p>
		
		<c:choose>
			<c:when test="${fn:length(current_run_list) > 0}">
				<div class="runBox">
					
					<table id="currentRunTable" class="runTable" border="1" cellpadding="0" cellspacing="0">
						<thead>
						    <tr>
						       <th style="width:250px;"class="tableHeaderMain"><spring:message code="teacher.run.myprojectruns.3"/></th>
						       <th style="width:140px;" class="tableHeaderMain"><spring:message code="teacher.run.myprojectruns.4" /></th>      
						       <th style="width:290px;" class="tableHeaderMain"><spring:message code="teacher.run.myprojectruns.5" /></th>
						       <th style="display:none;" class="tableHeaderMain"><spring:message code="teacher.run.myprojectruns.58A" /></th>
						       <th style="display:none;" class="tableHeaderMain"><spring:message code="teacher.run.myprojectruns.58B" /></th>
						       <th style="display:none;" class="tableHeaderMain"><spring:message code="teacher.run.myprojectruns.58C" /></th>
						       <th style="display:none;" class="tableHeaderMain"><spring:message code="teacher.run.myprojectruns.58D" /></th>
						       <th style="display:none;" class="tableHeaderMain"><spring:message code="teacher.run.myprojectruns.58E" /></th>
						    </tr>
						</thead>
						<tbody>
						  <c:if test="${fn:length(current_run_list) > 0}">
							  <c:forEach var="run" items="${current_run_list}">
							  
							  <tr id="runTitleRow_${run.id}" class="runRow">
							    <td class="titleCell">
							    	<div class="runTitle">${run.name}</div>
							    		<c:set var="ownership" value="owned" />
										<c:forEach var="sharedowner" items="${run.sharedowners}">
								    	    <c:if test="${sharedowner == user}">
								    	    	<c:set var="ownership" value="shared" />
								    	    	<div class="sharedIcon">
									    	    	<img src="/webapp/themes/tels/default/images/shared.png" alt="shared project" /> <spring:message code="teacher.run.myprojectruns.6"/>
									    	    	<c:forEach var="owner" items="${run.owners}">
									    	    		${owner.userDetails.firstname} ${owner.userDetails.lastname}
									    	    	</c:forEach>
								    	    	</div>
								    	    </c:if>
								    	</c:forEach>
							     
									<table class="runTitleTable">
							      			<tr>
												<th><spring:message code="teacher.run.myprojectruns.8" /></th>
												<td class="accesscode">${run.runcode}</td>
											</tr>
											
							      			<tr>
							      				<th><spring:message code="teacher.run.myprojectruns.11" /></th>
							      				<td>${run.id}</td>
							      			</tr>
							      			<tr>
							      				<th><spring:message code="teacher.run.myprojectruns.13"/></th>
							      				<td><fmt:formatDate value="${run.starttime}" type="date" dateStyle="medium" /></td>
							      			</tr>
							      			<!-- <tr>
							      				<th><spring:message code="teacher.run.myprojectruns.12"/></th> TODO: decide whether to include or not -->
							      				<c:set var="source" value="custom" />
							      				<c:choose>
							      				<c:when test="${run.project.familytag == 'TELS'}"> <!-- TODO: modify this to include ALL library projects (not just TELS) -->
								      				<c:set var="source" value="library" />
								      				<!-- <td><spring:message code="teacher.run.myprojectruns.43"/></td> -->
							      				</c:when>
							      				<c:otherwise>
								      				<!-- <td><spring:message code="teacher.run.myprojectruns.44"/></td> -->
							      				</c:otherwise>
							      				</c:choose>
							      			<!-- </tr>  -->
											<tr>
							      				<th><spring:message code="teacher.run.myprojectruns.11A"/></th>
							      				<td><a id="projectDetail_${run.project.id}" class="projectDetail" title="Project Details">${run.project.id}</a></td>
							      			</tr>
							      			<tr>
							      				<c:if test="${run.project.parentProjectId != null}">
							      				<th><spring:message code="teacher.run.myprojectruns.40"/></th>
												<td><a id="projectDetail_${run.project.parentProjectId}" class="projectDetail" title="Project Details">${run.project.parentProjectId}</a></td>
												</c:if>
							      			</tr>
							      			<tr>
							      				<td colspan="2" style="padding-top:.5em;">
							      				<a id="editRun_${run.id}" class="editRun" title="Edit Run Settings: ${run.name} (Run ID ${run.id})"><spring:message code="teacher.run.myprojectruns.48"/></a>
							      				</td>
							      			</tr>
									</table>
							      	
								</td>
															
							    <td style="vertical-align:top; padding:0;" >
							    	<table class="currentRunInfoTable" border="0" cellpadding="0" cellspacing="0">
							          <tr>
							            <th class="tableInnerHeader"><spring:message code="teacher.run.myprojectruns.29"/></th>
							            <th style="display:none;" class="tableInnerHeader"><spring:message code="teacher.run.myprojectruns.8"/></th>
							            <th class="tableInnerHeader"><spring:message code="teacher.run.myprojectruns.9"/></th>
							          </tr>
							          <c:forEach var="period" items="${run.periods}">
							            <tr>
							              <td style="width:20%;" class="tableInnerData">${period.name}</td>
							              <td style="display:none;"  style="width:45%;" class="tableInnerData">${run.runcode}</td>
							              <td style="width:35%;" class="tableInnerDataRight">
							              	<a class="manageStudents" title="Manage Students: ${run.name} (Run ID ${run.id})" id="runId=${run.id}&periodName=${period.name}">${fn:length(period.members)}&nbsp;<spring:message code="teacher.run.myprojectruns.10"/></a>
							              </td>
							            </tr>
							          </c:forEach>
							        </table>
							        
							    </td> 
							    <td style="vertical-align: top; padding: 0.25em 0;">
								    <c:set var="isExternalProject" value="0"/>
								    
								        <c:forEach var="external_run" items="${externalprojectruns}">
								           <c:if test="${run.id == external_run.id}">
								                   <c:set var="isExternalProject" value="1"/>
								           </c:if>
								        </c:forEach>
								           <c:choose>
								               <c:when test="${isExternalProject == 1}">
								               	  <ul class="actionList">
								                  	<li><spring:message code="teacher.run.myprojectruns.45"/> <c:forEach var="periodInRun" items="${run.periods}"><a href="report.html?runId=${run.id}&groupId=${periodInRun.id}">${periodInRun.name}</a>&nbsp;</c:forEach></li>
								               	  </ul>
								               </c:when>
								               <c:otherwise>
											    <ul class="actionList">
											        <li>
											        	<spring:message code="teacher.run.myprojectruns.46"/>&nbsp;<a href="/webapp/previewproject.html?projectId=${run.project.id}" target="_blank"><spring:message code="teacher.run.myprojectruns.46A"/></a>
										    			|&nbsp;<a id="projectInfo_${run.project.id}" class="projectInfo" title="Project Details"><spring:message code="teacher.run.myprojectruns.46B"/></a>
											        	<sec:accesscontrollist domainObject="${run.project}" hasPermission="16">
											        		|&nbsp;<a onclick="if(confirm('<spring:message code="teacher.run.myprojectruns.47"/>')){window.top.location='/webapp/author/authorproject.html?projectId=${run.project.id}&versionId=${run.versionId}';} return true;"><spring:message code="teacher.run.myprojectruns.46C"/></a>
											        	</sec:accesscontrollist>
											        </li>
											    </ul>
											    <ul class="actionList">
													<li><spring:message code="teacher.run.myprojectruns.16"/>: <a class="grading" title="Grading & Feedback: ${run.name} (Run ID ${run.id})" id="runId=${run.id}&gradingType=step&getRevisions=false&minified=true"><spring:message code="teacher.run.myprojectruns.42"/></a>&nbsp;|&nbsp;<a class="grading" title="Grading & Feedback: ${run.name} (Run ID: ${run.id})"  id="runId=${run.id}&gradingType=step&getRevisions=true&minified=true"><spring:message code="teacher.run.myprojectruns.41"/></a></li>
							  	                    <li><spring:message code="teacher.run.myprojectruns.17"/>: <a class="grading" title="Grading & Feedback: ${run.name} (Run ID ${run.id})" id="runId=${run.id}&gradingType=team&getRevisions=false&minified=true"><spring:message code="teacher.run.myprojectruns.42"/></a>&nbsp;|&nbsp;<a class="grading" title="Grading & Feedback: ${run.name} (Run ID: ${run.id})" id="runId=${run.id}&gradingType=team&getRevisions=true&minified=true"><spring:message code="teacher.run.myprojectruns.41"/></a></li>
								                    <c:if test="${isXMPPEnabled && run.XMPPEnabled}">
		                    							<li><a class="classroomMonitor" title="Classroom Monitor: ${run.name} (Run ID ${run.id})" id="runId=${run.id}&gradingType=monitor">Classroom Monitor</a></li>
		                    						</c:if>
		                    						<li><a class="researchTools" title="Researcher Tools: ${run.name} (Run ID ${run.id})" id="runId=${run.id}&gradingType=export">Researcher Tools</a></li>
								               </ul>
								               </c:otherwise>
								           </c:choose>
									
									<ul class="actionList actionList2">
				
										<sec:accesscontrollist domainObject="${run}" hasPermission="16">
				   					      <li><a id="shareRun_${run.id}" class="shareRun" title="Sharing Permissions: ${run.name} (Run ID ${run.id})"><spring:message code="teacher.run.myprojectruns.18"/></a></li> 
				 	                    	</sec:accesscontrollist>
								    	
								    	<c:set var="isExternalProject" value="0"/>
								    	<sec:accesscontrollist domainObject="${run}" hasPermission="16">
								      		<!-- <li><a id="editAnnouncements_${run.id}" class="editAnnouncements" title="Manage Announcements: ${run.name} (Run ID ${run.id})" ><spring:message code="teacher.run.myprojectruns.50"/></a></li> -->
								        </sec:accesscontrollist>			    	
								    	<!-- 
								    	<li><a href="../run/brainstorm/createbrainstorm.html?runId=${run.id}" target="_top">Create Q&A Discussion</a></li>
								    	<c:if test="${not empty run.brainstorms}" >
								            <c:forEach var="brainstorm" items="${run.brainstorms}" varStatus="brainstormVS" >
								                <li class="qaBullet"><a href="../run/brainstorm/managebrainstorm.html?brainstormId=${brainstorm.id}">Manage Q&A #${brainstormVS.index+1}</a></li>
								            </c:forEach>
								    	</c:if>
								    	 -->		
										<li><a href="/webapp/contactwiseproject.html?projectId=${run.project.id}" target="_top"><spring:message code="teacher.run.myprojectruns.22"/></a></li>
					                    <sec:accesscontrollist domainObject="${run}" hasPermission="16">					    	
								    	  <li><a onclick="javascript:popup('/webapp/teacher/run/manage/archiveRun.html?runId=${run.id}&runName=<c:out value="${fn:escapeXml(run.name)}" />')"><spring:message code="teacher.run.myprojectruns.51"/></a></li>
								    	</sec:accesscontrollist>
								    	
								    </ul>
				
								</td>
								<td style="display:none;">${run.starttime}</td>
								<td style="display:none;"></td>
								<td style="display:none;">${source}</td>
								<td style="display:none;">${ownership}</td>
								<td style="display:none;">
									<c:forEach var="period" items="${run.periods}">${period.name},</c:forEach>
							   </td>
							   </tr>
							  </c:forEach>
							</c:if>
						</tbody>
					</table>
				</div>
			</c:when>
		<c:otherwise>
			<p class="info"><spring:message code="teacher.run.myprojectruns.52"/> <a href="/webapp/teacher/management/library.html">
				<spring:message code="teacher.run.myprojectruns.52A"/></a> <spring:message code="teacher.run.myprojectruns.52D"/>	<a href="/webapp/author/authorproject.html">
				<spring:message code="teacher.run.myprojectruns.52E"/></a>.</p>
		</c:otherwise>
	</c:choose>
	</div><!-- end current runs tab -->

	<div id="archivedRuns">
		<p class="info"><spring:message code="teacher.run.myprojectruns.54"/></p>
		
		<c:choose>
			<c:when test="${fn:length(ended_run_list) > 0}">
			
				<div class="runBox">
					
					<table id="archivedRunTable" class="runTable" border="1" cellpadding="0" cellspacing="0" >
						<thead>
						    <tr>
						       <th style="width:250px;"class="tableHeaderMain archive"><spring:message code="teacher.run.myprojectruns.3A"/></th>
						       <th style="width:140px;" class="tableHeaderMain archive"><spring:message code="teacher.run.myprojectruns.4"/></th>      
						       <th style="width:290px;" class="tableHeaderMain archive"><spring:message code="teacher.run.myprojectruns.5"/></th>
						       <th style="display:none;" class="tableHeaderMain"><spring:message code="teacher.run.myprojectruns.58A" /></th>
						       <th style="display:none;" class="tableHeaderMain"><spring:message code="teacher.run.myprojectruns.58B" /></th>
						       <th style="display:none;" class="tableHeaderMain"><spring:message code="teacher.run.myprojectruns.58C" /></th>
						       <th style="display:none;" class="tableHeaderMain"><spring:message code="teacher.run.myprojectruns.58D" /></th>
						       <th style="display:none;" class="tableHeaderMain"><spring:message code="teacher.run.myprojectruns.58E" /></th>
						    </tr>
						</thead>
						<tbody>				
						    <c:if test="${fn:length(ended_run_list) > 0}">
							 	<c:forEach var="run" items="${ended_run_list}">
							  
							  	<tr class="runTitleRow">
							    	<td class="titleCell">
							    		<div class="runTitle">${run.name}</div>
							    		<c:set var="ownership" value="owned" />
						    			<c:forEach var="sharedowner" items="${run.sharedowners}">
								    	    <c:if test="${sharedowner == user}">
								    	    	<c:set var="ownership" value="shared" />
								    	    		<div class="sharedIcon">
								    	    			<img src="/webapp/themes/tels/default/images/shared.png" alt="shared project" /> <spring:message code="teacher.run.myprojectruns.6"/>
										    	    	<c:forEach var="owner" items="${run.owners}">
										    	    		${owner.userDetails.firstname} ${owner.userDetails.lastname}
										    	    	</c:forEach>
								    	    	</div></c:if>
								    	</c:forEach>
							     
										<table class="runTitleTable">
							      			<tr>
												<th><spring:message code="teacher.run.myprojectruns.8"/></th>
												<td>${run.runcode}</td>
											</tr>
											
							      			<tr>
							      				<th><spring:message code="teacher.run.myprojectruns.11"/></hd>
							      				<td>${run.id}</td>
							      			</tr>
							      			<tr>
							      				<th><spring:message code="teacher.run.myprojectruns.13"/></th>
							      				<td class="archivedDate"><fmt:formatDate value="${run.starttime}" type="date" dateStyle="short" /></td>
							      			</tr>
											 <tr>
							      				<th><spring:message code="teacher.run.myprojectruns.55"/></th>
							      				<td class="archivedDate"><fmt:formatDate value="${run.endtime}" type="date" dateStyle="short" /></td>
							      			</tr>
							      			<!-- <tr>
							      				<th><spring:message code="teacher.run.myprojectruns.12"/></th> TODO: decide whether to include or not -->
							      				<c:set var="source" value="custom" />
							      				<c:choose>
							      				<c:when test="${run.project.familytag == 'TELS'}"> <!-- TODO: modify this to include ALL library projects (not just TELS) -->
								      				<c:set var="source" value="library" />
								      				<!-- <td><spring:message code="teacher.run.myprojectruns.43"/></td> -->
							      				</c:when>
							      				<c:otherwise>
								      				<!-- <td><spring:message code="teacher.run.myprojectruns.44"/></td> -->
							      				</c:otherwise>
							      				</c:choose>
							      			<!-- </tr>  -->
											<tr>
							      				<th><spring:message code="teacher.run.myprojectruns.11A"/></th>
							      				<td><a id="projectDetail_${run.project.id}" class="projectDetail" title="Project Details">${run.project.id}</a></td>
							      			</tr>
							      			<tr>
							      				<c:if test="${run.project.parentProjectId != null}">
							      				<th><spring:message code="teacher.run.myprojectruns.40"/></th>
												<td><a id="projectDetail_${run.project.parentProjectId}" class="projectDetail" title="Project Details">${run.project.parentProjectId}</a></td>
												</c:if>
							      			</tr>
										</table>
									</td>
															
									<td style="vertical-align:top; padding:0px;" >
							    		<table class="currentRunInfoTable" border="0" cellpadding="0" cellspacing="0">
							          		<tr>
							            		<th class="tableInnerHeader"><spring:message code="teacher.run.myprojectruns.7"/></th>
							            		<th class="tableInnerHeader"><spring:message code="teacher.run.myprojectruns.9"/></th>
							          		</tr>
							          		<c:forEach var="period" items="${run.periods}">
								            <tr>
									        	<td style="width:20%;" class="tableInnerData">${period.name}</td>
									        	<td style="width:35%;" class="tableInnerDataRight archivedNumberStudents">
									        	${fn:length(period.members)}&nbsp;<spring:message code="teacher.run.myprojectruns.10"/></td>
								            </tr>
							          		</c:forEach>
										</table>
									</td> 
									<td style="vertical-align:top; padding: 0.25em 0;">
									    <ul class="actionList">
								        	<li><a href="/webapp/previewproject.html?projectId=${run.project.id}&versionId=${run.versionId}" target="_blank">View the Project</a></li>
								        </ul>
								        <ul class="actionList">
					 	                    <li><spring:message code="teacher.run.myprojectruns.16"/>: <a class="grading" title="Grading & Feedback: ${run.name} (Run ID: ${run.id})" id="runId=${run.id}&gradingType=step&getRevisions=false&minified=true"><spring:message code="teacher.run.myprojectruns.42"/></a>&nbsp;|&nbsp;<a class="grading" title="Grading & Feedback: ${run.name} (Run ID: ${run.id})"  id="runId=${run.id}&gradingType=step&getRevisions=true&minified=true"><spring:message code="teacher.run.myprojectruns.41"/></a></li>
							  	            <li><spring:message code="teacher.run.myprojectruns.17"/>: <a class="grading" title="Grading & Feedback: ${run.name} (Run ID: ${run.id})" id="runId=${run.id}&gradingType=team&getRevisions=false&minified=true"><spring:message code="teacher.run.myprojectruns.42"/></a>&nbsp;|&nbsp;<a class="grading" title="Grading & Feedback: ${run.name} (Run ID: ${run.id})" id="runId=${run.id}&gradingType=team&getRevisions=true&minified=true"><spring:message code="teacher.run.myprojectruns.41"/></a></li>		
					                    </ul>
					                    <ul class="actionList actionList2">
					                    	<sec:accesscontrollist domainObject="${run}" hasPermission="16">					    	
								    	  		<li><a onclick="javascript:popup('/webapp/teacher/run/manage/startRun.html?runId=${run.id}&runName=<c:out value="${fn:escapeXml(run.name)}" />')"><spring:message code="teacher.run.myprojectruns.56"/></a></li>
								    		</sec:accesscontrollist>							
										</ul>
									</td>
									<td style="display:none;">${run.starttime}</td>
									<td style="display:none;">${run.endtime}</td>
									<td style="display:none;">${source}</td>
									<td style="display:none;">${ownership}</td>
									<td style="display:none;">
										<c:forEach var="period" items="${run.periods}">${period.name},</c:forEach>
								   </td>
								</tr>
								</c:forEach>
							</c:if>
						</tbody>
					</table>
				</div>
			</c:when>
		<c:otherwise>
			<p class="info"><spring:message code="teacher.run.myprojectruns.57"/></p>
		</c:otherwise>
	</c:choose>
	</div> <!-- End of archived runs tab -->

</div>

<div id="gradingDialog" class="dialog"></div>
<div id="shareDialog" class="dialog"></div>
<div id="editRunDialog" class="dialog"></div>
<div id="editAnnouncementsDialog" class="dialog"></div>
<div id="manageStudentsDialog" style="overflow:hidden;" class="dialog"></div>
<div id="projectDetailDialog" style="overflow:hidden;" class="dialog"></div>
