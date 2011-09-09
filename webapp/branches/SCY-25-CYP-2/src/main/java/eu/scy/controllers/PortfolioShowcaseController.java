package eu.scy.controllers;

import eu.scy.common.mission.MissionRuntimeElo;
import eu.scy.core.model.transfer.Portfolio;
import eu.scy.core.roolo.MissionELOService;
import eu.scy.server.controllers.BaseController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URI;

/**
 * Created by IntelliJ IDEA.
 * User: Henrik
 * Date: 08.sep.2011
 * Time: 13:25:24
 * To change this template use File | Settings | File Templates.
 */
public class PortfolioShowcaseController extends BaseController {

    private MissionELOService missionELOService;

    @Override
    protected void handleRequest(HttpServletRequest request, HttpServletResponse response, ModelAndView modelAndView) {
        URI missionRuntimeURI = getURI(request.getParameter("missionRuntimeURI"));
        if (missionRuntimeURI != null) {
            MissionRuntimeElo missionRuntimeElo = MissionRuntimeElo.loadLastVersionElo(missionRuntimeURI, getMissionELOService());
            Portfolio portfolio = getMissionELOService().getPortfolio(missionRuntimeElo, null);

            String serverPort = "";
            if(request.getServerPort() != 80) {
                serverPort = ":" + request.getServerPort();
            }
            String serverPath = "http://" +  request.getServerName() + serverPort + "/webapp/portfolioShowcase.html?missionRuntimeURI=" + getEncodedUri(missionRuntimeURI.toString());

            modelAndView.addObject("portfolio", portfolio);
            modelAndView.addObject("serverPath", serverPath);
        } else {
            logger.warn("MISSION RUNTIME URI: " + request.getParameter("missionRuntimeURI"));
        }

    }

    public MissionELOService getMissionELOService() {
        return missionELOService;
    }

    public void setMissionELOService(MissionELOService missionELOService) {
        this.missionELOService = missionELOService;
    }
}
