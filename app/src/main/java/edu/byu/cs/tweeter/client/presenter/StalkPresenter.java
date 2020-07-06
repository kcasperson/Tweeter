package edu.byu.cs.tweeter.client.presenter;


import edu.byu.cs.tweeter.client.model.service.StalkServiceProxy;
import edu.byu.cs.tweeter.model.net.TweeterRemoteException;
import edu.byu.cs.tweeter.model.service.StalkService;
import edu.byu.cs.tweeter.model.service.request.menu.StalkRequest;
import edu.byu.cs.tweeter.model.service.response.menu.StalkResponse;

import java.io.IOException;

public class StalkPresenter {
//    private final View view;
//
//    public interface View {
//        // If needed, specify methods here that will be called on the view in response to model updates
//    }
//
//
//    public StalkPresenter(StalkPresenter.View view) {
//        this.view = view;
//    }


    public StalkPresenter() {
    }

    public StalkResponse stalk(StalkRequest stalkRequest) throws IOException, TweeterRemoteException {
        StalkService stalkService = new StalkServiceProxy();
        return stalkService.stalk(stalkRequest);
    }
}
