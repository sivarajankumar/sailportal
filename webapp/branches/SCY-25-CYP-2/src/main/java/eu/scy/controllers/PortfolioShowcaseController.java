package eu.scy.controllers;

import eu.scy.common.mission.MissionRuntimeElo;
import eu.scy.common.mission.MissionSpecificationElo;
import eu.scy.core.model.StudentUserDetails;
import eu.scy.core.model.User;
import eu.scy.core.model.transfer.PedagogicalPlanTransfer;
import eu.scy.core.model.transfer.Portfolio;
import eu.scy.core.model.transfer.TransferElo;
import eu.scy.core.roolo.MissionELOService;
import eu.scy.core.roolo.PedagogicalPlanELOService;
import eu.scy.server.controllers.BaseController;
import eu.scy.server.webeport.AnchoELOWithStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URI;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Henrik
 * Date: 08.sep.2011
 * Time: 13:25:24
 * To change this template use File | Settings | File Templates.
 */
public class PortfolioShowcaseController extends BaseController {

    private MissionELOService missionELOService;
    private PedagogicalPlanELOService pedagogicalPlanELOService;

    @Override
    protected void handleRequest(HttpServletRequest request, HttpServletResponse response, ModelAndView modelAndView) {
        URI missionURI = null;
        if(request.getParameter("missionRuntimeURI") != null) {
            missionURI = getURI(request.getParameter("missionRuntimeURI"));
        } else {
            missionURI = getURI(request.getParameter(ELO_URI));
        }

        MissionRuntimeElo missionRuntimeElo = MissionRuntimeElo.loadLastVersionElo(missionURI, getMissionELOService());
        MissionSpecificationElo missionSpecificationElo = getMissionELOService().getMissionSpecificationELOForRuntume(missionRuntimeElo);
        PedagogicalPlanTransfer pedagogicalPlanTransfer = getPedagogicalPlanELOService().getPedagogicalPlanForMission(missionSpecificationElo);
        Portfolio portfolio = getMissionELOService().getPortfolio(missionRuntimeElo, null);
        User user = getUserService().getUser(portfolio.getOwner());
        StudentUserDetails studentUserDetails = (StudentUserDetails) user.getUserDetails();

        List obligatoryAnchorElos = getObligatoryAnchorElos(missionSpecificationElo, pedagogicalPlanTransfer);

        List <AnchoELOWithStatus> anchoELOWithStatuses = new LinkedList<AnchoELOWithStatus>();

        for (int i = 0; i < obligatoryAnchorElos.size(); i++) {
            TransferElo anchorElo = (TransferElo) obligatoryAnchorElos.get(i);
            AnchoELOWithStatus anchoELOWithStatus = new AnchoELOWithStatus();
            anchoELOWithStatus.setAnchorElo(anchorElo);
            if(portfolio != null) {
                anchoELOWithStatus.setAddedElo(portfolio.getEloForAnchroElo(anchorElo));
                if(portfolio.getHasEloBeenAddedForAnchorElo(anchorElo)) anchoELOWithStatus.setEloHasBeenAdded(true);
            }
            if(anchoELOWithStatus.getAddedElo().getIncludeInShowcasePortfolio()) {
                anchoELOWithStatuses.add(anchoELOWithStatus);    
            }

        }

        String serverPort = "";
        if(request.getServerPort() != 80) {
            serverPort = ":" + request.getServerPort();
        }
        String serverPath = "http://" +  request.getServerName() + serverPort + "/webapp/portfolioShowcase.html?missionRuntimeURI=" + getEncodedUri(missionRuntimeElo.getUri().toString());

        modelAndView.addObject("serverPath", serverPath);


        modelAndView.addObject("pedagogicalPlanTransfer", pedagogicalPlanTransfer);
        modelAndView.addObject("obligatoryAnchorElos", obligatoryAnchorElos);
        modelAndView.addObject("portfolio", portfolio);
        modelAndView.addObject("missionRuntimeURI", getEncodedUri(missionURI.toString()));
        modelAndView.addObject("anchorElosWithStatuses", anchoELOWithStatuses);
        modelAndView.addObject("missionReflectionQuestionAnswers", portfolio.getMissionReflectionQuestionAnswers());
        modelAndView.addObject("studentUserDetails", studentUserDetails);

    }

    private List getObligatoryAnchorElos(MissionSpecificationElo missionSpecificationElo, PedagogicalPlanTransfer pedagogicalPlanTransfer) {
        return getMissionELOService().getObligatoryAnchorELOs(missionSpecificationElo, pedagogicalPlanTransfer);
    }


    public MissionELOService getMissionELOService() {
        return missionELOService;
    }

    public void setMissionELOService(MissionELOService missionELOService) {
        this.missionELOService = missionELOService;
    }

    public PedagogicalPlanELOService getPedagogicalPlanELOService() {
        return pedagogicalPlanELOService;
    }

    public void setPedagogicalPlanELOService(PedagogicalPlanELOService pedagogicalPlanELOService) {
        this.pedagogicalPlanELOService = pedagogicalPlanELOService;
    }
}
